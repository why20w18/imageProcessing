/*
    BEKLENEN ISLEVLER:
    +ilerleyen surecte burada okuma modlari eklenebilir
    {enum icersinde,enum abstract class icinde gomulu sekilde}

 */
package guiOperations.rw;

import guiOperations.ReadFace;
import guiOperations.WriteFace;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author bw
 */
public abstract class ReadWrite{
    private Reader imageReader;
    private Writer imageWriter;
    private JPanel panel;
    protected BufferedImage image;
    
    //zaten abstract instance uretilemez
    public ReadWrite(String imagePath) {
            imageReader = new Reader(imagePath);
            image = imageReader.getReadedImage();
        
            imageWriter = new Writer(image);
            panel = imageWriter.getWritedPanel();
    }
    
    protected BufferedImage getReadedImage(){
        return this.image;
    }
  
    public JPanel getWritedPanel(){
        return this.panel;
    }
    
    public int setScaleRange(int currentPixelValue , int minRange , int maxRange){
        return Math.min(maxRange, Math.max(minRange, currentPixelValue));
    }
    
    //ilerleyen surecte burada okuma modlari eklenebilir
    protected abstract void applyPointOperation(boolean isLogOpen);
    
    
    
}
