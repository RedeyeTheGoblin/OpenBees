package info.inpureprojects.OpenBees.Common.WorldGen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.ModuleOpenBees;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by den on 8/11/2014.
 */
public class WorldGenHives implements IWorldGenerator {

    private static int amountPerChunk = 1;
    private static List<ItemStack> replaceable = new ArrayList();
    private static List<ItemStack> canSitOn = new ArrayList();

    static {
        replaceable.addAll(OreDictionary.getOres("treeLeaves"));
        replaceable.add(new ItemStack(Blocks.snow_layer, 1, 0));
        canSitOn.add(new ItemStack(Blocks.grass, 1, 0));
        canSitOn.add(new ItemStack(Blocks.sand, 1, 0));
    }

    public WorldGenHives() {
    }

    public static void init() {
        GameRegistry.registerWorldGenerator(new WorldGenHives(), 9999);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        for (int i = 0; i < amountPerChunk; i++) {
            int randX = this.makeRandomCoord(chunkX, random, 16);
            int randY = random.nextInt(90 - 62) + 62;
            int randZ = this.makeRandomCoord(chunkZ, random, 16);
            // Landed in air.
            if (world.isAirBlock(randX, randY, randZ)) {
                // Is something under us?
                if (!world.isAirBlock(randX, randY - 1, randZ)) {
                    // What is it?
                    Block thing = world.getBlock(randX, randY - 1, randZ);
                    int meta = world.getBlockMetadata(randX, randY - 1, randZ);
                    for (ItemStack stack : canSitOn) {
                        Block b = Block.getBlockFromItem(stack.getItem());
                        boolean disregardMeta = stack.getItemDamage() == OreDictionary.WILDCARD_VALUE;
                        if (b == thing && (meta == stack.getItemDamage() || disregardMeta)) {
                            world.setBlock(randX, randY, randZ, OpenBeesAPI.getAPI().getCommonAPI().blocks.beehive.getBlock());
                            world.setBlockMetadataWithNotify(randX, randY, randZ, 0, 3);
                            break;
                        }
                    }
                }
            } else {
                // Can we replace it?
                Block thing = world.getBlock(randX, randY, randZ);
                int meta = world.getBlockMetadata(randX, randY, randZ);
                for (ItemStack stack : replaceable) {
                    Block b = Block.getBlockFromItem(stack.getItem());
                    boolean disregardMeta = stack.getItemDamage() == OreDictionary.WILDCARD_VALUE;
                    if (b == thing && (meta == stack.getItemDamage() || disregardMeta)) {
                        ModuleOpenBees.proxy.print("Replacing block: " + thing.getUnlocalizedName() + "." + String.valueOf(meta) + " with beehive.");
                        world.setBlock(randX, randY, randZ, OpenBeesAPI.getAPI().getCommonAPI().blocks.beehive.getBlock());
                        world.setBlockMetadataWithNotify(randX, randY, randZ, 0, 3);
                        break;
                    }
                }
            }
        }
    }

    private int makeRandomCoord(int chunkCoord, Random random, int size) {
        return (chunkCoord * 16) + random.nextInt(size);
    }
}
