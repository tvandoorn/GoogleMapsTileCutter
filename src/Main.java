import me.tvandoorn.gmapstilecutter.ArgumentValidator;
import me.tvandoorn.gmapstilecutter.TileCutter;
import me.tvandoorn.gmapstilecutter.Utility;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        ArgumentValidator.validate(args);
        
        int zoom = Integer.parseInt(args[0]);
        String outputdir = args[2];

        System.out.print("Loading full map into memory...");
        BufferedImage fullmap = Utility.loadImage(args[1]);
        System.out.println(" Done!");
        
        ArgumentValidator.fullmapIsCorrectResolution(fullmap, zoom);
        
        TileCutter tc = new TileCutter(zoom, fullmap, outputdir);
        tc.init();
        tc.start();
        
        System.out.println();
        System.out.println("Finished!");
        System.out.println();
    }
}
