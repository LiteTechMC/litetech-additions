package cf.litetech.additions.mixin.server.objective;

import cf.litetech.additions.helpers.ScoreboardObjectiveHelper;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScoreboardPlayerScore.class)
public class ScoreboardPlayerScoreMixin {

    @Shadow @Final @Nullable private ScoreboardObjective objective;

    @Inject(method = "incrementScore(I)V", at = @At("HEAD"), cancellable = true)
    public void incrementScore(int amount, CallbackInfo ci) {
        if (this.objective == null) return;

        if (((ScoreboardObjectiveHelper) this.objective).isFrozen()) {
            ci.cancel();
        }
    }

    @Inject(method = "incrementScore()V", at = @At("HEAD"), cancellable = true)
    public void incrementScore(CallbackInfo ci) {
        if (this.objective == null) return;

        if (((ScoreboardObjectiveHelper) this.objective).isFrozen()) {
            ci.cancel();
        }
    }
}
