/*


*/
package main;

import guiOperations.pointOperation.Brightness;
import guiOperations.pointOperation.Contrast;
import guiOperations.pointOperation.GammaCorrection;
import guiOperations.pointOperation.Histogram;
import guiOperations.pointOperation.HistogramEqualization;
import guiOperations.pointOperation.Otsu;
import guiOperations.pointOperation.Threshold;

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
    
        final String imagePathJPEG = "src\\images\\2.jpeg";
        final String imagePathBMP = "src\\images\\1.bmp";
        final String imagePathTEST_GAMMA = "src\\images\\gamma_test.png";
        final String imagePathTEST_GAMMA_2 = "src\\images\\gamma_test_2.png";
        final String imagePathTEST_THRESHOLD = "src\\images\\coin_threshold.jpg";
        final String imagePathTEST_THRESHOLD_1 = "src\\images\\coin_threshold_1.jpg";
        final String imagePathTEST_THRESHOLD_2 = "src\\images\\threshold_1.png";
        final String imagePathTEST_THRESHOLD_3 = "src\\images\\threshold_2.jpg";
        final String imagePathCV_1 = "src\\images\\cv.jpg";
        final String imagePathCV_2 = "src\\images\\scaled.png";

        
//ORIGINAL IMAGE WINDOW
        Reader imageReader = new Reader(imagePathTEST_THRESHOLD_2  , Reader.setReaderRGB());
        
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


//THRESHOLD POINT OPERATION:
//GRAYSCALE -> 
        Threshold thresholdOperation = new Threshold(imagePathTEST_THRESHOLD_2, 20, 255, Threshold.setThresholdModeGrayscale());
        thresholdOperation.applyPointOperation(false);
        JPanel panel_1 = thresholdOperation.getWritedPanel();
        Window window_after_operation_1 = new Window(640, 480, "Image Processing Library Testing | MANUEL TH IMAGE", panel_1);



//THRESHOLD POINT OPERATION:
//SRGB -> 
//        Threshold thresholdOperation = new Threshold(imagePathTEST_GAMMA_2, 140, 255, Threshold.setModeSRGB());
//        thresholdOperation.applyPointOperation(false);
//        JPanel panel = thresholdOperation.getWritedPanel();
//
//
//
//        Window window_after_operation = new Window(640, 480, "Image Processing Library Testing | AFTER IMAGE", panel);


//HISTOGRAM OPERATION:
//        Histogram histogramOperation = new Histogram(imagePathJPEG, Histogram.setModeSRGB());
//        histogramOperation.applyPointOperation(false);
//        histogramOperation.infoHistogram();
//        histogramOperation.writeHistogramGraphics();
//        JPanel panel = histogramOperation.getWritedPanel();


//HISTOGRAM EQUALIZATION OPERATION:
//        HistogramEqualization histogramEqualizationOperation = new HistogramEqualization(imagePathTEST_GAMMA_2,HistogramEqualization.setModeBasic(10, 25, 10));
//        histogramEqualizationOperation.applyPointOperation(false);
//        JPanel panel = histogramEqualizationOperation.getWritedPanel();

//OTSU THRESHOLDING OPERATION:
        Otsu otsuOperation = new Otsu(imagePathTEST_THRESHOLD_2);
        otsuOperation.applyPointOperation(false);
        JPanel panel = otsuOperation.getWritedPanel();

        Window window_after_operation = new Window(640, 480, "Image Processing Library Testing | AFTER IMAGE", panel);
    }
}
