package com.image.imageprocessing.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorTintFilter implements ImageFilter {
    private final Color tintColor;

    public ColorTintFilter(Color tintColor) {
        this.tintColor = tintColor;
    }

    @Override
    public BufferedImage filter(BufferedImage image) {
        BufferedImage tintedImage = new BufferedImage(
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

                // Calculate the new color values by blending with the tint color.
                // A simple additive blend is used here.
                int newR = Math.min(255, r + tintColor.getRed());
                int newG = Math.min(255, g + tintColor.getGreen());
                int newB = Math.min(255, b + tintColor.getBlue());

                Color newColor = new Color(newR, newG, newB);
                tintedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return tintedImage;
    }

}