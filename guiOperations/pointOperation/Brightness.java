/*
    NOKTA OPERASYONU OLARAK BRIGHTNESS:
    +goruntudeki her piksel tek tek gezilir ve max-min deger arasinda
    olceklenerek arttirma yada azaltma yapilir bunun icin Math.min(255,Math.max(255,VAL)) 
    seklinde implementasyon yapildi

 */
package guiOperations.pointOperation;

import guiOperations.ChainPanel;
import guiOperations.rw.ReadWrite;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author bw
 */
public class Brightness extends ReadWrite{
    private int brightnessAmount;
    

    
    public Brightness(String imagePath , int brightnessAmount) {
        super(imagePath);
        this.brightnessAmount = brightnessAmount;
    }
    
    public Brightness(String imagePath , int brightnessAmount , ChainPanel chain_panel){
        super(imagePath,chain_panel);
        this.brightnessAmount = brightnessAmount;
    }

    @Override
    public void applyPointOperation(boolean isLogOpen) {
        
      for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                Color c = new Color(image.getRGB(i, j)); //soldan saga

                /*
                algoritma:
                    >mevcut pixeli cek rgb degerlerine gore parse et
                    >255 degerinden buyuk her deger icin 255 al
                    >0 degerinden kucuk her deger icin 0 al
                    >aksi takdirde mevcutu al
                    >yeni pixel olustur ve mevcut i,j konumuna set et
                
                */
                
                int currentBluePixel = setScaleRange(c.getBlue()+brightnessAmount, 0, 255);
                int currentRedPixel = setScaleRange(c.getRed()+brightnessAmount, 0, 255);
                int currentGreenPixel = setScaleRange(c.getGreen()+brightnessAmount, 0, 255);
                
                
                Color brightColor = new Color(currentRedPixel,currentGreenPixel,currentBluePixel);
                image.setRGB(i, j, brightColor.getRGB());
                
                if(isLogOpen)
                System.out.println("red = " + currentRedPixel + " | green = " + currentGreenPixel + " | blue = " + currentGreenPixel);
            }
        }          
        
        
        
    }
    
}
