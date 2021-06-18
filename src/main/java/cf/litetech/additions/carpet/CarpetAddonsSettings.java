package cf.litetech.additions.carpet;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

public class CarpetAddonsSettings {
    public static final String LITETECH = "LiteTech";

    @Rule(
            desc = "Enables players without OP to change what objective is being displayed, and query a player's objectives",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean sidebarCommand = true;

    @Rule(
            desc = "Permission Level required for the seed command",
            category = {LITETECH, SURVIVAL}
    )
    public static int seedPermissionLevel = 0;

    @Rule(
            desc = "Display total score on the sidebar",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean totalScore = true;

}
