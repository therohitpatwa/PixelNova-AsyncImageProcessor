package com.image.imageprocessing.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InvertFilter implements ImageFilter {
    @Override
    public BufferedImage filter(BufferedImage image) {
        BufferedImage invertedImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color originalColor = new Color(image.getRGB(x, y));

                // Invert each color component by subtracting it from 255.
                int r = 255 - originalColor.getRed();
                int g = 255 - originalColor.getGreen();
                int b = 255 - originalColor.getBlue();

                Color invertedColor = new Color(r, g, b);
                invertedImage.setRGB(x, y, invertedColor.getRGB());
            }
        }
        return invertedImage;
    }
}