package cf.litetech.additions.mixin.server;

import carpet.patches.EntityPlayerMPFake;
import cf.litetech.additions.carpet.CarpetAddonsSettings;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ScoreboardPlayerUpdateS2CPacket;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Mixin(ServerScoreboard.class)
public abstract class ServerScoreboardMixin extends Scoreboard  {
    @Shadow
    @Final
    private MinecraftServer server;
    private ScoreboardPlayerScore score;

    @Inject(
            method = "updateScore",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/PlayerManager;sendToAll(Lnet/minecraft/network/Packet;)V"
            )
    )
    public void updateScore(ScoreboardPlayerScore score, CallbackInfo ci) {
        ScoreboardObjective objective = score.getObjective();
        if (CarpetAddonsSettings.totalScore)
            this.server.getPlayerManager().sendToAll(this.createTotalPacket(objective));
    }

    @Inject(
            method = "createChangePackets",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void addTotalScore(ScoreboardObjective objective, CallbackInfoReturnable<List<Packet<?>>> cir,
                              List<Packet<?>> list) {
        if (CarpetAddonsSettings.totalScore)
            list.add(this.createTotalPacket(objective));
    }

    @Inject(
            method = "createChangePackets",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/network/packet/s2c/play/ScoreboardPlayerUpdateS2CPacket"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void getPlayerScore(ScoreboardObjective objective, CallbackInfoReturnable<List<Packet<?>>> cir,
                               List<Packet<?>> list, Iterator<?> var5, ScoreboardPlayerScore score) {
        this.score = score;
    }

    public ScoreboardPlayerUpdateS2CPacket createTotalPacket(ScoreboardObjective objective) {
        int i = 0;
        for (ScoreboardPlayerScore score : this.getAllPlayerScores(objective))
            i += score.getScore();

        System.out.println(i);
        return new ScoreboardPlayerUpdateS2CPacket(ServerScoreboard.UpdateMode.CHANGE, objective.getName(),
                "Total", i);
    }

//    @Redirect(
//            method = "createChangePackets",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
//                    ordinal = 2
//            )
//    )
//    public boolean checkBotFilter(List<Packet<?>> list, Object packet) {
//        System.out.println(this.score.getPlayerName());
//        System.out.println(this.server.getPlayerManager().getPlayer(this.score.getPlayerName()) instanceof EntityPlayerMPFake);
//        if (CarpetAddonsSettings.displayBots && (this.server.getPlayerManager().getPlayer(this.score.getPlayerName()) instanceof EntityPlayerMPFake)) {
//            return false;
//        }
//        return list.add((ScoreboardPlayerUpdateS2CPacket) packet);
//    }

}

