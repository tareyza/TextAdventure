package textbasedadventurejj;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Location {

	private String name;
	private Map<String, GameObject> children;
	private Map<String, String> aliases;
	private Map<String, Location> subLocations;
	private Map<String, Location> adjacentLocations;
	private Location parentLocation;

	public Location(String name, Location parentLocation) {
		this.name = name;
		this.children = new HashMap<>();
		this.subLocations = new HashMap<>();
		this.adjacentLocations = new HashMap<>();
		this.parentLocation = parentLocation;
	}

	public Location(String name) {
		this(name, null);
	}

	public Location getSubLocation(String path) {
		return getSubLocation(path.split("\\."));
	}

	public Location getSubLocation(String[] path) {
		if (path.length == 0 || path[0].equals("here") || path[0].equals(""))
			return this;
		Location location = subLocations.get(path[0]);
		return location != null ? location.getSubLocation(Arrays.copyOfRange(path, 1, path.length)) : null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, String> getAliases(){
		return this.aliases;
	}

	public Map<String, GameObject> getChildren() {
		return children;
	}

	public Map<String, Location> getSubLocations() {
		return subLocations;
	}

	public Map<String, Location> getAdjacentLocations() {
		return this.adjacentLocations;
	}

	public Location getParentLocation() {
		return parentLocation;
	}

	public static String getPath(Location location) {
		String path = "";
		while (location != null) {
			path = location.getName() + "." + path;
			location = location.getParentLocation();
		}
		return path.substring(0, path.length() - 1);
	}

	@Override
	public String toString() {
		return toString("", 0);
	}

	private String toString(String buffer, int level) {
		buffer += indented(name + "\n", level);
		for (GameObject obj : children.values()) {
			//System.out.println(obj);
			buffer += indented(obj.getName() + ":" + obj.getType().getName() + "\n", level + 1);
		}
		for (Location loc : subLocations.values()) {
			buffer = loc.toString(buffer, level + 1);
		}
		return buffer;
	}

	private String indented(String initial, int indents) {
		for (int i = 0; i < indents; ++i) {
			initial = "|" + initial;
		}
		return initial;
	}
}