package textbasedadventurejj;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Location{
    
    private String name;
    private Map<String, GameObject> children;
    private Map<String, Location> subLocations;
    private Map<String, Location> adjacentLocations;
    private Location parentLocation;
    
    public Location(String name, Location parentLocation){
        this.name = name;
        this.children = new HashMap<>();
        this.subLocations = new HashMap<>();
        this.adjacentLocations = new HashMap<>();
        this.parentLocation = parentLocation;
    }
    
    public Location(String name){
        this(name,null);
    }
    
    public Location getSubLocation(String path){
        return getSubLocation(path.split("\\."));
    }
    
    public Location getSubLocation(String[] path){
        if(path.length == 0)
            return this;
        Location location = subLocations.get(path[0]);
        return location != null ? location.getSubLocation(Arrays.copyOfRange(path, 1, path.length)) : null;
    }
    
    public GameObject[] getGameObjects(){
        return children.values().toArray(new GameObject[children.size()]);   
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Map<String, GameObject> getChildren(){
        return children;
    }

    public Map<String, Location> getSubLocations(){
        return subLocations;
    }

    public Map<String, Location> getAdjacentLocations(){
        return this.adjacentLocations;
    }
    
    public Location getParentLocation(){
        return parentLocation;
    }
    
    public static String getPath(Location location){
        String path = "";
        while(location!=null){
            path = location.getName()+"."+path;
            location = location.getParentLocation();
        }
        return path.substring(0, path.length()-1);
    }
}
