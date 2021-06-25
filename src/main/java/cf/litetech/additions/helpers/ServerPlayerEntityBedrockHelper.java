package cf.litetech.additions.helpers;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ServerPlayerEntityBedrockHelper {
    int getTimeSincePistonPlaced();
    void incrementTimeSincePistonPlaced();
    void resetTimeSincePistonPlaced();
    boolean hasPlacedPiston();
    void toggleHasPlacedPiston(boolean value);
}
