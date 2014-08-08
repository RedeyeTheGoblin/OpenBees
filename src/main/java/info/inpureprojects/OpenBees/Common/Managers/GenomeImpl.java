package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleClimate;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleEffect;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleFlower;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleBoolean;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IBeeGenome;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;

import java.util.HashMap;

/**
 * Created by den on 8/6/2014.
 */
public class GenomeImpl implements IBeeGenome {

    private ISpecies species;
    private HashMap<Allele.AlleleTypes, Allele> alleles;

    public GenomeImpl(ISpecies species, HashMap<Allele.AlleleTypes, Allele> alleles) {
        this.species = species;
        this.alleles = alleles;
    }

    @Override
    public ISpecies getSpecies() {
        return this.species;
    }

    @Override
    public AlleleInt getWorkspeed() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.WORKSPEED);
    }

    @Override
    public AlleleInt getLifespan() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.LIFESPAN);
    }

    @Override
    public AlleleInt getPollination() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.POLLINATION);
    }

    @Override
    public AlleleFlower getFlower() {
        return (AlleleFlower) alleles.get(Allele.AlleleTypes.FLOWER);
    }

    @Override
    public AlleleClimate getClimate() {
        return (AlleleClimate) alleles.get(Allele.AlleleTypes.CLIMATE);
    }

    @Override
    public AlleleInt getFertility() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.FERTILITY);
    }

    @Override
    public AlleleInt getTerritory() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.TERRITORY);
    }

    @Override
    public AlleleBoolean getNocturnal() {
        return (AlleleBoolean) alleles.get(Allele.AlleleTypes.NOCTURNAL);
    }

    @Override
    public AlleleBoolean getCave() {
        return (AlleleBoolean) alleles.get(Allele.AlleleTypes.CAVE);
    }

    @Override
    public AlleleBoolean getRain() {
        return (AlleleBoolean) alleles.get(Allele.AlleleTypes.RAIN);
    }

    @Override
    public AlleleEffect getEffect() {
        return (AlleleEffect) alleles.get(Allele.AlleleTypes.EFFECT);
    }
}
