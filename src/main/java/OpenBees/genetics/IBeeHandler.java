package OpenBees.genetics;

import OpenBees.block.modifierBlock;
import OpenBees.enums.typeEnum;
import OpenBees.handler.IalleleHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Map;

public interface IBeeHandler {

    public List<modifierBlock> getModifierBlocksNear(IBeeKeepingTile tile);

    public void registerModifierBlock(modifierBlock block);

    public modifierBlock getModifierBlock(Block block, int meta);

    /** Kinda don't want to use this method of doing it, but leaving commented out just incase it's needed
        in the future...
    public void registerMutation(String species1, String species2, String outcome, float chance);
    */

    public void registerMutation(ISpecies species1, ISpecies species2, ISpecies outcome, float chance);

    public void registerCustomMutation(beeMutation mutation);

    public List<beeMutation> getMutations(ISpecies species1, ISpecies species2);

    public IBeeLogic getCurrentLogic();

    public void registerBeeforHive(ISpecies species, List<BiomeDictionary.Type> biomes);

    public List<ISpecies> getSpeciesForBiome(BiomeGenBase biome);

    public void registerSpecies(ISpecies species);

    //Converts an itemstack into a genome-encoded bee :)
    public IBee convertStacktoBee(ItemStack stack);

    public ISpecies getSpeciesByTag(String tag);

    public Map<String, ISpecies> getSpeciesMap();

    public IBee getTemplate(ISpecies species);

    public NBTTagCompound getBlankGenome();

    public ItemStack getBeeBySpeciesTag(String tag, typeEnum.Types type);

    public ItemStack getBeeBySpecies(ISpecies species, typeEnum.Types type);

    public IalleleHandler getAlleleHandler();


}
