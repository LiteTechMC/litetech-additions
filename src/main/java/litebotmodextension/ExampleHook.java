package litebotmodextension;

import cf.litetech.litebotmod.commands.CommandHook;
import cf.litetech.litebotmod.commands.ExecutingCommand;

public class ExampleHook extends CommandHook {
    private final String name;

    ExampleHook(String name) {
        this.name = name;
    }

    /**
     * Registers the command hook
     */
    public void register() {
        super.register(name, new ExampleHook(name));
    }

    /**
     * Method called before the command is invoked
     * @param command The executing command {@link ExecutingCommand}
     */
    @Override
    public void beforeInvoke(ExecutingCommand command) {
        System.out.println("beforeInvoke");
    }

    /**
     * Method called after the command callback finishes
     * @param command The executing command {@link ExecutingCommand}
     */
    @Override
    public void afterInvoke(ExecutingCommand command) {
        System.out.println("afterInvoke");
    }
}
