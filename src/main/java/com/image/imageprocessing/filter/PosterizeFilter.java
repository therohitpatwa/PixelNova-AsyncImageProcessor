package com.image.imageprocessing.filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PosterizeFilter implements ImageFilter {
    private final int levels;

    /**
     * Creates a new PosterizeFilter with a specified number of color levels.
     *
     * @param levels The number of color levels to use per channel (e.g., 2-256).
     *               A smaller number creates a more dramatic, poster-like effect.
     */
    public PosterizeFilter(int levels) {
        // Ensure that the number of levels is at least 1.
        this.levels = Math.max(1, levels);
    }

    @Override
    public BufferedImage filter(BufferedImage image) {
        BufferedImage posterizedImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        // The size of each color step.
        int step = 256 / this.levels;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color originalColor = new Color(image.getRGB(x, y));

                int r = originalColor.getRed();
                int g = originalColor.getGreen();
                int b = originalColor.getBlue();

                // Quantize each color channel to the nearest level.
                // For example, if levels=8, step=32. All red values from 0-31 become 0,
                // values from 32-63 become 32, and so on.
                int newR = (int) (Math.floor((double) r / step) * step);
                int newG = (int) (Math.floor((double) g / step) * step);
                int newB = (int) (Math.floor((double) b / step) * step);

                Color newColor = new Color(newR, newG, newB);
                posterizedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return posterizedImage;
    }
}
