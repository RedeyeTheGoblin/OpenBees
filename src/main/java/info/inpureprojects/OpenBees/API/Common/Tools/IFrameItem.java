package info.inpureprojects.OpenBees.API.Common.Tools;

/**
 * Created by den on 8/8/2014.
 */
public interface IFrameItem {

    public float getMutationModifer();

    public float getLifespanModifier();

    public float getFertilityModifier();

    public void damageItem(int dmg);

    public boolean canBypassNocturnal();

    public boolean canBypassRain();

    public boolean canBypassFlowerRequirement();

    public boolean canBypassOutdoorRequirement();

    public boolean canBypassBiomeRequirement();

}
