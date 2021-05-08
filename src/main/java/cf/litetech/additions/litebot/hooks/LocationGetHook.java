package cf.litetech.additions.litebot.hooks;

import cf.litetech.litebotmod.commands.CommandHook;
import cf.litetech.litebotmod.commands.ExecutingCommand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationGetHook extends CommandHook {
    private final String name;
    private final HashMap<ServerPlayerEntity, EndCrystalEntity> LOCATION_TRACKERS = new HashMap<>();

    public LocationGetHook(String name) {
        this.name = name;
    }

    public void register() {
        super.register(name, new LocationGetHook(name));
    }

    @Override
    public void afterInvoke(ExecutingCommand command, HashMap<String, Object> args) {
        String locationString = (String) args.get("location");
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();

        HashMap<String, Object> locationMap = new Gson().fromJson(locationString, type);
        List<Double> coordinates = ((ArrayList<Double>) locationMap.get("coordinates"));
        ServerWorld serverWorld = command.getContext().getSource().getWorld();


        EndCrystalEntity crystal = new EndCrystalEntity(serverWorld, coordinates.get(0), -5, coordinates.get(2));
        crystal.setBeamTarget(new BlockPos(coordinates.get(0), 255, coordinates.get(2)));

        try {
            ServerPlayerEntity player = command.getContext().getSource().getPlayer();

            if (LOCATION_TRACKERS.containsKey(player)) {
                LOCATION_TRACKERS.get(player).kill();
            }

            serverWorld.spawnEntity(crystal);
            LOCATION_TRACKERS.put(command.getContext().getSource().getPlayer(), crystal);
        } catch (CommandSyntaxException ignored) {}
    }

    public void removeCrystal(ServerPlayerEntity player) {
        if (LOCATION_TRACKERS.containsKey(player)) {
            LOCATION_TRACKERS.get(player).kill();
            LOCATION_TRACKERS.remove(player);
        }
    }

    public void onTick() {
        for (ServerPlayerEntity player : LOCATION_TRACKERS.keySet()) {
            Entity ent = LOCATION_TRACKERS.get(player);
            if (Math.sqrt(Math.pow((player.getX() - ent.getX()), 2) + Math.pow((player.getZ() - ent.getZ()), 2)) <= 10) {
                player.sendSystemMessage(new LiteralText(
                        "You've reached your location!").styled(style -> style.withColor(Formatting.AQUA)),
                        Util.NIL_UUID);
                ent.kill();
                LOCATION_TRACKERS.remove(player);
            }
        }
    }
}