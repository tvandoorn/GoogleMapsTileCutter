package me.tvandoorn.gmapstilecutter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class TileWriter {
    
    private String outputDir;
    
    public TileWriter(String outputdir) {
        this.outputDir = outputdir;
    }
    
    public void saveTile(int zoom, int x, int y, BufferedImage tile) {
        String fullpath = String.format(this.outputDir + "/%d/%d/%d.png", zoom, x, y);

        if(!Utility.directoryExists(this.outputDir + '/' + zoom))
            Utility.createDirectory(this.outputDir + '/' + zoom);

        if(!Utility.directoryExists(this.outputDir + '/' + zoom + '/' + x))
            Utility.createDirectory(this.outputDir + '/' + zoom + '/' + x);

        try {
            ImageIO.write(tile, "PNG", new FileOutputStream(fullpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        tile.flush();
    }
}
