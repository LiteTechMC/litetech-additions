package cf.litetech.additions.litebot.hooks;

import cf.litetech.litebotmod.commands.CommandHook;
import cf.litetech.litebotmod.commands.ExecutingCommand;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class BridgeConnectHook extends CommandHook {
    private static final List<ServerPlayerEntity> connectedPlayers = new ArrayList<>();
    private final String name;

    public BridgeConnectHook(String name) {
        this.name = name;
    }

    public void register() {
        super.register(name, new BridgeConnectHook(name));
    }

    @Override
    public void afterInvoke(ExecutingCommand command) {
        ServerPlayerEntity player;
        try {
            player = command.getContext().getSource().getPlayer();
        } catch (CommandSyntaxException ignored) {
            return;
        }

        if (!connectedPlayers.contains(player)) {
            connectedPlayers.add(player);
        }
    }

    public static List<ServerPlayerEntity> getConnectedPlayers() {
        return connectedPlayers;
    }
}
