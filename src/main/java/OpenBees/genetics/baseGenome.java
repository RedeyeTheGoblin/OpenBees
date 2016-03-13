package OpenBees.genetics;

import OpenBees.genetics.alleleHandlers.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class baseGenome implements IBeeGenome {

    private ISpecies species;
    private HashMap<Allele.AlleleTypes, Allele> alleles;

    public baseGenome(ISpecies species, HashMap<Allele.AlleleTypes, Allele> alleles) {
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
    public alleleIntHandler getWorkSpeed() {
        return (alleleIntHandler) alleles.get(Allele.AlleleTypes.WORKSPEED);
    }

    @Override
    public void setWorkSpeed(Allele allele) {
        alleles.put(Allele.AlleleTypes.WORKSPEED, allele);
    }

    @Override
    public alleleIntHandler getLifespan() {
        return (alleleIntHandler) alleles.get(Allele.AlleleTypes.LIFESPAN);
    }

    @Override
    public void setLifespan(Allele allele) {
        alleles.put(Allele.AlleleTypes.LIFESPAN, allele);
    }

    @Override
    public alleleIntHandler getPollination() {
        return (alleleIntHandler) alleles.get(Allele.AlleleTypes.POLLINATION);
    }

    @Override
    public void setPollination(Allele allele) {
        alleles.put(Allele.AlleleTypes.POLLINATION, allele);
    }

    @Override
    public alleleFlowerHandler getFlower() {
        return (alleleFlowerHandler) alleles.get(Allele.AlleleTypes.FLOWERS);
    }

    @Override
    public void setFlower(Allele allele) {
        alleles.put(Allele.AlleleTypes.FLOWERS, allele);
    }

    @Override
    public alleleClimateHandler getClimate() {
        return (alleleClimateHandler) alleles.get(Allele.AlleleTypes.CLIMATE);
    }

    @Override
    public void setClimate(Allele allele) {
        alleles.put(Allele.AlleleTypes.CLIMATE, allele);
    }

    @Override
    public alleleIntHandler getFertility() {
        return (alleleIntHandler) alleles.get(Allele.AlleleTypes.FERTILITY);
    }

    @Override
    public void setFertility(Allele allele) {
        alleles.put(Allele.AlleleTypes.FERTILITY, allele);
    }

    @Override
    public alleleIntHandler getTerritory() {
        return (alleleIntHandler) alleles.get(Allele.AlleleTypes.TERRITORY);
    }

    @Override
    public void setTerritory(Allele allele) {
        alleles.put(Allele.AlleleTypes.TERRITORY, allele);
    }

    @Override
    public alleleBooleanHandler getNocturnal() {
        return (alleleBooleanHandler) alleles.get(Allele.AlleleTypes.NOCTURNAL);
    }

    @Override
    public void setNocturnal(boolean isNocturnal) {
        if (isNocturnal) {
            alleles.put(Allele.AlleleTypes.NOCTURNAL, new Allele("Nocturnal.TRUE"));
        } else {
            alleles.put(Allele.AlleleTypes.NOCTURNAL, new Allele("Nocturnal.FALSE"));
        }
    }

    @Override
    public alleleBooleanHandler getCave() {
        return (alleleBooleanHandler) alleles.get(Allele.AlleleTypes.CAVE);
    }

    @Override
    public void setCave(boolean isCave) {
        if (isCave) {
            alleles.put(Allele.AlleleTypes.CAVE, new Allele("Nocturnal.TRUE"));
        } else {
            alleles.put(Allele.AlleleTypes.CAVE, new Allele("Nocturnal.FALSE"));
        }
    }

    @Override
    public alleleBooleanHandler getRain() {
        return (alleleBooleanHandler) alleles.get(Allele.AlleleTypes.RAIN);
    }

    @Override
    public void setRain(boolean isRain) {
        if (isRain) {
            alleles.put(Allele.AlleleTypes.RAIN, new Allele("Nocturnal.TRUE"));
        } else {
            alleles.put(Allele.AlleleTypes.RAIN, new Allele("Nocturnal.FALSE"));
        }
    }

    @Override
    public alleleEffectHandler getEffect() {
        return (alleleEffectHandler) alleles.get(Allele.AlleleTypes.EFFECT);
    }

    @Override
    public void setEffect(Allele allele) {
        alleles.put(Allele.AlleleTypes.EFFECT, allele);
    }

    @Override
    public Map<Allele.AlleleTypes, Allele> getRawGenome() {
        return Collections.unmodifiableMap(this.alleles);
    }

    @Override
    public boolean isIdentical(IBeeGenome compGenome) {
        if (this == compGenome) return true;
        if (!(compGenome instanceof baseGenome)) return false;

        baseGenome genome = (baseGenome) compGenome;

        if (!alleles.equals(genome.alleles)) return false;
        if (!species.equals(genome.species)) return false;

        return true;

    }
}
