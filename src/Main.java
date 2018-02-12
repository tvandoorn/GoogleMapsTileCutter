public class Main {

    public static void main(String[] args) {
        ArgumentValidator.validate(args);
        
        int zoom = Integer.parseInt(args[0]);
        String fullmap = args[1];
        String outputdir = args[2];
        
        TileCutter tc = new TileCutter(zoom, fullmap, outputdir);
        tc.init();
        tc.start();
        
        System.out.println();
        System.out.println("Finished");
        System.out.println();
    }
}
