package me.tvandoorn.gmapstilecutter;

import java.awt.image.BufferedImage;

public class ArgumentValidator {
    
    public static void validate(String[] args) {
        if(args.length != 4)
            Utility.exitWithError("Usage: GoogleMapsTileCutter {zoom} {threads} {full map} {output directory}");

        if(!Utility.tryParseInt(args[0]))
            Utility.exitWithError("Zoom parameter is not an integer!");

        int zoom = Integer.parseInt(args[0]);
        if(zoom < 1)
            Utility.exitWithError("Zoom level has to be higher than zero.");

        if(!Utility.tryParseInt(args[1]))
            Utility.exitWithError("Threads parameter is not an integer!");

        int threads = Integer.parseInt(args[1]);
        if(threads < 1)
            Utility.exitWithError("Thread count has to be higher than zero.");

        if(!Utility.fileExists(args[2]))
            Utility.exitWithError("Full map file does not exist!");

        if(!Utility.directoryExists(args[3])) {
            System.out.println("Output directory does not appear to exist. Creating...");

            String parentdir = Utility.getParentFolder(args[3]);
            if(!Utility.canWrite(parentdir))
                Utility.exitWithError("Cannot write to output directory: " + parentdir);

            Utility.createDirectory(args[3]);
            System.out.println("Output directory created!");
        }
    }
    
    public static void fullmapIsCorrectResolution(BufferedImage image, int zoom) {
        int size = (int)(Math.pow(2, zoom - 1) * 256);
        
        if(image.getWidth() != size && image.getHeight() != size) {
            Utility.exitWithError("Incorrect image dimensions. Image should be " + size + "x" + size + " pixels.");
        }
    }
}
