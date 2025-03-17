/*
    BEKLENEN ISLEVLER:
    +ilerleyen surecte burada okuma modlari eklenebilir
    {enum icersinde,enum abstract class icinde gomulu sekilde}

 */
package guiOperations.rw;

import guiOperations.ReadFace;
import guiOperations.WriteFace;
import java.awt.Color;
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
    

    
    public BufferedImage getReadedImage(){
        return this.image;
    }
  
    public JPanel getWritedPanel(){
        return this.panel;
    }
  
    
    public int setScaleRange(int currentPixelValue , int minRange , int maxRange){
        return Math.min(maxRange, Math.max(minRange, currentPixelValue));
    }
    
     protected void clearImage(){
        Color newColor = new Color(255,255,255);
        
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                this.image.setRGB(i, j, newColor.getRGB());
                
            }
        }
    }
    
    //ilerleyen surecte burada okuma modlari eklenebilir
    public abstract void applyPointOperation(boolean isLogOpen);
    
    
    
}
