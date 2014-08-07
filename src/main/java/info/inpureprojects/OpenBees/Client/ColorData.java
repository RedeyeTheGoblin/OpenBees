package info.inpureprojects.OpenBees.Client;

/**
 * Created by den on 8/6/2014.
 */
public class ColorData {

    private int primary;
    private int secondary;

    public ColorData(int primary, int secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public int getPrimary() {
        return primary;
    }

    public int getSecondary() {
        return secondary;
    }
}
