package OpenBees.genetics;

import java.util.Locale;

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

    public void setCanBeInherited(boolean canBeInheritedChange) {
        this.canBeInherited = canBeInheritedChange;
    }

    @Override
    public String toString() {
        return "Allele{" +
                "tag='" + tag + '\'' +
                '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Allele)) return false;

        Allele allele = (Allele) obj;

        if (canBeInherited != allele.canBeInherited) return false;
        if (!tag.equals(allele.tag)) return false;
        if (!unloc.equals(allele.unloc)) return false;

        return true;

    }

    public static enum AlleleTypes {
        //This includes all of the alleles present in a bee genome
        WORKSPEED,
        LIFESPAN,
        POLLINATION,
        FLOWERS,
        CLIMATE,
        FERTILITY,
        EFFECT,
        NOCTURNAL,
        CAVE,
        RAIN,
        TERRITORY;

    }
}
