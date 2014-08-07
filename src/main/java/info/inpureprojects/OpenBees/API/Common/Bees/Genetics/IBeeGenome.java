package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleClimate;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleEffect;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleFlower;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleBoolean;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;

/**
 * Created by den on 8/6/2014.
 */
public interface IBeeGenome {

    public ISpecies getSpecies();

    public AlleleInt getWorkspeed();

    public AlleleInt getLifespan();

    public AlleleInt getPollination();

    public AlleleFlower getFlower();

    public AlleleClimate getClimate();

    public AlleleInt getFertility();

    public AlleleInt getTerritory();

    public AlleleBoolean getNocturnal();

    public AlleleBoolean getCave();

    public AlleleBoolean getRain();

    public AlleleEffect getEffect();

}
