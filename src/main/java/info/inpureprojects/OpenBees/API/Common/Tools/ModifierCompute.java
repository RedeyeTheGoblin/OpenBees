package info.inpureprojects.OpenBees.API.Common.Tools;

import net.minecraft.block.Block;

import java.util.List;

/**
 * Created by den on 8/10/2014.
 */
public class ModifierCompute extends ModifierBlock{

    private boolean rain = false;
    private boolean outdoor = false;
    private boolean biome = false;
    private boolean flower = false;
    private boolean night = false;
    private int totalFertMod = 0;

    public ModifierCompute(List<IFrameItem> modifiers) {
        super(null, -1);
        for (IFrameItem b : modifiers){
            if (b.canBypassOutdoorRequirement()){
                outdoor = true;
            }
            if (b.canBypassBiomeRequirement()){
                biome = true;
            }
            if (b.canBypassFlowerRequirement()){
                flower = true;
            }
            if (b.canBypassNocturnal()){
                night = true;
            }
            if (b.canBypassRain()){
                rain = true;
            }
            totalFertMod+=b.getFertilityModifier();
        }
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
        return this.totalFertMod;
    }

    @Override
    public void damageItem(int dmg) {

    }

    @Override
    public boolean canBypassNocturnal() {
        return night;
    }

    @Override
    public boolean canBypassRain() {
        return rain;
    }

    @Override
    public boolean canBypassFlowerRequirement() {
        return flower;
    }

    @Override
    public boolean canBypassOutdoorRequirement() {
        return outdoor;
    }

    @Override
    public boolean canBypassBiomeRequirement() {
        return biome;
    }
}
