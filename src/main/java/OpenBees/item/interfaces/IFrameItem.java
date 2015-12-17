package OpenBees.item.interfaces;

public interface IFrameItem {

    public float getMutationModifier();

    public float getLifespanModifier();

    public float getFertilityModifier();

    public void damageItem(int damage);

    public boolean canBypassNocturnal();

    public boolean canBypassRain();

    public boolean canBypassFlowers();

    public boolean canBypassCave();

    public boolean canBypassBiome();
}
