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
    
    public static int getTotalTileCount(int layers) {
        int tiles = 0;
        
        for(int i = 0; i <= layers; i++)
            tiles += (int)(Math.pow(2, i) * Math.pow(2, i));
        
        return tiles;
    }
    
    public static int getLayerTileCount(int layer) {
        return (int)(Math.pow(2, layer) * Math.pow(2, layer));
    }
    
    public static int getMapSize(int zoom) {
        return Config.TILE_SIZE * (int)Math.pow(2, zoom);
    }
}
