# LiteTech Additions

A [fabric-carpet](https://github.com/gnembon/fabric-carpet) and [litebot-mod](https://github.com/iDarkLightning/litebot-mod) extension
that adds some useful features to the game, as well as extends some standard LiteBot commands.

# Carpet Extensions

## sidebarCommand

Enables players without OP to change what objective is being displayed, and query a player's objectives. 
Players with OP level 1 can also freeze or unfreeze an objective, which will stop scores for an objective from increasing.

* Type: `Boolean`
* Default value: `true`
* Required options: `false`, `true`
* Categories: `LITETECH`, `SURVIVAL`

## goalCommand

Set a goal for a scoreboard objective to complete. This goal is for individual players and is not persistent between logins.

* Type: `Boolean`
* Default value: `true`
* Required options: `false`, `true`
* Categories: `LITETECH`, `SURVIVAL`

## betterOPCommand

Allows you to set OP level when OPing players.

* Type: `Boolean`
* Default value: `true`
* Required options: `false`, `true`
* Categories: `LITETECH`, `SURVIVAL`

## seedPermissionLevel

The permission level required in order to use the seed command.

* Type: `Integer`
* Default value: `2`
* Required options: `0`, `1`, `2`, `3`, `4`
* Categories: `LITETECH`, `SURVIVAL`

## teamPermissionLevel

The permission level required in order to use the team command.

* Type: `Integer`
* Default value: `2`
* Required options: `0`, `1`, `2`, `3`, `4`
* Categories: `LITETECH`, `SURVIVAL`

## defaultOpLevel

The OP level used by default when OP-ing a player. -1 Will use the default.

* Type: `Integer`
* Default value: `-1`
* Required options: `-1`, `1`, `2`, `3`, `4`
* Categories: `LITETECH`, `SURVIVAL`

## totalScore

Display total score on the sidebar

* Type: `Boolean`
* Default value: `false`
* Required options: `false`, `true`
* Categories: `LITETECH`, `SURVIVAL`

## bedrockBrokenStatistics

Player's bedrock "mined" statistic is increased when a bedrock block is broken 2 game ticks after a piston is placed

* Type: `Boolean`
* Default value: `false`
* Required options: `false`, `true`
* Categories: `LITETECH`, `SURVIVAL`

## drownedTridentNBTFix

Allows trident enchantments to work for drowned

* Type: `Boolean`
* Default value: `false`
* Required options: `false`, `true`
* Categories: `LITETECH`, `SURVIVAL`

# LiteBot-Mod Extensions

## Bridge Command

Stop messages sent by players connected to another server via the `/bridge` being displayed on their current server.
Useful to ensure that other players on the server don't receive out of context messages.

## Location Command

Spawns an end-crystal with the beam facing directly upwards when the `/location get` command is used. Serves as an indicator
to help the player easily find the location

## Position Command

Gives the player the glowing effect upon using the `/pos` command so that they are easier to find.