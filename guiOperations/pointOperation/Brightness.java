/*
    NOKTA OPERASYONU OLARAK BRIGHTNESS:
    +goruntudeki her piksel tek tek gezilir ve max-min deger arasinda
    olceklenerek arttirma yada azaltma yapilir bunun icin Math.min(255,Math.max(255,VAL)) 
    seklinde implementasyon yapildi

 */
package guiOperations.pointOperation;

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

    @Override
    public void applyPointOperation(boolean isLogOpen) {
        
      for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                Color c = new Color(image.getRGB(j, i)); //soldan saga

                /*
                algoritma:
                    >mevcut pixeli cek rgb degerlerine gore parse et
                    >255 degerinden buyuk her deger icin 255 al
                    >0 degerinden kucuk her deger icin 0 al
                    >aksi takdirde mevcutu al
                    >yeni pixel olustur ve mevcut i,j konumuna set et
                
                */
                
                int currentBluePixel = Math.min(255,Math.max(0,c.getBlue()+brightnessAmount));
                int currentRedPixel = Math.min(255,Math.max(0,c.getRed()+brightnessAmount));
                int currentGreenPixel = Math.min(255,Math.max(0,c.getGreen()+brightnessAmount));
                
                
                Color brightColor = new Color(currentRedPixel,currentGreenPixel,currentBluePixel);
                image.setRGB(j, i, brightColor.getRGB());
                
                if(isLogOpen)
                System.out.println("red = " + currentRedPixel + " | green = " + currentGreenPixel + " | blue = " + currentGreenPixel);
            }
        }          
        
        
        
    }
    
}
