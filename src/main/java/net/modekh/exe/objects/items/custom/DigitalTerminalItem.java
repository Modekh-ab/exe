package net.modekh.exe.objects.items.custom;

import appeng.api.features.INetworkEncodable;
import appeng.util.Platform;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.modekh.exe.registry.ItemRegistry;
import net.modekh.exe.objects.items.base.ItemBase;
import net.modekh.exe.utils.functions.TooltipUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class DigitalTerminalItem extends ItemBase implements INetworkEncodable {
    private static final String ACCESS_KEY = "unpacked";

    public DigitalTerminalItem(String id) {
        super(id);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> components, ITooltipFlag flag) {
        String prefix = this.getUnlocalizedName() + ".info.";
        prefix += stack.hasTagCompound() ? "1." : "0.";

        TooltipUtils.addTooltipTranslationOnShift(components, Arrays.asList(prefix + "0", prefix + "1"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();

            if (tag != null) {
                if (tag.getString("encryptionKey").equals(ACCESS_KEY)) {
                    player.setHeldItem(hand, ItemRegistry.EXE_STONE.getDefaultInstance());
                    player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);

                    // hurt
                    player.setHealth(player.getHealth() - 11F);
                    player.performHurtAnimation();
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 220, 0));
                } else {
                    player.sendStatusMessage(new TextComponentTranslation(this.getUnlocalizedName() + ".message"), true);
                    this.setEncryptionKey(stack, ACCESS_KEY, "");
                }

                return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
        }

        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public String getEncryptionKey(ItemStack stack) {
        final NBTTagCompound tag = Platform.openNbtData(stack);
        return tag.getString("encryptionKey");
    }

    @Override
    public void setEncryptionKey(ItemStack stack, String encKey, String name) {
        final NBTTagCompound tag = Platform.openNbtData(stack);

        tag.setString("encryptionKey", encKey);
        tag.setString("name", name);
    }
}
