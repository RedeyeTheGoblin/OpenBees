package OpenBees.handler;

import OpenBees.block.modifierBlock;
import OpenBees.enums.typeEnum;
import OpenBees.genetics.*;
import OpenBees.item.interfaces.IFrameItem;
import cofh.lib.util.position.BlockPosition;
import cofh.lib.world.biome.BiomeDictionaryArbiter;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.*;

public class beeHandler implements IBeeHandler {

    private HashMap<String, ISpecies> beeSpecies = new HashMap();
    private HashMap<String, IBee> templates = new HashMap();
    private IalleleHandler alleles = new alleleHandler();
    private IBeeLogic beeLogic = new beeLogicHandler();
    private HashMap<BiomeDictionary.Type, ArrayList<ISpecies>> hiveMap = new HashMap();
    private ArrayList<beeMutation> mutations = new ArrayList();
    private HashMap<String, modifierBlock> modifierBlocks = new HashMap();

    public void registerMutation(String species1, String species2, String outcome, float chance) {
        this.registerMutation(this.getSpeciesByTag(species1), this.getSpeciesByTag(species2), this.getSpeciesByTag(outcome), chance);
    }

    @Override
    public void registerModifierBlock(modifierBlock block) {
        modifierBlocks.put(block.getTag(), block);
    }

    @Override
    public modifierBlock getModifierBlock(Block block, int meta) {
        modifierBlockComparison comp = new modifierBlockComparison(block, meta);
        if (modifierBlocks.containsKey(comp.getTag())) {
            return modifierBlocks.get(comp.getTag());
        }
        return null;
    }

    @Override
    public void registerCustomMutation(beeMutation mutation) {
        mutations.add(mutation);
    }

    @Override
    public void registerMutation(ISpecies species1, ISpecies species2, ISpecies outcome, float chance) {
        mutations.add(new beeMutation(species1, species2, outcome, chance));
    }

    @Override
    public List<beeMutation> getMutations(ISpecies species1, ISpecies species2) {
        beeMutation testMutation = new beeMutation(species1, species2, null, 0.0f);
        List<beeMutation> potentialMutations = new ArrayList();
        for (beeMutation muts : mutations) {
            if (muts.isMatch(testMutation)) {
                potentialMutations.add(muts);
            }
        }
        return Collections.unmodifiableList(potentialMutations);
    }

    @Override
    public List<ISpecies> getSpeciesForBiome(BiomeGenBase biome) {
        ArrayList<ISpecies> bees = new ArrayList();
        for (BiomeDictionary.Type type : BiomeDictionary.getTypesForBiome(biome)) {
            if (hiveMap.containsKey(type)) {
                bees.addAll(hiveMap.get(type));
            }
        }
        return Collections.unmodifiableList(bees);
    }

    @Override
    public void registerBeeforHive(ISpecies species, List<BiomeDictionary.Type> biomes) {
        for (BiomeDictionary.Type type : biomes) {
            if (!hiveMap.containsKey(type)) {
                hiveMap.put(type, new ArrayList());
            }
            hiveMap.get(type).add(species);
        }
    }

    @Override
    public void registerSpecies(ISpecies species) {
        beeSpecies.put(species.getTag(), species);
        templates.put(species.getTag(), new baseBee(species.generateGenericGenome()));
    }

    @Override
    public IBeeLogic getCurrentLogic() {
        return this.beeLogic;
    }

    @Override
    public ISpecies getSpeciesByTag(String tag) {
        return beeSpecies.get(tag);
    }

    @Override
    public IBee convertStacktoBee(ItemStack stack) {
       return new baseBee(stack.getTagCompound());
    }

    @Override
    public Map<String, ISpecies> getSpeciesMap() {
        return Collections.unmodifiableMap(beeSpecies);
    }

    @Override
    public IBee getTemplate(ISpecies species) {
        return templates.get(species.getTag());
    }

    @Override
    public NBTTagCompound getBlankGenome() {
        return new NBTTagCompound();
    }

    @Override
    public ItemStack getBeeBySpeciesTag(String tag, typeEnum.Types type) {
        return this.getBeeBySpecies(this.getSpeciesByTag(tag), type);
    }

    @Override
    public ItemStack getBeeBySpecies(ISpecies species, typeEnum.Types type) {
        ItemStack bee = type.createStack();
        IBee ibee = this.getTemplate(species);
        bee.setTagCompound(ibee.getNBT());
        return bee;
    }

    @Override
    public List<modifierBlock> getModifierBlocksNear(IBeeKeepingTile tile) {
        List<modifierBlock> blocks = new ArrayList();
        for (BlockPosition blockPos : tile.getSurroundingBlocks()) {
            if (blockPos.blockExists(tile.getWorld())) {
                modifierBlock block = getModifierBlock(tile.getWorld().getBlock(blockPos.x, blockPos.y, blockPos.z), tile.getWorld().getBlockMetadata(blockPos.x, blockPos.y, blockPos.z));
                if (block != null) {
                    blocks.add(block);
                }
            }
        }

        return Collections.unmodifiableList(blocks);
    }

    @Override
    public IalleleHandler getAlleleHandler() {
        return this.alleles;
    }
}
