package com.image.imageprocessing.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileImageIO implements ImageReadInf {

    @Override
    public <T> BufferedImage readImage(T src){
        try{
            String path = (String) src;
            File imageFile = new File(path);
            return ImageIO.read(imageFile);
        }catch (Exception ex){
            System.err.println("Not able to read the image");
            return null;
        }
    }

    @Override
    public void sendImage(BufferedImage src){
        // Todo: implement this fucntion to store the imageFile

    }

}
