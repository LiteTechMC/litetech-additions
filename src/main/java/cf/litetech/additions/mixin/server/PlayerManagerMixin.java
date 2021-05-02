package cf.litetech.additions.mixin.server;

import carpet.CarpetServer;
import cf.litetech.additions.litebot.hooks.BridgeConnectHook;
import net.minecraft.network.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Inject(method = "broadcastChatMessage", at = @At("HEAD"), cancellable = true)
    public void broadcastChatMessage(Text message, MessageType type, UUID senderUuid, CallbackInfo ci) {
        ServerPlayerEntity sender = CarpetServer.minecraft_server.getPlayerManager().getPlayer(senderUuid);

        System.out.println(BridgeConnectHook.getConnectedPlayers());
        if (BridgeConnectHook.getConnectedPlayers().contains(sender)) {
            ci.cancel();
            assert sender != null;
            System.out.println("check");
            sender.sendSystemMessage(message, senderUuid);
        }
    }
}
