package OpenBees.handler;

import OpenBees.block.modifierBlock;
import net.minecraft.block.Block;

public class modifierBlockComparison extends modifierBlock {

    public modifierBlockComparison(Block block, int meta)
    {
        super(block, meta);
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
    public void damageItem(int damage)
    {

    }

    @Override
    public float getFertilityModifier()
    {
        return 0;
    }

    @Override
    public boolean canBypassNocturnal()
    {
        return false;
    }

    @Override
    public boolean canBypassRain()
    {
        return false;
    }

    @Override
    public boolean canBypassFlowers()
    {
        return false;
    }

    @Override
    public boolean canBypassCave()
    {
        return false;
    }

    @Override
    public boolean canBypassBiome()
    {
        return false;
    }
}
