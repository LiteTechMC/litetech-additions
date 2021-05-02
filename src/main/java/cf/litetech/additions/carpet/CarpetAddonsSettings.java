package cf.litetech.additions.carpet;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

public class CarpetAddonsSettings {
    public static final String LITETECH = "LiteTech";

    @Rule(
            desc = "Enables players without OP to change what objective is being displayed, and query a player's objectives",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean sidebarCommand;
}
