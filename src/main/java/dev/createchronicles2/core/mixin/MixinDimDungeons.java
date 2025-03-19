package dev.createchronicles2.core.mixin;
import dev.createchronicles2.core.CreateChroniclesCore;
import net.minecraft.world.InteractionResult;
import org.slf4j.Logger;


import com.catastrophe573.dimdungeons.utils.DungeonUtils;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.living.LivingDestroyBlockEvent;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import com.catastrophe573.dimdungeons.PlayerDungeonEvents;
import com.catastrophe573.dimdungeons.utils.DungeonUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerDungeonEvents.class)
public abstract class MixinDimDungeons {

    @Inject(method = "rightClickBlock(Lnet/neoforged/neoforge/event/entity/player/PlayerInteractEvent$RightClickItem;)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private void blockEnderPearlUse(PlayerInteractEvent.RightClickItem event, CallbackInfo ci) {
       // CreateChroniclesCore.LOGGER.info("ENTREOU------------------");


       /* if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (DungeonUtils.isDimensionDungeon(player.level()) &&
                event.getItemStack().is(Items.ENDER_PEARL)) {

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.FAIL);
            ci.cancel();
        }*/
    }
}
