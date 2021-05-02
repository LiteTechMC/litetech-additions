package cf.litetech.additions.mixin.server.objective;

import cf.litetech.additions.helpers.ScoreboardObjectiveHelper;
import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(Scoreboard.class)
public abstract class ScoreboardMixin {
    @Shadow
    @Final
    private final Map<String, Map<ScoreboardObjective, ScoreboardPlayerScore>> playerObjectives = Maps.newHashMap();

    @Shadow
    public abstract ScoreboardObjective getObjective(String name);

    @Shadow public abstract ScoreboardPlayerScore getPlayerScore(String player, ScoreboardObjective objective);

    @Inject(method = "toTag", at = @At("HEAD"), cancellable = true)
    public void toTag(CallbackInfoReturnable<ListTag> cir) {
        cir.cancel();

        ListTag listTag = new ListTag();
        this.playerObjectives.values().stream().map(Map::values).forEach((collection) -> {
            collection.stream().filter((score) -> score.getObjective() != null).forEach((score) -> {
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putString("Name", score.getPlayerName());
                compoundTag.putString("Objective", score.getObjective().getName());
                compoundTag.putInt("Score", score.getScore());
                compoundTag.putBoolean("Locked", score.isLocked());
                compoundTag.putBoolean("Frozen", ((ScoreboardObjectiveHelper) score.getObjective()).isFrozen());
                listTag.add(compoundTag);
            });
        });

        cir.setReturnValue(listTag);
    }

    @Inject(method = "fromTag", at = @At("HEAD"), cancellable = true)
    public void fromTag(ListTag listTag, CallbackInfo ci) {
        for(int i = 0; i < listTag.size(); ++i) {
            CompoundTag compoundTag = listTag.getCompound(i);
            ScoreboardObjective scoreboardObjective = this.getObjective(compoundTag.getString("Objective"));
            String string = compoundTag.getString("Name");
            if (string.length() > 40) {
                string = string.substring(0, 40);
            }

            ScoreboardPlayerScore scoreboardPlayerScore = this.getPlayerScore(string, scoreboardObjective);
            scoreboardPlayerScore.setScore(compoundTag.getInt("Score"));
            if (compoundTag.contains("Locked")) {
                scoreboardPlayerScore.setLocked(compoundTag.getBoolean("Locked"));
            }

            if (compoundTag.contains("Frozen")) {
                ((ScoreboardObjectiveHelper) scoreboardObjective).setFrozen(compoundTag.getBoolean("Frozen"));
            }
        }
    }
}
