package textbasedadventurejj;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class GameObject {

	private final Map<Trigger, Event> events;
	private final Map<String, Object> properties;
	private GameObject parent;
	private GameObject type;
	private String state = "default";
	private final String name;

	public static final GameObject NOTHING;

	static {
		NOTHING = new GameObject("nothing");
		NOTHING.setState("nothing");
	}

	public GameObject(String name) {
		events = new TreeMap<>();
		properties = new HashMap<>();
		parent = null;
		this.name = name;
	}

	public GameObject(String name, String state) {
		events = new TreeMap<>();
		properties = new HashMap<>();
		parent = null;
		this.name = name;
		this.state = state;
	}

	public GameObject(String name, GameObject object) {
		events = new HashMap<>(object.events);
		//System.out.println(events);
		properties = new HashMap<>(object.properties);
		parent = object.parent;
		type = object;
		state = object.state;
		this.name = name;
	}

	public GameObject(GameObject object) {
		this(object.getName(), object);
	}

	public Map<Trigger, Event> getEvents() {
		return events;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void addEvents(Map<Trigger, Event> eventMap) {
		events.putAll(eventMap);
	}

	public void addEvent(Trigger trigger, Event event) {
		events.put(trigger, event);
	}

	public Event getEvent(Trigger trigger) {
		return events.get(trigger);
	}

	public String getName() {
		return name;
	}

	public GameObject getType() {
		return type;
	}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isA(GameObject object) {
		if (this.equals(object)) {
			return true;
		}
		if (parent == null) {
			return false;
		}
		return parent.isA(object);
	}

	@Override
	public String toString() {
		return name + (parent != null ? "(" + parent.getName() + ")" : "") + "," + state + ":" + properties;
	}
}
