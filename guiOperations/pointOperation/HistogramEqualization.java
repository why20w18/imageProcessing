/*
    1-Histogram hesaplama           +
    2-Pi degerlerini hesaplama      +
    3-CDF fonksiyonu                +
    4-yeni pixel degeri             +



*/
package guiOperations.pointOperation;

import guiOperations.rw.ReadWrite;
import java.awt.Color;

/**
 *
 * @author bw
 */

enum HIST_EQUAL_MODE{
    NORMALIZED,
    BASIC,
}


public class HistogramEqualization extends ReadWrite{
    private int redHistogram[];
    private int greenHistogram[];
    private int blueHistogram[];
    
    private int redEqualization[];
    private int greenEqualization[];
    private int blueEqualization[];
    
    private int totalPixel;
    private HIST_EQUAL_MODE mode_normalized_basic;
    private static int basicR;
    private static int basicG;
    private static int basicB;
    
    public HistogramEqualization(String imagePath,HIST_EQUAL_MODE mode_normalized_basic) {
        super(imagePath);
        this.redHistogram = new int[256];
        this.greenHistogram = new int[256];
        this.blueHistogram = new int[256];
        
        this.redEqualization = new int[256];
        this.greenEqualization = new int[256];
        this.blueEqualization = new int[256];
        
        this.totalPixel = image.getWidth() * image.getHeight();
        this.mode_normalized_basic = mode_normalized_basic;
        
        
    }
    
    public static HIST_EQUAL_MODE setModeNormalized(){
        return HIST_EQUAL_MODE.NORMALIZED;
    }
    
    public static HIST_EQUAL_MODE setModeBasic(int R , int G , int B){
        basicR = R;
        basicG = G;
        basicB = B;
        return HIST_EQUAL_MODE.BASIC;
    }
    

    @Override
    public void applyPointOperation(boolean isLogOpen) {
         calculateHistogram();
         calculateEqualization(this.mode_normalized_basic);
        applyHistogramEqualization(isLogOpen);     
    }
    
    
    
    
    
    private void calculateHistogram(){
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                Color currentPixel = new Color(image.getRGB(i, j));
               
                this.redHistogram[currentPixel.getRed()]++;
                this.greenHistogram[currentPixel.getGreen()]++;
                this.blueHistogram[currentPixel.getBlue()]++;
            }
        }
    }
    
    
    
    
    
    
    private int[] getCDF(int histogram[]){
        int cdf[] = new int[256];
        
        cdf[0] = histogram[0];
        for(int i = 1 ; i < 256 ; i++){
            cdf[i] = cdf[i-1] + histogram[i];
        }
        
        return cdf;
    }
    
    private int getMin(int arr[]){
        int result = arr[0];
        for(int i = 0 ; i < arr.length ; i++){
            if(result > arr[i])
                result = arr[i];
        }
        return result;
    }
    
    private void calculateEqualization(HIST_EQUAL_MODE mode_normalized_basic){
        //tum kanallarin cdfleri hesaplandi
        int redCDF[] = getCDF(redHistogram);
        int greenCDF[] = getCDF(greenHistogram);
        int blueCDF[] = getCDF(blueHistogram);
        
        //NORMALIZASYON
        if(mode_normalized_basic == HIST_EQUAL_MODE.NORMALIZED){
            int redMin = getMin(redCDF);
            int greenMin = getMin(greenCDF);
            int blueMin = getMin(blueCDF);
            
            for(int i = 0 ; i < 256 ; i++){
                redEqualization[i] = setScaleRange((int)(((double)(redCDF[i] - redMin))/(totalPixel-redMin) *255), 0, 255);
                greenEqualization[i] = setScaleRange( (int)(((double)(greenCDF[i] - greenMin))/(totalPixel-greenMin) *255), 0, 255);
                blueEqualization[i] = setScaleRange((int)(((double)(blueCDF[i] - blueMin))/(totalPixel-blueMin) *255), 0, 255);
            }
        }
        else if(mode_normalized_basic == HIST_EQUAL_MODE.BASIC){
            for(int i = 0 ; i < 256 ; i++){
                redEqualization[i] = setScaleRange( ((int)((double)(redCDF[i] * 255))/redCDF[255-basicR]), 0, 255);
                greenEqualization[i] = setScaleRange(((int)((double)(greenCDF[i] * 255))/greenCDF[255-basicG]), 0, 255);
                blueEqualization[i] = setScaleRange(((int)((double)(blueCDF[i] * 255))/blueCDF[255-basicB]), 0, 255);
            }
        }
    }
    
    
    
    private void applyHistogramEqualization(boolean  isLogOpen){
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                Color currentPixel = new Color(image.getRGB(i, j));
                
                int eqRed = redEqualization[currentPixel.getRed()];
                int eqGreen = greenEqualization[currentPixel.getGreen()];
                int eqBlue = blueEqualization[currentPixel.getBlue()];
                
                Color eqPixel = new Color(eqRed,eqGreen,eqBlue);
                image.setRGB(i, j, eqPixel.getRGB());
            }
        }
    }
    
    
    
 
    
    
 
    
}
