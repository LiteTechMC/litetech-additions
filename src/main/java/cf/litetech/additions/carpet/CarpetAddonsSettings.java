package cf.litetech.additions.carpet;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.*;

public class CarpetAddonsSettings {
    public static final String LITETECH = "LiteTech";

    @Rule(
            desc = "Enables players without OP to change what objective is being displayed, and query a player's objectives",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean sidebarCommand = true;

    @Rule(
            desc = "Set a goal for a scoreboard objective to complete",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean goalCommand = true;

    @Rule(
            desc = "Permission Level required for the seed command",
            category = {LITETECH, SURVIVAL}
    )
    public static int seedPermissionLevel = 2;

    @Rule(
            desc = "Permission Level required for the teams command",
            category = {LITETECH, SURVIVAL}
    )
    public static int teamPermissionLevel = 2;

    @Rule(
            desc = "The OP level used by default when OP-ing a player",
            extra = "-1 will use the default",
            validate = ValidatorOpLevel.class,
            options = {"-1", "1", "2", "3", "4"},
            category = {LITETECH, CREATIVE}
    )
    public static int defaultOpLevel = -1;

    public static class ValidatorOpLevel extends Validator<Integer> {
        @Override
        public Integer validate(ServerCommandSource source, ParsedRule<Integer> rule, Integer newValue, String string) {
            return (newValue == -1) || (newValue > 0 && newValue <= 4) ? newValue : null;
        }

        @Override
        public String description() { return "Choose a value between 1 to 4 (or -1)"; }
    }

    @Rule(
            desc = "Display total score on the sidebar",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean totalScore = false;

    @Rule(
            desc = "Player's bedrock \"mined\" statistic is increased when a bedrock block is broken 2 game ticks after a piston is placed",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean bedrockBrokenStatistics = false;

    @Rule(
            desc = "Allows trident enchantments to work for drowned",
            category = {LITETECH, SURVIVAL}
    )
    public static boolean drownedTridentNBTFix = false;
}
