package litebotmodextension;

import cf.litetech.litebotmod.LiteBotExtension;
import cf.litetech.litebotmod.LiteBotMod;

public class ExampleExtension implements LiteBotExtension {
    /**
     * Register all the command hooks here
     */
    @Override
    public void registerHooks() {
        new ExampleHook("bridge.connect").register();
    }

    /**
     * Register your extension with LiteBotMod
     */
    public static void register() {
        LiteBotMod.addExtension(new ExampleExtension());
    }

    /**
     * A method called whenever a websocket message from LiteBot is sent
     * @param message The string message sent from LiteBot
     */
    @Override
    public void onWebsocketMessage(String message) {
        System.out.println(message);
    }

    /**
     * A method called when the websocket connection with LiteBot is opened
     */
    @Override
    public void onWebsocketOpen() {
        System.out.println("Websocket connection established!");
    }

    /**
     * A method called when the websocket connection with LiteBot is closed
     * @param code The code for the connection closing
     * @param reason The reason for the connection closing
     * @param remote Whether the connection was closed remotely
     */
    @Override
    public void onWebsocketClose(int code, String reason, boolean remote) {
        System.out.println("Websocket connection closed!");
    }

    /**
     * A method called when there is an error in the websocket connection
     * @param ex The exception that was thrown
     */
    @Override
    public void onWebsocketError(Exception ex) {
        System.out.println();
    }
}
