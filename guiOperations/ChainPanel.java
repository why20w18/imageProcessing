/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guiOperations;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author bw
 */
public class ChainPanel {
    private JPanel chain_panel;
    private BufferedImage chain_image;
    private String image_path;
    
    public ChainPanel(){
        chain_panel = null;
        chain_image = null;
    }
    
    public void setImagePath(String imagePath){
        this.image_path = imagePath;
    }
    
    public String getImagePath(){
        return this.image_path;
    }
    
   public void setPanel(JPanel setterPanel) {
    if (setterPanel == null) {
        System.out.println("Setter panel null!");
    } else {
        this.chain_panel = setterPanel;
        System.out.println("Panel başarıyla set edildi: " + setterPanel);
    }
}

public JPanel getPanel() {
    if (this.chain_panel == null) {
        System.out.println("Panel null, yeniden set edilmeli!");
    }
    return this.chain_panel;
}

    
    public BufferedImage getImage(){
        return this.chain_image;
    }
    
    public void setImage(BufferedImage image){
           this.chain_image = image;
    }
}
