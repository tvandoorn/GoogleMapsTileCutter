package me.tvandoorn.gmapstilecutter;

import java.awt.image.BufferedImage;

public class ArgumentValidator {
    
    public static void validate(String[] args) {
        if(args.length != 3)
            Utility.exitWithError("Usage: GoogleMapsTileCutter {zoom} {full map} {output directory}");

        if(!Utility.tryParseInt(args[0]))
            Utility.exitWithError("Zoom parameter is not an integer!");

        int zoom = Integer.parseInt(args[0]);
        if(zoom < 1)
            Utility.exitWithError("Zoom level has to be higher than zero.");

        if(!Utility.fileExists(args[1]))
            Utility.exitWithError("Full map file does not exist!");

        if(!Utility.directoryExists(args[2])) {
            System.out.println("Output directory does not appear to exist. Creating...");

            String parentdir = Utility.getParentFolder(args[2]);
            if(!Utility.canWrite(parentdir))
                Utility.exitWithError("Cannot write to output directory: " + parentdir);

            Utility.createDirectory(args[2]);
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
