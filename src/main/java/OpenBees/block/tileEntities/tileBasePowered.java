package OpenBees.block.tileEntities;


import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;


public abstract class tileBasePowered extends blockBaseTile implements IEnergyHandler, IEnergyInfo {

    protected EnergyStorage eStorage;

    protected  tileBasePowered (int size, int maxPower) {
        super(size);
        eStorage = new EnergyStorage(maxPower);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        eStorage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT (NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        eStorage.writeToNBT(nbt);
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
        return this.eStorage.getEnergyStored();
    }

    @Override
    public int getInfoMaxEnergyStored() {
        return this.eStorage.getMaxEnergyStored();
    }

    public EnergyStorage geteStorage() {
        return eStorage;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return eStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return eStorage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return eStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return eStorage.getMaxEnergyStored();
    }
}
