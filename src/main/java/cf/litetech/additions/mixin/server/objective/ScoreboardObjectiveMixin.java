package cf.litetech.additions.mixin.server.objective;

import cf.litetech.additions.helpers.ScoreboardObjectiveHelper;
import net.minecraft.scoreboard.ScoreboardObjective;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ScoreboardObjective.class)
public class ScoreboardObjectiveMixin implements ScoreboardObjectiveHelper {
    private boolean frozen;

    @Override
    public boolean isFrozen() {
        return frozen;
    }

    @Override
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
