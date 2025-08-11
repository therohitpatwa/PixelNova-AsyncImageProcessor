package com.image.imageprocessing.image;

import java.awt.image.BufferedImage;

public class ImageData {

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageData(BufferedImage image, int i, int j, int x, int y) {
        this.image = image;
        this.i = i;
        this.j = j;
        this.x = x;
        this.y = y;
    }

    private BufferedImage image;
    private int i;
    private int j;
    private int x;
    private int y;

}
