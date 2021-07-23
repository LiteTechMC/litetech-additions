package cf.litetech.additions.mixin.client;

import cf.litetech.litebotmod.connection.RequestBuilder;
import cf.litetech.litebotmod.network.LiteBotClient;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.CommandSuggestor;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.command.CommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Mixin(CommandSuggestor.class)
public abstract class CommandSuggesterMixin {

    @Shadow @Final private TextFieldWidget textField;
    @Shadow
    private static int getLastPlayerNameStart(String input) {
        return 0;
    }

    @Shadow private CompletableFuture<Suggestions> pendingSuggestions;
    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract void showSuggestions(boolean narrateFirstSuggestion);

    private boolean displayMentions = false;
    private ArrayList<String> mentions = null;

    @Inject(method = "refresh", at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(II)Ljava/lang/String;"), cancellable = true)
    public void refresh(CallbackInfo ci) {
        if (!LiteBotClient.isLitebotServer() || !LiteBotClient.isLiteBotConnected()) return;

        String text = this.textField.getText().substring(0, this.textField.getCursor());
        int lastPlayerNameStart = getLastPlayerNameStart(text);

        if (this.textField.getCursor() != 0) {
            if (this.textField.getText().charAt(this.textField.getCursor() - 1) == '@') this.displayMentions = true;
            else if (this.textField.getText().charAt(this.textField.getCursor() - 1) == ' ') this.displayMentions = false;
        } else if (this.displayMentions) this.displayMentions = false;

        if (!this.displayMentions) {
            this.pendingSuggestions = CommandSource.suggestMatching(this.client.player.networkHandler.getCommandSource()
                    .getPlayerNames(), new SuggestionsBuilder(text, lastPlayerNameStart));
            return;
        }

        if (this.mentions == null) this.mentions = new RequestBuilder<ArrayList<String>>("rpc")
                .setName("mentions").requestToServer();
        this.pendingSuggestions = CommandSource.suggestMatching(this.mentions,
                new SuggestionsBuilder(text, lastPlayerNameStart));
        this.showSuggestions(false);
    }

}
