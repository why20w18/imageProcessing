package guiOperations.rw;

import guiOperations.ChainPanel;
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
    
     public Writer(BufferedImage image,ChainPanel chain_panel){

         writePanel(image, chain_panel);
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
public void writePanel(BufferedImage image, ChainPanel chain_panel) {
      if (chain_panel.getImage() == null) {
        System.out.println("Image is null!");
        return; 
    }
      
    JPanel chains = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, chain_panel.getImage().getWidth(), chain_panel.getImage().getHeight(), this);
            }
        }
    };
    chain_panel.setPanel(chains);
    System.out.println("Panel set edildikten sonra: " + chain_panel.getPanel());
}

    @Override
    public JPanel getWritedPanel() {
        return panel;
    }
    
    
    
}
