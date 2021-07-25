package cf.litetech.additions.mixin.server.entity;

import cf.litetech.additions.carpet.CarpetAddonsSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DrownedEntity.class)
public abstract class DrownedEntityMixin {
    @ModifyVariable(
            method = "attack(Lnet/minecraft/entity/LivingEntity;F)V",
            at = @At("STORE")
    )
    private TridentEntity attackInitTrident(TridentEntity original) {
        if (!CarpetAddonsSettings.drownedTridentNBTFix)
            return original;

        DrownedEntity entity = (DrownedEntity)(Object) this;
        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.MAINHAND);

        return new TridentEntity(entity.world, entity, itemStack);
    }
}
