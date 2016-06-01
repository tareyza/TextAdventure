package textbasedadventurejj;

import java.io.IOException;

public final class Constants {

	public static String ROOT;
	
	public static final String OBJECT_DIR = "Objects/";
	public static final String LOCATION_DIR = "Locations/";
	public static final String OBJECT_FILE = "objects";
	public static final String EVENT_FILE = "events";
	public static final String STRUCTURE_FILE = "structures";
	public static final String SAVE_FILE = "save";
	public static final String ERROR_FILE = "error";
    public static final String GAME_OBJECT_FILE = "aliases";
    public static final String NEW_GAME_FILE = "new";
    
    public Constants() throws IOException{
        ROOT = RunGame.setDirectory();
    }
}