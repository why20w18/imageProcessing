package guiOperations.rw;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import guiOperations.ReadFace;

/**
 *
 * @author bw
 */
public class Reader implements ReadFace{
    private BufferedImage image;
    private int imageWidth;
    private int imageHeigt;
    private String imagePath;
    
    
    public Reader(String imagePath){
        this.imagePath = imagePath;
        readImage();
    }
    
    @Override
    public void readImage() {
         try{
            File input = new File(imagePath);
            image = ImageIO.read(input);
            imageWidth = image.getWidth();
            imageHeigt = image.getHeight();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage getReadedImage() {
        return this.image;
    }
    
    public int getImageWidth(){
        return this.imageWidth;
    }
    
    public int getImageHeight(){
        return this.imageHeigt;
    }
    
}
