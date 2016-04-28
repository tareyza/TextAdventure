package textbasedadventurejj;

import java.io.File;
import java.io.IOException;

public class LocationManager {
	
	private static volatile LocationManager INSTANCE;
	
	Location root;
	
	private LocationManager(){}
	
	public Location getRoot(){
		return root;
	}
	
	public void setRoot(Location root){
		this.root = root;
	}
	
	private void load(){
		File file = new File(Constants.LOCATION_ROOT);
		Location root = new Location("root");
		loadTree(root, file);
	}
	
	private void loadTree(Location location, File locFile){
		for(File file : locFile.listFiles()){
			if(file.getName().equals("new.tba")){
				try {
					Interpreter.getInstance().interpret(new Event(Utils.readFile(file)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Location newLoc = new Location(file.getName());
				location.getSubLocations().put(file.getName(), newLoc);
				loadTree(newLoc, file);
			}
		}
	}
	
	public static LocationManager getInstance() {
        if (INSTANCE == null) {
            synchronized (LocationManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationManager();
                }
            }
        }
        return INSTANCE;
    }
}
