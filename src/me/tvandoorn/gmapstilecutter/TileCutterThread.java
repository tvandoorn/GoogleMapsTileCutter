package me.tvandoorn.gmapstilecutter;

import java.awt.image.BufferedImage;

public class TileCutterThread implements Runnable {
    
    private BufferedImage sourceImage;
    private TileWriter tileWriter;
    
    private int zoom;
    private int mapSize;
    
    public TileCutterThread(BufferedImage source, int zoom, TileWriter tileWriter) {
        this.sourceImage = source;
        this.zoom = zoom;
        this.tileWriter = tileWriter;
        
        this.mapSize = Config.TILE_SIZE * (int)Math.pow(2, this.zoom);
    }
    
    @Override
    public void run() {
        int tilesPerRow = this.mapSize / Config.TILE_SIZE;
        for(int x = 0; x < tilesPerRow; x++)
            for(int y = 0; y < tilesPerRow; y++)
                this.processTile(x, y, this.zoom);
        
        this.sourceImage.flush();
        System.out.println("Thread for zoom level " + (this.zoom + 1) + " has finished.");
    }

    private void processTile(int x, int y, int zoom) {
        BufferedImage dest = this.sourceImage.getSubimage(x * Config.TILE_SIZE, y * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE);

        this.tileWriter.saveTile(zoom, x, y, dest);
    }
}
