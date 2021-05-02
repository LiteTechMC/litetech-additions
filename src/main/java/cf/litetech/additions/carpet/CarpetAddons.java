package cf.litetech.additions.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import cf.litetech.additions.litebot.hooks.BridgeConnectHook;
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
        BridgeConnectHook.getConnectedPlayers().remove(player);
    }
}
