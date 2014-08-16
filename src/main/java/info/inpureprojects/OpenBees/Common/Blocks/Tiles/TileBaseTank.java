package info.inpureprojects.OpenBees.Common.Blocks.Tiles;

import cofh.lib.util.helpers.ServerHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by den on 8/16/2014.
 */
public abstract class TileBaseTank extends TileBase implements IFluidHandler {

    protected FluidTank tank;
    protected int canSlot;

    public TileBaseTank(int size, int tankSize, int canSlot) {
        super(size);
        this.canSlot = canSlot;
        tank = new FluidTank(tankSize);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (ServerHelper.isClientWorld(this.worldObj)) {
            return;
        }
        if (this.getStackInSlot(canSlot) != null) {
            if (FluidContainerRegistry.isFilledContainer(this.getStackInSlot(canSlot))) {
                FluidStack f = FluidContainerRegistry.getFluidForFilledItem(this.getStackInSlot(canSlot));
                if (this.canFill(ForgeDirection.UNKNOWN, f.getFluid())) {
                    if (this.fill(ForgeDirection.UNKNOWN, f, false) == FluidContainerRegistry.getContainerCapacity(this.getStackInSlot(canSlot))) {
                        this.fill(ForgeDirection.UNKNOWN, f, true);
                        if (FluidContainerRegistry.isBucket(this.getStackInSlot(canSlot))) {
                            this.setInventorySlotContents(canSlot, FluidContainerRegistry.EMPTY_BUCKET.copy());
                        } else {
                            this.decrStackSize(canSlot, 1);
                        }
                    }
                }
            }
        }
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
    }

    /* IFluidHandler */
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{tank.getInfo()};
    }

}
