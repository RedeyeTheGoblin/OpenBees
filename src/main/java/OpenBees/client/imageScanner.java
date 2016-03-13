package OpenBees.client;

import OpenBees.utility.logHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.*;
import java.util.List;

public class imageScanner {

    public static final pixelData target = new pixelData(-1, -1, -6075996);
    private ArrayList<pixelData> data = new ArrayList();

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
                data.add(new pixelData(j, i, pixel));
            }
        }
    }

    public void drawMeAMap(BufferedImage image) {
        BufferedImage copy = this.deepCopy(image);
        Graphics graphics = copy.getGraphics();
        this.loadImage(image);
        int index = 0;

        for (pixelData data: this.findTargets()) {
            graphics.drawString(String.valueOf(index++), data.getX(), data.getY());
        }

        try {
            ImageIO.write(copy, "png", new File("map.png"));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public BufferedImage deepCopy(BufferedImage buffImg) {
        ColorModel model = buffImg.getColorModel();
        boolean isAlphaPremultiplied = model.isAlphaPremultiplied();
        WritableRaster raster = buffImg.copyData(null);
        return new BufferedImage(model, raster, isAlphaPremultiplied, null);
    }

    public List<pixelData> findTargets() {
        ArrayList<pixelData> find = new ArrayList();

        for (pixelData pix : data) {
            if (pix.isEqual(target)) {
                find.add(pix);
            }
        }
        return find;
    }
}
