package cf.litetech.additions.mixin.server;

import cf.litetech.additions.carpet.CarpetAddonsSettings;
import net.minecraft.server.command.SeedCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SeedCommand.class)
public abstract class SeedCommandMixin {
    @ModifyArg(method = "method_13618", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/command/ServerCommandSource;hasPermissionLevel(I)Z"))
    private static int register(int original) {
        return CarpetAddonsSettings.seedPermissionLevel;
    }

}
