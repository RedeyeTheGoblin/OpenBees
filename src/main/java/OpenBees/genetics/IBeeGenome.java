package OpenBees.genetics;

import OpenBees.genetics.alleleHandlers.*;

import java.util.Map;

public interface IBeeGenome {

    public ISpecies getSpecies();

    public void setSpecies(ISpecies species);

    public alleleIntHandler getWorkSpeed();

    public void setWorkSpeed(Allele allele);

    public alleleIntHandler getLifespan();

    public void setLifespan(Allele allele);

    public alleleIntHandler getPollination();

    public void setPollination(Allele allele);

    public alleleFlowerHandler getFlower();

    public void setFlower(Allele allele);

    public alleleClimateHandler getClimate();

    public void setClimate(Allele allele);

    public alleleIntHandler getFertility();

    public void setFertility(Allele allele);

    public alleleIntHandler getTerritory();

    public void setTerritory(Allele allele);

    public alleleBooleanHandler getNocturnal();

    public void setNocturnal(boolean isNocturnal);

    public alleleBooleanHandler getCave();

    public void setCave(boolean isCave);

    public alleleBooleanHandler getRain();

    public void setRain(boolean isRain);

    public alleleEffectHandler getEffect();

    public void setEffect(Allele allele);

    public Map<Allele.AlleleTypes, Allele> getRawGenome();

    public boolean isIdentical(IBeeGenome compGenome);

}
