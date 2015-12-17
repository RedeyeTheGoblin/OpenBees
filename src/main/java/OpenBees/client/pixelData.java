package OpenBees.client;

public class pixelData {

    private int x;
    private int y;
    private int pixel;

    public pixelData(int x, int y, int pixel)
    {
        this.x = x;
        this.y = y;
        this.pixel = pixel;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getPixel()
    {
        return pixel;
    }

    public boolean isEqual(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof pixelData)) return true;

        pixelData pixelData = (pixelData) obj;

        if (pixel != pixelData.pixel) return false;

        return true;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof pixelData)) return false;

        pixelData testPixelData = (pixelData) obj;

        if (pixel != testPixelData.pixel) return false;
        if (x != testPixelData.x) return false;
        if (y != testPixelData.y) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + pixel;
        return result;
    }
}
