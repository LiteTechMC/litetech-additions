package cf.litetech.additions.litebot.hooks;

import cf.litetech.litebotmod.commands.CommandHook;
import cf.litetech.litebotmod.commands.ExecutingCommand;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

public class PosHook extends CommandHook {
    private final String name;

    public PosHook(String name) {
        this.name = name;
    }

    public void register() {
        super.register(name, new PosHook(name));
    }

    @Override
    public void afterInvoke(ExecutingCommand command) {
        ServerPlayerEntity player = null;

        if (command.getArguments().containsKey("player")) {
            player = (ServerPlayerEntity) command.getArguments().get("player");
        }
        else {
            try {
                player = command.getContext().getSource().getPlayer();
            } catch (CommandSyntaxException ignored) {}
        }

        player.addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(24), 1200, 1));
    }
}