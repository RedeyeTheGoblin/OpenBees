package info.inpureprojects.OpenBees.Client;

import info.inpureprojects.OpenBees.Common.NeedsMovedToCore;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
@NeedsMovedToCore
public class ImageScanner {

    public static final PixelData target = new PixelData(-1, -1, -6075996);
    private ArrayList<PixelData> data = new ArrayList();

    public void load(String path) {
        try {
            this.loadImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(path)));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void loadImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int pixel = image.getRGB(j, i);
                data.add(new PixelData(j, i, pixel));
            }
        }
    }

    public void drawMeAMap(BufferedImage image) {
        BufferedImage copy = this.deepCopy(image);
        Graphics g = copy.getGraphics();
        this.loadImage(image);
        int index = 0;
        for (PixelData d : this.findTargets()) {
            g.drawString(String.valueOf(index++), d.getX(), d.getY());
        }
        try {
            ImageIO.write(copy, "png", new File("map.png"));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public List<PixelData> findTargets() {
        ArrayList<PixelData> find = new ArrayList();
        for (PixelData d : data) {
            if (d.isEqual(target)) {
                find.add(d);
            }
        }
        return find;
    }

    public static class PixelData {

        private int x;
        private int y;
        private int pixel;

        public PixelData(int x, int y, int pixel) {
            this.x = x;
            this.y = y;
            this.pixel = pixel;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getPixel() {
            return pixel;
        }

        public boolean isEqual(Object o) {
            if (this == o) return true;
            if (!(o instanceof PixelData)) return false;

            PixelData pixelData = (PixelData) o;

            if (pixel != pixelData.pixel) return false;

            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PixelData)) return false;

            PixelData pixelData = (PixelData) o;

            if (pixel != pixelData.pixel) return false;
            if (x != pixelData.x) return false;
            if (y != pixelData.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + pixel;
            return result;
        }
    }
}
