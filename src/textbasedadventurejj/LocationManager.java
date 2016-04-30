package textbasedadventurejj;

import java.io.File;
import java.io.IOException;

public class LocationManager {
	
	private static volatile LocationManager INSTANCE;
	
	Location root;
	Location context;
	
	private LocationManager(){}
	
	public Location getRoot(){
		return root;
	}
	
	private void setRoot(Location root){
		this.root = root;
	}
	
	public Location getContext(){
		return context;
	}
	
	public void setContext(Location context){
		this.context = context;
	}
	
    public GameObject parseObject(String objectName) {
        if (root.getSubLocations().containsKey(Utils.getPathRoot(objectName))) {
            return root.getSubLocation(Utils.getPathHead(objectName)).getChildren().get(Utils.getPathTail(objectName));
        } else {
            return context.getSubLocation(Utils.getPathHead(objectName)).getChildren().get(Utils.getPathTail(objectName));
        }
    }
	
	public void load(){
		File file = new File(Constants.LOCATION_ROOT);
		root = new Location("root");
		setRoot(root);
		loadTree(root, file);
	}
	
	private void loadTree(Location location, File locFile){
		for(File file : locFile.listFiles()){
			if(file.getName().equals("new.tba")){
				try {
					System.out.printf("[LocationManager] constructing %s\n", location.getName());
					Interpreter.getInstance().interpret(new Event("new", Utils.readFile(file).split("\n")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(file.isDirectory()){
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