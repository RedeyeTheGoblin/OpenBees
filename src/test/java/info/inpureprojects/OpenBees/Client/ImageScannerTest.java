package info.inpureprojects.OpenBees.Client;

import junit.framework.TestCase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageScannerTest extends TestCase {

    public void testScan() throws Exception {
        ImageScanner i = new ImageScanner();
        try{
            BufferedImage buf = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("mappingTest.png"));
            i.loadImage(buf);
            for (ImageScanner.PixelData d : i.findTargets()){
                System.out.println("X: " + d.getX() + ", Y: " + d.getY());
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}