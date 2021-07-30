package cf.litetech.additions;

import cf.litetech.additions.carpet.CarpetAddons;
import cf.litetech.additions.litebot.Extension;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class LiteTechAdditions implements DedicatedServerModInitializer {
    public static final boolean RUNNING_LITEBOT_MOD = FabricLoader.getInstance().isModLoaded("litebot-mod");
    public static final boolean RUNNING_CARPET = FabricLoader.getInstance().isModLoaded("carpet");

    @Override
    public void onInitializeServer() {
        if (RUNNING_LITEBOT_MOD) Extension.register();
        if (RUNNING_CARPET) CarpetAddons.register();
    }
}
