package com.image.imageprocessing.processor;

import com.image.imageprocessing.filter.ImageFilter;
import com.image.imageprocessing.image.DrawMultipleImagesOnCanvas;
import com.image.imageprocessing.image.ImageData;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageProcessor {

    private ExecutorService executorService;
    private DrawMultipleImagesOnCanvas drawFn;

    public ImageProcessor(){
        executorService = Executors.newFixedThreadPool(100);
    }

    public void processImage(BufferedImage image, int num, ImageFilter imageFilter, DrawMultipleImagesOnCanvas drawFn){
        int numHorizontalImages = image.getWidth() / num;   //return original width of image
        int numVerticalImages = image.getHeight() / num;

        List<Future<ImageData>> futures = new ArrayList<>();

        for (int i = 0; i<numHorizontalImages; i++){
            for(int j=0; j<numVerticalImages; j++){
                BufferedImage subImage = image.getSubimage(i*num, j*num, num, num);
                int finalI = i;
                int finalJ = j;
                Future<ImageData> future = executorService.submit(new Callable<ImageData>() {
                    @Override
                    public ImageData call(){
                        BufferedImage result = imageFilter.filter(subImage);
                        ImageData imageData = new ImageData(result, finalI *num, finalJ *num, num, num);
                        // Add to queue immediately when processing is complete
                        drawFn.addImageToQueue(imageData);
                        return imageData;
                    }
                });
                futures.add(future);
            }
        }

        for (Future<ImageData> future : futures) {
            try {
                future.get();
            } catch (Exception ex) {
                System.err.println("Error processing image: " + ex.getMessage());
            }
        }

    }

}
