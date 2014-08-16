package info.inpureprojects.OpenBees.Client;

import junit.framework.TestCase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageScannerTest extends TestCase {

    private boolean skipMap = true;

    public void testScan() throws Exception {
        ImageScanner i = new ImageScanner();
        try {
            BufferedImage buf = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("mappingTest.png"));
            i.loadImage(buf);
            for (ImageScanner.PixelData d : i.findTargets()) {
                System.out.println("X: " + d.getX() + ", Y: " + d.getY());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void testMap() throws Exception {
        if (skipMap){
            return;
        }
        ImageScanner i = new ImageScanner();
        try {
            BufferedImage buf = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("gui_carpenter_map.png"));
            i.drawMeAMap(buf);
            //new File("map.png").deleteOnExit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}