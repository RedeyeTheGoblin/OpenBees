package info.inpureprojects.OpenBees.API.Common.Tools;

/**
 * Created by den on 8/8/2014.
 */
public interface IFrameItem {

    // For reference: 1.0f = double the current chance.
    public float getMutationModifer();

    // This is in bee-ticks. There are 45 bee-ticks in the lowest life-span.
    public float getLifespanModifier();

    public float getFertilityModifier();

    public void damageItem(int dmg);

    public boolean canBypassNocturnal();

    public boolean canBypassRain();

    public boolean canBypassFlowerRequirement();

    public boolean canBypassOutdoorRequirement();

    public boolean canBypassBiomeRequirement();

}
