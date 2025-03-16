/*
    NOKTA OPERASYONU OLARAK CONTRAST:
    +kontrast goruntunun max ve min piksel yogunlugu arasindaki farktir
    +Contrast = maxPixelIntensity - minPixelIntensity

    +Contrasti bulduktan sonra kontrast katsayisi = F cikartmak gerekli
    +F = (259x(C+255)) / (255x(259-C))  -->kullanici buradaki C degerini girebilmelidir
    
    +tum pixelleri gezecegiz kontrast katsayisi ile pixelleri tekrardan degerlendirecegiz
    +I'= F x (I-128) + 128


*/
package guiOperations.pointOperation;

import guiOperations.rw.ReadWrite;
import java.awt.Color;

/**
 *
 * @author bw
 */
public class Contrast extends ReadWrite{
    private int contrastFactor;
    
    //max degeri biz veririz ve buna gore hesaplama yapilir
    public Contrast(String imagePath , int contrastMax) {
        super(imagePath);
        this.contrastFactor = getContrastFactor(contrastMax);
    }
    
    //default bizim buldugumuz max pixele gore calisacak
    public Contrast(String imagePath) {
        super(imagePath);

        int contrast = getContrast();
        this.contrastFactor = getContrastFactor(contrast);
        
    }

    
    //+I'= F x (I-128) + 128
    @Override
    public void applyPointOperation(boolean isLogOpen){
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                Color c = new Color(image.getRGB(j, i));
                
                //contrastFactore gore yeni pixellerin degerini hesapladik
                int newRedPixel = setScaleRange((contrastFactor*(c.getRed()-128)) +128, 0, 255);
                int newGreenPixel = setScaleRange((contrastFactor*(c.getGreen()-128)) +128, 0, 255);
                int newBluePixel = setScaleRange((contrastFactor*(c.getBlue()-128)) +128, 0, 255);
                
                //yeni pikseli eski pikselin yerine set etmemiz gerekir biz color olarak cektiysek color olarak set ederiz
                Color newContrastColor = new Color(newRedPixel, newGreenPixel, newBluePixel);
                image.setRGB(j,i,newContrastColor.getRGB());                
            }
        }

    }
    
    public int getContrast(){
        int contrast = 0;
        int minIntensityPixel = 255;
        int maxIntensityPixel = 0;
        
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                Color c = new Color(image.getRGB(j, i));
                
                //intensity rgb'nin yogunluk ortalamasidir
                int intensity = getCurrentIntensity(c.getRed(), c.getGreen(), c.getBlue());
                
                //kiyasa gore min ve max intensity farkindan kontrasti hesaplama
                minIntensityPixel = (intensity < minIntensityPixel) ? intensity : minIntensityPixel;
                maxIntensityPixel = (intensity > maxIntensityPixel) ? intensity : maxIntensityPixel;
            }
        }

        contrast = maxIntensityPixel - minIntensityPixel;
        
        return contrast;
    }
    
    public int getCurrentIntensity(int redPixel , int greenPixel , int bluePixel){
        return (redPixel+greenPixel+bluePixel)/3;
    }
    
    //+F = (259x(C+255)) / (255x(259-C))  -->kullanici buradaki C degerini girebilmelidir
    public int getContrastFactor(int contrast){
        this.contrastFactor = (259*(contrast+255)) / (255*(259-contrast));
        return contrastFactor;
    }
    
  
    
}
