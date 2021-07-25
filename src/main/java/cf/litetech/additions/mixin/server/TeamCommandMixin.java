package cf.litetech.additions.mixin.server;

import cf.litetech.additions.carpet.CarpetAddonsSettings;
import net.minecraft.server.command.TeamCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TeamCommand.class)
public class TeamCommandMixin {
    @ModifyArg(method = "method_13719", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/command/ServerCommandSource;hasPermissionLevel(I)Z"))
    private static int register(int original) {
        return CarpetAddonsSettings.teamPermissionLevel;
    }
}
