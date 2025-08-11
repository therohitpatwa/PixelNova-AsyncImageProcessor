package com.image.imageprocessing.io;

import java.awt.image.BufferedImage;

public interface ImageReadInf {

     <T> BufferedImage readImage(T src);

     void sendImage(BufferedImage image);

}
