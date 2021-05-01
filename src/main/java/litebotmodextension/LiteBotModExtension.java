package litebotmodextension;

import net.fabricmc.api.DedicatedServerModInitializer;

public class LiteBotModExtension implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        // Register our extension
        ExampleExtension.register();
    }
}
