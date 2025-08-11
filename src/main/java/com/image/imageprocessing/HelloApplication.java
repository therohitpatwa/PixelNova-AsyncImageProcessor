package com.image.imageprocessing;

import com.image.imageprocessing.filter.*;
import com.image.imageprocessing.image.DrawMultipleImagesOnCanvas;
import com.image.imageprocessing.io.FileImageIO;
import com.image.imageprocessing.io.ImageReadInf;
import com.image.imageprocessing.processor.ImageProcessor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Check your code for these lines and make sure they are false

        DrawMultipleImagesOnCanvas drawMultipleImagesOnCanvas = DrawMultipleImagesOnCanvas.getInstance();
        drawMultipleImagesOnCanvas.initialize(stage);

        ImageReadInf imageIO = new FileImageIO();
        BufferedImage image = imageIO.readImage("C:\\Users\\thero\\OneDrive\\Desktop\\imageProcessingJava\\src\\main\\java\\com\\image\\imageprocessing\\io\\test.jpg");
        ImageProcessor processor = new ImageProcessor();
        ChooseFilter choose=new ChooseFilter();

        ImageFilter imageFilter = (ImageFilter)(choose.chooseFilter());
        processor.processImage(image, 10, imageFilter, drawMultipleImagesOnCanvas);

        FileImageIO send=new FileImageIO();


        Platform.setImplicitExit(false);

    }

    public static void main(String[] args) {
        launch();
    }
}