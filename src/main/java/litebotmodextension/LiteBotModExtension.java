package litebotmodextension;

import net.fabricmc.api.DedicatedServerModInitializer;

public class LiteBotModExtension implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        // Register LiteBot extension
        ExampleExtension.register();

        // Register carpet extension
        litebotmodextension.carpet.ExampleExtension.register();
    }
}
