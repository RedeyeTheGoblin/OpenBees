package info.inpureprojects.OpenBees.Common.Blocks.Tiles;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by den on 8/16/2014.
 */
public abstract class TileBasePoweredTank extends TileBaseTank implements IEnergyHandler, IEnergyInfo {

    protected EnergyStorage storage;

    protected TileBasePoweredTank(int size, int tankSize, int canSlot, int maxPower) {
        super(size, tankSize, canSlot);
        storage = new EnergyStorage(maxPower);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    @Override
    public int getInfoEnergyPerTick() {
        return 0;
    }

    @Override
    public int getInfoMaxEnergyPerTick() {
        return 12;
    }

    @Override
    public int getInfoEnergyStored() {
        return this.storage.getEnergyStored();
    }

    @Override
    public int getInfoMaxEnergyStored() {
        return this.storage.getMaxEnergyStored();
    }

    public EnergyStorage getStorage() {
        return storage;
    }

    /* IEnergyHandler */
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
    }
}
