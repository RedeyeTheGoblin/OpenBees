package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Tools.ModifierBlock;
import net.minecraft.init.Blocks;

/**
 * Created by den on 8/10/2014.
 */
public class ModifierBlockTest extends ModifierBlock {

    public ModifierBlockTest() {
        super(Blocks.glowstone, 0);
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
    public int getFertilityModifier() {
        return 0;
    }

    @Override
    public void damageItem(int dmg) {

    }

    @Override
    public boolean canBypassNocturnal() {
        return true;
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
