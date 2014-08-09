package info.inpureprojects.OpenBees.Client;

import junit.framework.TestCase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ImageScannerTest extends TestCase {

    public void testScan() throws Exception {
        ImageScanner i = new ImageScanner();
        try{
            BufferedImage buf = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("gui_apiary_findarrow.png"));
            i.loadImage(buf);
            for (ImageScanner.PixelData d : i.findTargets()){
                System.out.println("X: " + d.getX() + ", Y: " + d.getY());
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    public void testMap() throws Exception{
        ImageScanner i = new ImageScanner();
        try{
            BufferedImage buf = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("gui_apiary.png"));
            i.drawMeAMap(buf);
            new File("map.png").deleteOnExit();
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}