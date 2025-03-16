/*
    NOKTA OPERASYONU OLARAK GAMA CORRECTION:
    1-goruntudeki max ve min intensityler bulunmalidir
    2-gval , gama duzeltmesi uygulanmadan onceki intensity degeridir
    
    I' = 255 x((I/255)^(1/GamaValue))
    (sRGB) GamaValue = 2.2
    
Corrected = 255 * (Image/255).^2.2      => g=0.1 icin parlak tonlar artar //ENCODING
Corrected = 255 * (Image/255).^(1/2.2)  => g=0.1 icin koyu tonlar artar   //DECODING
    
    
*/
package guiOperations.pointOperation;

import guiOperations.rw.ReadWrite;
import java.awt.Color;

/**
 *
 * @author bw
 */

 enum GAMMA_MODE{
        GAMMA_ENCODING,
        GAMMA_DECODING
}

public class GammaCorrection extends ReadWrite{
    private double gamma;
    private GAMMA_MODE mode_en_de_code;
    

   public static GAMMA_MODE gammaEncoding(){
       return GAMMA_MODE.GAMMA_ENCODING;
   }
   
   public static GAMMA_MODE gammaDecoding(){
       return GAMMA_MODE.GAMMA_DECODING;
   }

    
    public GammaCorrection(String imagePath , double gamma , GAMMA_MODE mode_en_de_code) {
        super(imagePath);
        this.gamma = gamma;
        this.mode_en_de_code = mode_en_de_code;
    
    }
    
    //default 2.2 gamma and default mode gamma encoding
    public GammaCorrection(String imagePath) {
        super(imagePath);
        this.gamma = 2.2;
        this.mode_en_de_code = GAMMA_MODE.GAMMA_ENCODING;
        
        
        
    }

    @Override
    public void applyPointOperation(boolean isLogOpen) {
        applyGammaCorrection(this.gamma, isLogOpen,this.mode_en_de_code);
    }
    
    //      ENCODING:
    //      I' = 255 x((I/255)^(GamaValue))
    //      (sRGB) GamaValue = 2.2
    public void applyGammaCorrection(double gammaValue , boolean isLogOpen,GAMMA_MODE mode_en_de_code){
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                
                Color currentColor = new Color(image.getRGB(i, j));
                
                int currentRedPixel = 0;
                int currentGreenPixel = 0;
                int currentBluePixel = 0;
                
                switch(mode_en_de_code){
                    case GAMMA_ENCODING:
                        currentRedPixel = (int) (255 * (Math.pow((currentColor.getRed()/255.0), gammaValue)));
                        currentGreenPixel = (int) (255 * (Math.pow((currentColor.getGreen()/255.0), gammaValue)));
                        currentBluePixel = (int) (255 * (Math.pow((currentColor.getBlue()/255.0), gammaValue)));
                    break;
                    
                    case GAMMA_DECODING:
                        currentRedPixel = (int) (255 * (Math.pow((currentColor.getRed()/255.0), 1/gammaValue)));
                        currentGreenPixel = (int) (255 * (Math.pow((currentColor.getGreen()/255.0), 1/gammaValue)));
                        currentBluePixel = (int) (255 * (Math.pow((currentColor.getBlue()/255.0), 1/gammaValue)));
                    break;
                }
           

                Color correctedColor = new Color(
                        setScaleRange(currentRedPixel,0,255),
                        setScaleRange(currentGreenPixel,0,255),
                        setScaleRange(currentBluePixel,0,255));
                image.setRGB(i,j,correctedColor.getRGB());
            }
        }
        
        
    }
    
}
