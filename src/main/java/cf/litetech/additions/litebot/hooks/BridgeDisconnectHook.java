package cf.litetech.additions.litebot.hooks;

import cf.litetech.litebotmod.commands.CommandHook;
import cf.litetech.litebotmod.commands.ExecutingCommand;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class BridgeDisconnectHook extends CommandHook {
    private final String name;

    public BridgeDisconnectHook(String name) {
        this.name = name;
    }

    public void register() {
        super.register(name, new BridgeDisconnectHook(name));
    }

    @Override
    public void afterInvoke(ExecutingCommand command) {
        try {
            BridgeConnectHook.getConnectedPlayers().remove(command.getContext().getSource().getPlayer());
        } catch (CommandSyntaxException ignored) {}
    }
}
