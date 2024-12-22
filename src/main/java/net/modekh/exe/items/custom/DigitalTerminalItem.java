package net.modekh.exe.items.custom;

import appeng.api.features.INetworkEncodable;
import appeng.util.Platform;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.modekh.exe.init.ItemRegistry;
import net.modekh.exe.items.base.ItemBase;
import net.modekh.exe.utils.functions.TooltipUtils;

import javax.annotation.Nullable;
import java.util.List;

public class DigitalTerminalItem extends ItemBase implements INetworkEncodable {
    private static final String ACCESS_KEY = "unblocked";

    public DigitalTerminalItem(String id) {
        super(id);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> components, ITooltipFlag flag) {
        if (stack.hasTagCompound()) {
            TooltipUtils.addTooltipOnShift(components, this.getUnlocalizedName() + ".info.1");
        } else {
            TooltipUtils.addTooltipOnShift(components, this.getUnlocalizedName() + ".info.0");
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);

            if (stack.hasTagCompound()) {
                NBTTagCompound tag = stack.getTagCompound();

                if (tag != null) {
                    if (tag.getString("encryptionKey").equals(ACCESS_KEY)) {
                        player.setHeldItem(hand, ItemRegistry.EXE_STONE.getDefaultInstance());
                        player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                    } else {
                        player.sendStatusMessage(new TextComponentTranslation(this.getUnlocalizedName() + ".message"), true);
                        this.setEncryptionKey(stack, ACCESS_KEY, "");
                    }

                    return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
                }
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
