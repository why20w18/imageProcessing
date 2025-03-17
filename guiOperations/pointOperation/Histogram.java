/*
    BEKLENEN ISLEVSELLIK:
    1-goruntunun tum piksellerini gezip yogunlugu cekecegiz ve olmasi gereken
    renk hangisiyse onun icin 256 bytelik array icinde saklayip bunu gorsellestirecegiz
    
    2-
 */
package guiOperations.pointOperation;

import guiOperations.rw.ReadWrite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author bw
 */

enum MODE_HISTOGRAM{
    SRGB,
    GRAYSCALE
}

public class Histogram extends ReadWrite{
    private int [] redHist;
    private int [] greenHist;
    private int [] blueHist;
    private int [] grayHist;
    private int totalPixelCount;
    
  
    private MODE_HISTOGRAM mode_srgb_grayscale;
    
    public Histogram(String imagePath,MODE_HISTOGRAM mode_srgb_grayscale) {
        super(imagePath);
        this.mode_srgb_grayscale = mode_srgb_grayscale;
        
        if(mode_srgb_grayscale == MODE_HISTOGRAM.SRGB){            
            this.redHist = new int[256];
            this.greenHist = new int[256];
            this.blueHist = new int[256];
        }
        else if(mode_srgb_grayscale == MODE_HISTOGRAM.GRAYSCALE){
            this.grayHist = new int[256];
        }   
    }
    
    public static MODE_HISTOGRAM setModeSRGB(){
        return MODE_HISTOGRAM.SRGB;
    }

    public static MODE_HISTOGRAM setModeGrayscale(){
        return MODE_HISTOGRAM.GRAYSCALE;
    }
    
    @Override
    public void applyPointOperation(boolean isLogOpen) {
        applyCalculateHistogram(this.mode_srgb_grayscale);
    }
    
    private void applyCalculateHistogram(MODE_HISTOGRAM mode_srgb_grayscale){
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                totalPixelCount++;
                Color currentPixel = new Color(image.getRGB(i, j));
                
                
                if(mode_srgb_grayscale == MODE_HISTOGRAM.SRGB){
                    int currentRedPixel = currentPixel.getRed();
                    int currentGreenPixel = currentPixel.getGreen();
                    int currenBluePixel = currentPixel.getBlue();

                    this.redHist[currentRedPixel]++;
                    this.greenHist[currentGreenPixel]++;
                    this.blueHist[currenBluePixel]++;
                }
                
                else if(mode_srgb_grayscale == MODE_HISTOGRAM.GRAYSCALE){
                    int currentGrayPixel = (currentPixel.getRed() + currentPixel.getGreen() + currentPixel.getBlue())/3;
                    this.grayHist[currentGrayPixel]++;
                }
                
                
            }
        }
    }
    
    
    public void infoHistogram(){
        if(this.mode_srgb_grayscale == MODE_HISTOGRAM.SRGB){
            for(int i = 0 ; i < 256 ; i++){
                System.out.printf("RED[%3d]= %5d | GREEN[%3d]= %5d | BLUE[%3d]= %5d\n",
                        i,redHist[i],i,greenHist[i],i,blueHist[i]
                        );
            }
                }
        else if(this.mode_srgb_grayscale == MODE_HISTOGRAM.GRAYSCALE){
             for (int i = 0; i < 256; i++) {
            System.out.printf("GRAY[%3d] = %5d\n", i, grayHist[i]);
        }
        }
    }
    
    public int [] getRedHist(){
        return this.redHist;
    }
    
    public int [] getBlueHist(){
        return this.blueHist;
    }
    
    public int [] getGreenHist(){
        return this.greenHist;
    }
    
    public int [] getGrayHist(){
        return this.grayHist;
    }
    
    
    
    
    public void writeHistogramGraphics(){
        clearImage();

        double scaleX = 0.8; //%50x
        double scaleY = 0.1;  //%50y
        int rectWidth = 8;
        int rectHeigt = 10;

        
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.scale(scaleX, scaleY);

        g2d.setColor(Color.RED);
        for(int i = 0 ; i < 256; i++){
            for(int j = 0 ; j < 256 ; j++){
                g2d.fillRect(i, this.redHist[i], rectWidth, rectHeigt);
                g2d.setColor(Color.WHITE);
                g2d.fillRect(i+1, 0, rectWidth, rectHeigt);  
                g2d.setColor(Color.RED);
            }
        }
        
        g2d.setColor(Color.GREEN);
        for(int i = 0 ; i < 256; i++){
            for(int j = 0 ; j < 256 ; j++){
                g2d.fillRect(i+256, this.greenHist[i], rectWidth, rectHeigt);
                g2d.setColor(Color.WHITE);
                g2d.fillRect(i+257, 0, rectWidth, rectHeigt);  
                g2d.setColor(Color.GREEN);  
            }
        }
        
        g2d.setColor(Color.BLUE);
        for(int i = 0 ; i < 256; i++){
            for(int j = 0 ; j < 256 ; j++){
                g2d.fillRect(i+256+256, this.blueHist[i], rectWidth, rectHeigt);
                g2d.setColor(Color.WHITE);
                g2d.fillRect(i+1+256+256, 0, rectWidth, rectHeigt);  
                g2d.setColor(Color.BLUE);  
            }
        }
        
        
        
    }
    
   
    
}
