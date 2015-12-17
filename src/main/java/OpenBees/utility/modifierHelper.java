package OpenBees.utility;

import OpenBees.block.modifierBlock;
import OpenBees.item.interfaces.IFrameItem;

import java.util.List;

public class modifierHelper extends modifierBlock {

    private boolean rain = false;
    private boolean outdoor = false;
    private boolean biome = false;
    private boolean flower = false;
    private boolean night = false;
    private float totalFertilityMod = 0;

    public modifierHelper(List<IFrameItem> modifiers)
    {
        super(null, -1);
        for (IFrameItem frame : modifiers)
        {
            if (frame.canBypassCave())
            {
                outdoor = true;
            }

            if (frame.canBypassBiome())
            {
                biome = true;
            }

            if (frame.canBypassFlowers())
            {
                flower = true;
            }

            if (frame.canBypassNocturnal())
            {
                night = true;
            }

            totalFertilityMod += frame.getFertilityModifier();
        }
    }

    @Override
    public float getMutationModifier()
    {
        return 0;
    }

    @Override
    public float getLifespanModifier()
    {
        return 0;
    }

    @Override
    public float getFertilityModifier()
    {
        return this.totalFertilityMod;
    }

    @Override
    public void damageItem(int damage)
    {

    }

    @Override
    public boolean canBypassNocturnal()
    {
        return night;
    }

    @Override
    public boolean canBypassRain()
    {
        return rain;
    }

    @Override
    public boolean canBypassFlowers()
    {
        return flower;
    }

    @Override
    public boolean canBypassCave()
    {
        return outdoor;
    }

    @Override
    public boolean canBypassBiome()
    {
        return biome;
    }

}
