package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Tools.ModifierBlock;
import net.minecraft.block.Block;

/**
 * Created by den on 8/10/2014.
 */
public class ModifierBlockComparison extends ModifierBlock {

    public ModifierBlockComparison(Block block, int meta) {
        super(block, meta);
    }

    @Override
    public float getMutationModifer() {
        return 0;
    }

    @Override
    public int getLifespanModifier() {
        return 0;
    }

    @Override
    public void damageItem(int dmg) {

    }

    @Override
    public int getFertilityModifier() {
        return 0;
    }

    @Override
    public boolean canBypassNocturnal() {
        return false;
    }

    @Override
    public boolean canBypassRain() {
        return false;
    }

    @Override
    public boolean canBypassFlowerRequirement() {
        return false;
    }

    @Override
    public boolean canBypassOutdoorRequirement() {
        return false;
    }

    @Override
    public boolean canBypassBiomeRequirement() {
        return false;
    }
}
