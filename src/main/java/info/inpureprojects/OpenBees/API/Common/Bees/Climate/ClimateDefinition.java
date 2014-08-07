package info.inpureprojects.OpenBees.API.Common.Bees.Climate;

import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by den on 8/6/2014.
 */
public class ClimateDefinition {

    private String tag;
    private BiomeDictionary.Type type;

    public ClimateDefinition(String tag, BiomeDictionary.Type type) {
        this.tag = tag;
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public BiomeDictionary.Type getType() {
        return type;
    }
}
