package info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles;

/**
 * Created by den on 8/6/2014.
 */
public class Allele {

    protected String tag;
    protected String unloc;
    protected boolean canBeInherited = true;

    public Allele(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setCanBeInherited(boolean canBeInherited) {
        this.canBeInherited = canBeInherited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Allele)) return false;

        Allele allele = (Allele) o;

        if (canBeInherited != allele.canBeInherited) return false;
        if (!tag.equals(allele.tag)) return false;
        if (!unloc.equals(allele.unloc)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag.hashCode();
        result = 31 * result + unloc.hashCode();
        result = 31 * result + (canBeInherited ? 1 : 0);
        return result;
    }

    public static enum AlleleTypes {

        // This is a general map of the genome.
        WORKSPEED, // int
        LIFESPAN, // int
        POLLINATION, // int
        FLOWER, // flower
        CLIMATE, // climate
        FERTILITY, // int
        EFFECT, // effect
        NOCTURNAL, // boolean
        CAVE, // boolean
        RAIN, // boolean
        TERRITORY; // int

    }
}
