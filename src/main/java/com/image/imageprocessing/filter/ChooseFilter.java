package com.image.imageprocessing.filter;


import java.awt.*;
import java.util.Scanner;

public class ChooseFilter {

    public Object chooseFilter()
    {
        System.out.println("Choose a Filter which you want to apply on Image :");
        System.out.println("For GreyScaleFilter Type A");
        System.out.println("For SpiaToneFilter Type B");
        System.out.println("For InvertFilter Type C");
        System.out.println("For Color Tint Filter Type D");
        System.out.println("For Solarize Effect Type E");
        System.out.println("For Posterize Effect Type F");
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        switch (s){
            case "A":
                return new GreyScaleFilter();

            case "B":
                return new SpiaToneFilter();

            case "C":
                return new InvertFilter();

            case "D":
                Color tintColor = new Color(100, 150, 200);
                return new ColorTintFilter(tintColor);


            case "E":
                System.out.println("Enter the value of threshold upto which you want Solarize Effect");
                System.out.println("Note: threshold An integer from 0-255. Color channels with values above this threshold will be inverted.");
                int threshold=sc.nextInt();
                return new SolarizeFilter(threshold);

            case "F":
                System.out.println("Enter the value of threshold upto which you want Posterize Effect");
                System.out.println("Note: levels The number of color levels to use per channel (e.g., 2-256) A smaller number creates a more dramatic, poster-like effect");
                int level=sc.nextInt();
                return new PosterizeFilter(level);

            default:
                System.out.println("Please choose available Filter");
                System.exit(0);
      }
        return "";
    }

}
