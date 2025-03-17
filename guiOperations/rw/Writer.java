package guiOperations.rw;

import guiOperations.WriteFace;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author bw
 */
public class Writer implements WriteFace {
    private JPanel panel;
  
    public Writer(BufferedImage image){
        writePanel(image);
    }
    
    
    @Override
    public void writePanel(BufferedImage image) {
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                   super.paintComponent(g);
                   if(image != null){
                       g.drawImage(image,0,0,image.getWidth(), image.getHeight(), panel);
                   }
                   
            }
        };
    }
    
    
 

    @Override
    public JPanel getWritedPanel() {
        return panel;
    }
    
    
    
}
