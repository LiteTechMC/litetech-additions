package cf.litetech.additions;

import net.fabricmc.api.DedicatedServerModInitializer;

public class LiteTechAdditions implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        System.out.println("Server is online!!!!");
    }
}
