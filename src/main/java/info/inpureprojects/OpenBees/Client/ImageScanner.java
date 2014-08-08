package info.inpureprojects.OpenBees.Client;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
public class ImageScanner {

    public static final PixelData target = new PixelData(-1, -1, -6075996);
    private ArrayList<PixelData> data = new ArrayList();

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

    public List<PixelData> findTargets() {
        ArrayList<PixelData> find = new ArrayList();
        for (PixelData d : data) {
            if (d.equals(target)) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PixelData)) return false;

            PixelData pixelData = (PixelData) o;

            if (pixel != pixelData.pixel) return false;

            return true;
        }

    }
}
