/*
    BEKLENEN ISLEVLER:
    ilerleyen surecte burada okuma modlari eklenebilir

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
    
    //ilerleyen surecte burada okuma modlari eklenebilir
    public abstract void applyPointOperation(boolean isLogOpen);
    
    
    
}
