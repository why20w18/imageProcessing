/*
    BEKLENEN ISLEVSELLIK: Writer
    1-verilen BufferImage bir panele yazilacak
    2-yazilan panel geri cevrilecek
    3-BufferImage bu class icinde tutulmayacak ki tasarim kalibini bozmayalim :|

*/
package guiOperations;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author bw
 */
public interface WriteFace {
    
    public abstract void writePanel(BufferedImage image);
    public abstract void writePanel(BufferedImage image,ChainPanel chain_panel);
   
    public abstract JPanel getWritedPanel();
    
    
}
