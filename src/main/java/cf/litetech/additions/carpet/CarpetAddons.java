package cf.litetech.additions.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import cf.litetech.additions.LiteTechAdditions;
import cf.litetech.additions.commands.GoalCommand;
import cf.litetech.additions.commands.SideBarCommand;
import cf.litetech.additions.helpers.ServerPlayerEntityBedrockHelper;
import cf.litetech.additions.litebot.hooks.BridgeConnectHook;
import cf.litetech.additions.litebot.hooks.LocationGetHook;
import cf.litetech.litebotmod.commands.CommandHook;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class CarpetAddons implements CarpetExtension {
    public static void noop() {}

    public static void register() {
        CarpetServer.manageExtension(new CarpetAddons());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetAddonsSettings.class);
    }

    @Override
    public void onPlayerLoggedOut(ServerPlayerEntity player) {
        if (!LiteTechAdditions.RUNNING_LITEBOT_MOD) return;

        BridgeConnectHook.getConnectedPlayers().remove(player);
        ((LocationGetHook) CommandHook.getRegisteredHooks().get("location.get")).removeCrystal(player);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        SideBarCommand.register(dispatcher);
        GoalCommand.register(dispatcher);
    }

    @Override
    public void onTick(MinecraftServer server) {
        if (!LiteTechAdditions.RUNNING_LITEBOT_MOD) {
            GoalCommand.tick();
            return;
        }

        ((LocationGetHook) CommandHook.getRegisteredHooks().get("location.get")).onTick();

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ServerPlayerEntityBedrockHelper castPlayer = (ServerPlayerEntityBedrockHelper) player;
            if (castPlayer.hasPlacedPiston()) {
                if (castPlayer.getTimeSincePistonPlaced() > 2) {
                    castPlayer.resetTimeSincePistonPlaced();
                }

                castPlayer.incrementTimeSincePistonPlaced();
            }
        }
    }


    @Override
    public void onServerClosed(MinecraftServer server) {
        if (!LiteTechAdditions.RUNNING_LITEBOT_MOD) return;

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ((LocationGetHook) CommandHook.getRegisteredHooks().get("location.get")).removeCrystal(player);
        }
    }
}
