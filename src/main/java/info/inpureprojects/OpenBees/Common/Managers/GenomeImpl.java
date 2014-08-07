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
        return null;
    }

    @Override
    public AlleleInt getLifespan() {
        return null;
    }

    @Override
    public AlleleInt getPollination() {
        return null;
    }

    @Override
    public AlleleFlower getFlower() {
        return null;
    }

    @Override
    public AlleleClimate getClimate() {
        return null;
    }

    @Override
    public AlleleInt getFertility() {
        return null;
    }

    @Override
    public AlleleInt getTerritory() {
        return null;
    }

    @Override
    public AlleleBoolean getNocturnal() {
        return null;
    }

    @Override
    public AlleleBoolean getCave() {
        return null;
    }

    @Override
    public AlleleBoolean getRain() {
        return null;
    }

    @Override
    public AlleleEffect getEffect() {
        return null;
    }
}
