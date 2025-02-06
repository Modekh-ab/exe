package net.modekh.exe.events;

import divinerpg.api.ArmorHandlers;
import lain.mods.cos.CosmeticArmorReworked;
import lain.mods.cos.inventory.InventoryCosArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.modekh.exe.objects.items.base.ArmorBase;
import net.modekh.exe.utils.Reference;

public class EventHandler {
    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class ModEvents {
        private static boolean canFly = false;

        @SubscribeEvent
        public static void onCosmeticTick(TickEvent.PlayerTickEvent event) {
            World world = event.player.world;

            if (world.isRemote)  {
                EntityPlayer player = event.player;

                triggerFlying(player, checkInventory(player, player.inventory, false)
                        || checkInventory(player,
                        CosmeticArmorReworked.invMan.getCosArmorInventory(player.getUniqueID()),
                        true));
            }
        }

        @SubscribeEvent
        public static void onPlayerFall(LivingFallEvent event) {
            if (canFly) {
                ArmorHandlers.disableFallDamage(event);
            }
        }

        private static boolean checkInventory(EntityPlayer player, IInventory inventory, boolean isCosInventory) {
            for (int slotId = 0; slotId < 4; slotId++) {
                ItemStack stack = isCosInventory
                        ? ((InventoryCosArmor) inventory).func_70301_a(slotId)
                        : inventory.getStackInSlot(slotId + 36); // 36-39 - default armor slots

                if (stack.isEmpty() || !(stack.getItem() instanceof ArmorBase)) {
                    return false;
                }
            }

            return true;
        }

        private static void triggerFlying(EntityPlayer player, boolean trigger) {
            canFly = trigger;
            ArmorHandlers.onCanFlyChanged(player, trigger);
        }
    }
}
