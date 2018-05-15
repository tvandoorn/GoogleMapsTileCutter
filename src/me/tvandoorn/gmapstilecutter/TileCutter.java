package me.tvandoorn.gmapstilecutter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;

public class TileCutter {
    
    private int zoom;
    private String outputDir;
    
    private BufferedImage fullMap;
    
    private int threadCount;
    
    public TileCutter(int zoom, int threadcount, BufferedImage fullmap, String outputdir) {
        this.zoom = zoom;
        this.fullMap = fullmap;
        this.outputDir = outputdir;
        this.threadCount = threadcount;
    }

    public void init() {
        System.out.println();
        System.out.println("Initializing tile cutter...");
        
        System.out.println("Tile count to be generated: " + Utility.getTotalTileCount(zoom));

        System.out.println("Initialization finished.");
    }

    public void start() {
        System.out.println();
        System.out.println("Starting cutter...");
        System.out.println();
        
        this.dispatchThreads();
    }
    
    private void dispatchThreads() {
        int lastZoom = 0;
        
        ExecutorService threadPool = Executors.newFixedThreadPool(this.threadCount);
        BlockingQueue<TileCutterThread> threads = new ArrayBlockingQueue<>(this.zoom);
        
        System.out.println("Creating threads...");
        while(lastZoom < this.zoom) {
            System.out.println("Creating thread for zoom level " + (lastZoom + 1) + "/" + this.zoom);

            BufferedImage source = this.resizeImage(this.fullMap, Utility.getMapSize(lastZoom));
            
            TileCutterThread tcThread = new TileCutterThread(source, lastZoom, new TileWriter(this.outputDir));
            threads.add(tcThread);
            
            lastZoom++;
        }
        
        System.out.println("\nStarting threads...");
        
        while(!threads.isEmpty()) {
            try {
                threadPool.execute(threads.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {}
    }

    private BufferedImage resizeImage(BufferedImage image, int size) {
        if(image.getWidth() == size)
            return image;
        
        Image temp = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        Graphics g = dimg.createGraphics();
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        return dimg;
    }
}
