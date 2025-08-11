package com.image.imageprocessing.image;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DrawMultipleImagesOnCanvas {

    private static DrawMultipleImagesOnCanvas instance;
    private Queue<ImageData> queue = new LinkedBlockingQueue<>();
    private GraphicsContext gc;
    private boolean isDrawing = false;

    public static DrawMultipleImagesOnCanvas getInstance(){
        if(instance == null){
            return new DrawMultipleImagesOnCanvas();
        }
        return instance;
    }

    public void addImageToQueue(ImageData image){
        queue.offer(image);
    }

    public void initialize(Stage primaryStage){
        Canvas canvas = new Canvas(1900, 1000);
        this.gc = canvas.getGraphicsContext2D();
        this.gc.clearRect(0,0, 1900, 1000);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Todo: poll the image from queue, spin a thread and draw
                if (!isDrawing && !queue.isEmpty()) {
                    new Thread(() -> {
                        try{
                            drawNextImage();
                        }finally {
                            isDrawing = false;
                        }
                    }).start();
                }
            }
        }.start();

        StackPane stack = new StackPane(canvas);


// or simply don't call them at all.
        Scene scene = new Scene(stack, 1900, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Canvas Example");
        primaryStage.show();
    }

    private void drawNextImage(){
        ImageData imageData = queue.poll();
        Platform.runLater(() -> {
            if(imageData != null){
                this.gc.drawImage(SwingFXUtils.toFXImage(imageData.getImage(), null),
                        imageData.getI(), imageData.getJ(), imageData.getX(), imageData.getY());
                System.out.println("Drawing using thread "+Thread.currentThread().getName());
                System.out.println(String.format("Drawing image at i: %s, j: %s", imageData.getI(), imageData.getJ()));
            }
        });
    }
}
