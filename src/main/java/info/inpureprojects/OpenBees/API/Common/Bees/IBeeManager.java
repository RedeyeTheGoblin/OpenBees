package info.inpureprojects.OpenBees.API.Common.Bees;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IAlleleManager;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Map;

/**
 * Created by den on 8/6/2014.
 */
public interface IBeeManager {

    public void registerBeeForHive(ISpecies species, List<BiomeDictionary.Type> biomes);

    public IBeeLogic getCurrentLogic();

    public List<ISpecies> getSpeciesForBiome(BiomeGenBase biome);

    // Register a species. Make sure your tag is unique.
    public void registerSpecies(ISpecies species);

    // Takes an itemstack and gives you an IBee with the genome read into an easy to use form.
    public IBee convertStackToBee(ItemStack stack);

    // Gets the species registered with the given tag.
    public ISpecies getSpeciesByTag(String tag);

    // Gets the entire species map. This map is immutable, so no funny business.
    public Map<String, ISpecies> getSpeciesMap();

    // Returns a template bee with basic alleles for the given species.
    public IBee getTemplate(ISpecies species);

    // This will return an NBT tag ready for adding alleles. Attach to bee itemstack.
    public NBTTagCompound getBlankGenome();

    // This pretty much only exists for the creative tab icon.
    public ItemStack getBeeBySpeciesTag(String tag, Type type);

    public ItemStack getBeeBySpecies(ISpecies species, Type type);

    public IAlleleManager getAlleleManager();

    public static enum Type {
        // Shortcut enum for getting the bees.
        DRONE,
        PRINCESS,
        QUEEN;

        public ItemStack createStack() {
            switch (this.ordinal()) {
                case 0:
                    return OpenBeesAPI.getAPI().getCommonAPI().items.drone.getStack(1);
                case 1:
                    return OpenBeesAPI.getAPI().getCommonAPI().items.princess.getStack(1);
                case 2:
                    return OpenBeesAPI.getAPI().getCommonAPI().items.queen.getStack(1);
            }
            return null;
        }
    }

}
