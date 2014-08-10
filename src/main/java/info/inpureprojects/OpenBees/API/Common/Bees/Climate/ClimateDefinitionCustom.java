package info.inpureprojects.OpenBees.API.Common.Bees.Climate;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by den on 8/9/2014.
 */
public abstract class ClimateDefinitionCustom extends ClimateDefinition {

    public ClimateDefinitionCustom(String tag) {
        super(tag, null);
    }

    @Override
    public abstract boolean isBiomeCompatible(BiomeGenBase biome);
}
