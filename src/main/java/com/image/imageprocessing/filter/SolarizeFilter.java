package com.image.imageprocessing.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SolarizeFilter implements ImageFilter {
    private final int threshold;

    /**
     * Creates a new SolarizeFilter with a specified threshold.
     * @param threshold An integer from 0-255. Color channels with values above this
     * threshold will be inverted.
     */
    public SolarizeFilter(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public BufferedImage filter(BufferedImage image) {
        BufferedImage solarizedImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color originalColor = new Color(image.getRGB(x, y));

                int r = originalColor.getRed();
                int g = originalColor.getGreen();
                int b = originalColor.getBlue();

                // Invert the color channel if its value is above the threshold.
                r = (r > threshold) ? 255 - r : r;
                g = (g > threshold) ? 255 - g : g;
                b = (b > threshold) ? 255 - b : b;

                Color newColor = new Color(r, g, b);
                solarizedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return solarizedImage;
    }
}
