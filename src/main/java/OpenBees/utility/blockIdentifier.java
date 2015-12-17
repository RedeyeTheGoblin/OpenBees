package OpenBees.utility;


public class blockIdentifier {

    private String id;
    private int meta;

    public blockIdentifier(String id, int meta)
    {
        this.id = id;
        this.meta = meta;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof blockIdentifier)) return false;

        blockIdentifier that = (blockIdentifier) obj;

        if (meta != that.meta) return false;
        if (!id.equals(that.id)) return false;

        return true;
    }
}
