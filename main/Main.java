/*


*/
package main;

import guiOperations.pointOperation.Brightness;
import guiOperations.pointOperation.Contrast;
import guiOperations.pointOperation.GammaCorrection;

import guiOperations.rw.ReadWrite;
import guiOperations.rw.Reader;
import guiOperations.rw.Writer;
import guiOperations.window.Window;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author bw // 15.03.2025 23:35 ~ 16.03.2025 01:41 ~ 
 */
public class Main {
    public static void main(String[] args) {
    
        final String imagePathJPEG = "C:\\Users\\bw\\Documents\\NetBeansProjects\\imageProcessFinal\\src\\images\\2.jpeg";
        final String imagePathBMP = "C:\\Users\\bw\\Documents\\NetBeansProjects\\imageProcessFinal\\src\\images\\1.bmp";
        final String imagePathTEST_GAMMA = "C:\\Users\\bw\\Documents\\NetBeansProjects\\imageProcessFinal\\src\\images\\gamma_test.png";
        final String imagePathTEST_GAMMA_2 = "C:\\Users\\bw\\Documents\\NetBeansProjects\\imageProcessFinal\\src\\images\\gamma_test_2.png";
        
        
//ORIGINAL IMAGE WINDOW
        Reader imageReader = new Reader(imagePathTEST_GAMMA);
        
        BufferedImage image = imageReader.getReadedImage();
        
        Writer imageWriter = new Writer(image);
        JPanel panel1 = imageWriter.getWritedPanel();
        Window window_original = new Window(640, 480, "Image Processing Library Testing | ORIGINAL IMAGE", panel1);


//BRIGHTNESS POINT OPERATION:
//        Brightness brightnessOperation = new Brightness(imagePathJPEG, 0);
//        brightnessOperation.applyPointOperation(false);
//        JPanel panel = brightnessOperation.getWritedPanel();
        
//CONTRAST POINT OPERATION:
//        Contrast contrastOperation = new Contrast(imagePathJPEG,200);
//        contrastOperation.applyPointOperation(false);
//        JPanel panel = contrastOperation.getWritedPanel();

//GAMMA CORRECTION POINT OPERATION:
//DECODING -> 1/gammaValue
//        GammaCorrection gammaCorrectionOperation = new GammaCorrection(imagePathTEST_GAMMA_2,8.5,GammaCorrection.gammaDecoding());
//        gammaCorrectionOperation.applyPointOperation(false);
//        JPanel panel = gammaCorrectionOperation.getWritedPanel();


//GAMMA CORRECTION POINT OPERATION:
//ENCODING -> /gammaValue
//        GammaCorrection gammaCorrectionOperation = new GammaCorrection(imagePathTEST_GAMMA,65.5,GammaCorrection.gammaEncoding());
//        gammaCorrectionOperation.applyPointOperation(false);
//        JPanel panel = gammaCorrectionOperation.getWritedPanel();







        //Window window_after_operation = new Window(640, 480, "Image Processing Library Testing | AFTER IMAGE", panel);
    
    
        
        
    }
}
