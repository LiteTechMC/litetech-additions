package cf.litetech.additions;

import cf.litetech.additions.litebot.Extension;
import net.fabricmc.api.DedicatedServerModInitializer;

public class LiteTechAdditions implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Extension.register();
    }
}
