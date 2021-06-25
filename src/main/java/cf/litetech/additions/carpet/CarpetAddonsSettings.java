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

    @Rule(
            desc = "Display bots on the sidebar",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean displayBots = true;

    @Rule(
            desc = "Player's bedrock \"mined\" statistic is increased when a bedrock block is broken 2 game ticks after a piston is placed",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean bedrockBrokenStatistics = false;
}
