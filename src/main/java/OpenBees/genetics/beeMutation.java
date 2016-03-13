package OpenBees.genetics;

public class beeMutation {

    private ISpecies species1;
    private ISpecies species2;
    private ISpecies outcome;
    private float chance;

    public beeMutation(ISpecies species1, ISpecies species2, ISpecies outcome, float chance) {
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

    public ISpecies getOutcome() {
        return outcome;
    }

    public float getChance() {
        return chance;
    }

    public boolean meetsRequirements(IBeeKeepingTile tile) {
        return true;
    }

    public boolean isMatch(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof beeMutation)) return false;

        beeMutation mutation = (beeMutation) obj;

        if (!species1.equals(mutation.species1)) return false;
        if (!species2.equals(mutation.species2)) return false;

        return true;
    }

}
