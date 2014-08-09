package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleClimate;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleEffect;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleFlower;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleBoolean;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;

import java.util.Map;

/**
 * Created by den on 8/6/2014.
 */
public interface IBeeGenome {

    public ISpecies getSpecies();

    public void setSpecies(ISpecies species);

    public AlleleInt getWorkspeed();

    public void setWorkspeed(Allele allele);

    public AlleleInt getLifespan();

    public void setLifespan(Allele allele);

    public AlleleInt getPollination();

    public void setPollination(Allele allele);

    public AlleleFlower getFlower();

    public void setFlower(Allele allele);

    public AlleleClimate getClimate();

    public void setClimate(Allele allele);

    public AlleleInt getFertility();

    public void setFertility(Allele allele);

    public AlleleInt getTerritory();

    public void setTerritory(Allele allele);

    public AlleleBoolean getNocturnal();

    public void setNocturnal(boolean isNocturnal);

    public AlleleBoolean getCave();

    public void setCave(boolean isCave);

    public AlleleBoolean getRain();

    public void setRain(boolean isRain);

    public AlleleEffect getEffect();

    public void setEffect(Allele allele);

    public Map<Allele.AlleleTypes, Allele> getRawGenome();

}
