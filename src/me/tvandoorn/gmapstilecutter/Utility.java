package me.tvandoorn.gmapstilecutter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utility {

    public static void exitWithError(String error) {
        System.err.println(error);
        System.exit(1);
    }
    
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean fileExists(String path) {
        File f = new File(path);
        return f.exists() && !f.isDirectory();
    }

    public static boolean directoryExists(String path) {
        File f = new File(path);
        return f.exists() && f.isDirectory();
    }
    
    public static boolean canWrite(String path) {
        File f = new File(path);
        return f.canWrite();
    }
    
    public static String getParentFolder(String path) {
        File f = new File(path);
        return f.getParentFile().getPath();
    }
    
    public static void createDirectory(String path) {
        File f = new File(path);
        f.mkdir();
    }
    
    public static BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
