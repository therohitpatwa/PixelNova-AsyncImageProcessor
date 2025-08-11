package com.image.imageprocessing.filter;

import java.awt.image.BufferedImage;

public interface ImageFilter {
    BufferedImage filter(BufferedImage image);
}
