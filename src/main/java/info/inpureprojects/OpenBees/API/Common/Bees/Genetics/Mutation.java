package info.inpureprojects.OpenBees.API.Common.Bees.Genetics;

import info.inpureprojects.OpenBees.API.Common.Bees.IBeeKeepingTile;

/**
 * Created by den on 8/10/2014.
 */
public class Mutation {

    private ISpecies species1;
    private ISpecies species2;
    private ISpecies outcome;
    private float chance;

    public Mutation(ISpecies species1, ISpecies species2, ISpecies outcome, float chance) {
        this.species1 = species1;
        this.species2 = species2;
        this.outcome = outcome;
        this.chance = chance;
    }

    public ISpecies getSpecies1() {
        return species1;
    }

    public ISpecies getSpecies2() {
        return species2;
    }

    public float getChance() {
        return chance;
    }

    public boolean areRequirementsMet(IBeeKeepingTile tile) {
        return true;
    }

    public ISpecies getOutcome() {
        return outcome;
    }

    public boolean isEqual(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mutation)) return false;

        Mutation mutation = (Mutation) o;

        if (!species1.equals(mutation.species1)) return false;
        if (!species2.equals(mutation.species2)) return false;

        return true;
    }
}
