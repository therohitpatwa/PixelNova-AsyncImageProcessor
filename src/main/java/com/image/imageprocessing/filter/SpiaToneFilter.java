package com.image.imageprocessing.filter;

import java.awt.image.BufferedImage;

public class SpiaToneFilter implements ImageFilter {
    @Override
    public BufferedImage filter(BufferedImage image) {
        BufferedImage greyscaleImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                // Calculate the average for greyscale.
                int avg = (red + green + blue) / 3;

                // Set the new greyscale color.
                int greyscaleRGB = (avg << 16) | (avg << 8) | avg;
                greyscaleImage.setRGB(x, y, greyscaleRGB);
            }
        }
        return greyscaleImage;
    }
}
