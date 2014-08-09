package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleClimate;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleEffect;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.AlleleFlower;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleBoolean;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleInt;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IBeeGenome;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public void setSpecies(ISpecies species) {
        this.species = species;
    }

    @Override
    public AlleleInt getWorkspeed() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.WORKSPEED);
    }

    @Override
    public void setWorkspeed(Allele allele) {
        alleles.put(Allele.AlleleTypes.WORKSPEED, allele);
    }

    @Override
    public AlleleInt getLifespan() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.LIFESPAN);
    }

    @Override
    public void setLifespan(Allele allele) {
        this.alleles.put(Allele.AlleleTypes.LIFESPAN, allele);
    }

    @Override
    public AlleleInt getPollination() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.POLLINATION);
    }

    @Override
    public void setPollination(Allele allele) {
        this.alleles.put(Allele.AlleleTypes.POLLINATION, allele);
    }

    @Override
    public AlleleFlower getFlower() {
        return (AlleleFlower) alleles.get(Allele.AlleleTypes.FLOWER);
    }

    @Override
    public void setFlower(Allele allele) {
        this.alleles.put(Allele.AlleleTypes.FLOWER, allele);
    }

    @Override
    public AlleleClimate getClimate() {
        return (AlleleClimate) alleles.get(Allele.AlleleTypes.CLIMATE);
    }

    @Override
    public void setClimate(Allele allele) {
        this.alleles.put(Allele.AlleleTypes.CLIMATE, allele);
    }

    @Override
    public AlleleInt getFertility() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.FERTILITY);
    }

    @Override
    public void setFertility(Allele allele) {
        this.alleles.put(Allele.AlleleTypes.FERTILITY, allele);
    }

    @Override
    public AlleleInt getTerritory() {
        return (AlleleInt) alleles.get(Allele.AlleleTypes.TERRITORY);
    }

    @Override
    public void setTerritory(Allele allele) {
        this.alleles.put(Allele.AlleleTypes.TERRITORY, allele);
    }

    @Override
    public AlleleBoolean getNocturnal() {
        return (AlleleBoolean) alleles.get(Allele.AlleleTypes.NOCTURNAL);
    }

    @Override
    public void setNocturnal(boolean isNocturnal) {
        this.alleles.put(Allele.AlleleTypes.NOCTURNAL, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getAlleleManager().getAlleleByTag("openbees|Boolean" + String.valueOf(isNocturnal).toUpperCase()));
    }

    @Override
    public AlleleBoolean getCave() {
        return (AlleleBoolean) alleles.get(Allele.AlleleTypes.CAVE);
    }

    @Override
    public void setCave(boolean isCave) {
        this.alleles.put(Allele.AlleleTypes.CAVE, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getAlleleManager().getAlleleByTag("openbees|Boolean" + String.valueOf(isCave).toUpperCase()));
    }

    @Override
    public AlleleBoolean getRain() {
        return (AlleleBoolean) alleles.get(Allele.AlleleTypes.RAIN);
    }

    @Override
    public void setRain(boolean isRain) {
        this.alleles.put(Allele.AlleleTypes.RAIN, OpenBeesAPI.getAPI().getCommonAPI().beeManager.getAlleleManager().getAlleleByTag("openbees|Boolean" + String.valueOf(isRain).toUpperCase()));
    }

    @Override
    public AlleleEffect getEffect() {
        return (AlleleEffect) alleles.get(Allele.AlleleTypes.EFFECT);
    }

    @Override
    public void setEffect(Allele allele) {
        this.alleles.put(Allele.AlleleTypes.EFFECT, allele);
    }

    @Override
    public Map<Allele.AlleleTypes, Allele> getRawGenome() {
        return Collections.unmodifiableMap(this.alleles);
    }
}
