package OpenBees.item;

import OpenBees.handler.creativeTabHandler;
import cofh.api.block.IDismantleable;
import cofh.lib.util.helpers.BlockHelper;
import cofh.lib.util.helpers.ServerHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Set;


public abstract class itemToolBase extends ItemTool {

    protected itemToolBase(String unloc, ToolMaterial material, Set set, String toolClass, int level) {
        super(2.0f, material, set);
        this.setUnlocalizedName(unloc);
        this.setHarvestLevel(toolClass, level);
        this.setCreativeTab(creativeTabHandler.creativeTabBees);
    }

    @Override
    public abstract IIcon getIcon(ItemStack stack, int pass);

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add("Durability: " + String.valueOf(this.toolMaterial.getMaxUses() - stack.getItemDamage()) + "\\" + String.valueOf(this.toolMaterial.getMaxUses()));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List tools) {
        tools.add(new ItemStack(this, 1, 0));
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 1;
    }

    //Lemming... den stole this from Thermal Expansion. He says sorry.
    //No idea what it does, though.
    public boolean onItemUseFirst(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3) {
        Block localBlock = paramWorld.getBlock(paramInt1, paramInt2, paramInt3);
        if ((ServerHelper.isServerWorld(paramWorld)) && (paramEntityPlayer.isSneaking()) && ((localBlock instanceof IDismantleable)) && (((IDismantleable) localBlock).canDismantle(paramEntityPlayer, paramWorld, paramInt1, paramInt2, paramInt3))) {
            ((IDismantleable) localBlock).dismantleBlock(paramEntityPlayer, paramWorld, paramInt1, paramInt2, paramInt3, false);
            return true;
        }
        if (BlockHelper.canRotate(localBlock)) {
            if (paramEntityPlayer.isSneaking()) {
                paramWorld.setBlockMetadataWithNotify(paramInt1, paramInt2, paramInt3, BlockHelper.rotateVanillaBlockAlt(paramWorld, localBlock, paramInt1, paramInt2, paramInt3), 3);
                paramWorld.playSoundEffect(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, localBlock.stepSound.getBreakSound(), 1.0F, 0.6F);
            } else {
                paramWorld.setBlockMetadataWithNotify(paramInt1, paramInt2, paramInt3, BlockHelper.rotateVanillaBlock(paramWorld, localBlock, paramInt1, paramInt2, paramInt3), 3);
                paramWorld.playSoundEffect(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, localBlock.stepSound.getBreakSound(), 1.0F, 0.8F);
            }
            return ServerHelper.isServerWorld(paramWorld);
        }
        if ((!paramEntityPlayer.isSneaking()) && (localBlock != null) && (localBlock.rotateBlock(paramWorld, paramInt1, paramInt2, paramInt3, ForgeDirection.getOrientation(paramInt4)))) {
            paramEntityPlayer.swingItem();
            return ServerHelper.isServerWorld(paramWorld);
        }
        return false;
    }
}
