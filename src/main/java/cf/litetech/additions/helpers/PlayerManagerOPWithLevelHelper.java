package cf.litetech.additions.helpers;

import com.mojang.authlib.GameProfile;

public interface PlayerManagerOPWithLevelHelper {
    void addToOperators(GameProfile profile, int level, boolean bypassesPlayerLimit);
}
