import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class TileCutter {
    
    private int zoom;
    private String fullmapurl;
    private String outputdir;
    
    private int tilesize;
    private int totaltiles;
    private BufferedImage fullmap;
    private ProgressBar progress;
    
    public TileCutter(int zoom, String fullmap, String outputdir) {
        this.zoom = zoom;
        this.fullmapurl = fullmap;
        this.outputdir = outputdir;
        
        this.tilesize = 256;
    }

    public int getZoom() {
        return this.zoom;
    }
    public String getFullmapurl() {
        return this.fullmapurl;
    }
    public String getOutputdir() {
        return this.outputdir;
    }

    public void init() {
        System.out.println();
        System.out.println("Initializing tile cutter...");
        
        for(int i = 0; i <= this.zoom; i++)
            this.totaltiles += (int)(Math.pow(2, i) * Math.pow(2, i));
        
        System.out.println("Tile count to be generated: " + this.totaltiles);

        System.out.print("Loading full map into memory...");
        this.fullmap = Utility.loadImage(this.fullmapurl);
        System.out.println(" Done!");

        System.out.println("Initialization finished.");
    }

    public void start() {
        System.out.println();
        System.out.println("Starting cutter...");
        System.out.println();
        
        this.progress = new ProgressBar("tiles", this.totaltiles);
        
        this.recursiveSplit(this.fullmap, this.zoom);
    }
    
    private void recursiveSplit(BufferedImage image, int zoom) {
        int mapwidth = this.getMapSize(zoom);
        
        int sizetiles = mapwidth / this.tilesize;

        BufferedImage resizedimage = this.resizeImage(image, mapwidth);
        image.flush();
        
        for(int x = 0; x < sizetiles; x++)
            for(int y = 0; y < sizetiles; y++)
                this.processTile(resizedimage, x, y, zoom);
        
        if(zoom > 0)
            this.recursiveSplit(resizedimage, zoom - 1);
    }
    
    private int getMapSize(int zoom) {
        return this.tilesize * (int)Math.pow(2, zoom);
    }
    private BufferedImage resizeImage(BufferedImage image, int size) {
        Image temp = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private void processTile(BufferedImage image, int x, int y, int zoom) {
        String fullpath = String.format(this.outputdir + "/%d/%d/%d.png", zoom, x, y);
        
        if(!Utility.directoryExists(this.outputdir + '/' + zoom))
            Utility.createDirectory(this.outputdir + '/' + zoom);

        if(!Utility.directoryExists(this.outputdir + '/' + zoom + '/' + x))
            Utility.createDirectory(this.outputdir + '/' + zoom + '/' + x);
        
        BufferedImage dest = image.getSubimage(x * this.tilesize, y * this.tilesize, this.tilesize, this.tilesize);

        try {
            ImageIO.write(dest, "PNG", new FileOutputStream(fullpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.progress.advance();
    }
}
