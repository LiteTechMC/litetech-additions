package cf.litetech.additions.litebot;

import cf.litetech.additions.litebot.hooks.BridgeConnectHook;
import cf.litetech.additions.litebot.hooks.BridgeDisconnectHook;
import cf.litetech.additions.litebot.hooks.LocationGetHook;
import cf.litetech.additions.litebot.hooks.PosHook;
import cf.litetech.litebotmod.LiteBotExtension;
import cf.litetech.litebotmod.LiteBotMod;

public class Extension implements LiteBotExtension {

    public static void register() {
        LiteBotMod.addExtension(new Extension());
    }

    @Override
    public void registerHooks() {
        new BridgeConnectHook("bridge.connect").register();
        new BridgeDisconnectHook("bridge.disconnect").register();
        new PosHook("pos").register();
        new LocationGetHook("location.get").register();
    }
}
