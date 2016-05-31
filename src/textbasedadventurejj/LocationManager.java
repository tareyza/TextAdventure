package textbasedadventurejj;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    private static volatile LocationManager INSTANCE;

	private Location root;
	private Location context;
	private Location inventory;

    private LocationManager() {
    }

    public Location getRoot() {
        return root;
    }

    private void setRoot(Location root) {
        this.root = root;
    }

    public Location getContext() {
        return context;
    }

    public void setContext(Location context) {
        this.context = context;
    }
    
    public Location getInventory(){
    	return inventory;
    }
    
    public void setInventory(Location inventory){
    	this.inventory = inventory;
    }

    public Location getSubLocation(String path) {
        Location loc = context.getSubLocation(path);
        if (loc == null) {
            loc = root.getSubLocation(path);
        }
        return loc;
    }

    public GameObject getObject(String objectName) {
    	GameObject object = inventory.getChildren().get(objectName);
    	if(object != null)
    	    return object;
        Location location = getSubLocation(Utils.getPathHead(objectName));
        return location.getChildren().get(Utils.getPathTail(objectName));
    }

    public GameObject getObjectInCurrentRoom(String name) {
        return context.getChildren().get(name);
    }

    public void load() {
        File file = new File(Constants.ROOT + Constants.LOCATION_DIR);
        root = new Location("root");
        inventory = new Location("inventory");
        setRoot(root);
        loadTree(root, file);
    }

    private void loadTree(Location location, File locFile) {
        File constructor = null;
        for (File file : locFile.listFiles()) {
            if (file.getName().equals("new.tba")) {
                constructor = file;
            } else if (file.isDirectory()) {
                Location newLoc = new Location(file.getName());
                location.getSubLocations().put(file.getName(), newLoc);
                loadTree(newLoc, file);
            }
        }
        setContext(location);
        if (constructor != null) {
            try {
                Interpreter.getInstance().interpret(new Event("new", Utils.readFile(constructor).split("\n")));
                constructor = null;
            } catch (IOException e) {
                e.printStackTrace();
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
