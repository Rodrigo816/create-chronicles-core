package dev.createchronicles2.core;

import com.hollingsworth.arsnouveau.api.mana.IManaCap;
import com.hollingsworth.arsnouveau.api.util.ManaUtil;
import com.hollingsworth.arsnouveau.api.event.SpellCastEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ArrowLooseEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ItemUseEventHandler {

    private static final String DUNGEON_DIMENSION_NAMESPACE = "dimdungeons";
    private final Map<UUID, Integer> savedMana = new HashMap<>();

    private static final Set<String> BLOCKED_ITEMS = Set.of(
            "minecraft:ender_pearl",
            "minecraft:chorus_fruit",
            "minecraft:trident",
            "supplementaries:slingshot",
            "supplementaries:bubble_blower",
            "supplementaries:ash_brick",
            "supplementaries:rope_arrow",
            "ars_nouveau:spell_bow",
            "ars_nouveau:spell_wand",
            "ars_nouveau:spell_crossbow"
    );

    @SubscribeEvent
    public void onItemUse(PlayerInteractEvent.RightClickItem event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (player.level().dimension().location().getNamespace().equals(DUNGEON_DIMENSION_NAMESPACE)) {
            ItemStack stack = event.getItemStack();
            String registryName = stack.getItem().builtInRegistryHolder().key().location().toString();

            if (BLOCKED_ITEMS.contains(registryName)) {
                event.setCanceled(true);
                    player.displayClientMessage(
                            Component.literal("You can't use " + stack.getHoverName().getString() + " on this Dungeon!")
                                    .withStyle(ChatFormatting.RED), true);
            }
        }
    }

    @SubscribeEvent
    public void onArrowShoot(ArrowLooseEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (player.level().dimension().location().getNamespace().equals(DUNGEON_DIMENSION_NAMESPACE)) {
            ItemStack arrowStack = player.getProjectile(player.getUseItem());

            String registryName = arrowStack.getItem().builtInRegistryHolder().key().location().toString();

            if ("supplementaries:rope_arrow".equals(registryName)) {
                event.setCanceled(true);
                player.displayClientMessage(
                        Component.literal("You can't use Rope Arrows here!")
                                .withStyle(ChatFormatting.RED), true
                );
            }
        }
    }
    @SubscribeEvent
    public void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        boolean enteringDungeon = event.getTo().location().getNamespace().equals(DUNGEON_DIMENSION_NAMESPACE);

        if (enteringDungeon) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public void onSpellCast(SpellCastEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (player.level().isClientSide) return;

        if (player.level().dimension().location().getNamespace().equals(DUNGEON_DIMENSION_NAMESPACE)) {
            event.setCanceled(true);
            player.displayClientMessage(
                    Component.literal("Your magic is sealed here!")
                            .withStyle(ChatFormatting.RED), true
            );
        }
    }
}