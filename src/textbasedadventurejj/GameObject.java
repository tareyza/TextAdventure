package textbasedadventurejj;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class GameObject {

    private final Map<Trigger, Event> events;
    private final Map<String, Object> properties;
    private GameObject parent;
    private String state;
    private final String name;

    public static final GameObject NOTHING;
    
    static{
        NOTHING = new GameObject("nothing");
        NOTHING.setState("nothing");
    }

    public GameObject(String name) {
        events = new TreeMap<>();
        properties = new HashMap<>();
        parent = null;
        this.name = name;
    }
    
    public Map<Trigger, Event> getEvents(){
    	return events;
    }
    
    public void addEvents(Map<Trigger, Event> eventMap){
    	events.putAll(eventMap);
    }
 
    public void addEvent(Trigger trigger, Event event) {
        events.put(trigger, event);
    }

    public Event getEvent(Trigger trigger) {
        Event event = null;
        for (Map.Entry<Trigger, Event> entry : events.entrySet()) {
            int result = trigger.compareTo(entry.getKey());
            if (result >= 0) {
                if (event == null) {
                    event = entry.getValue();
                }
            }
            if (result == 0) {
                return event;
            }
        }
        return event;
    }

    public String getName() {
        return name;
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public void setProperty(String key, Object value) {
        properties.put(key, value);
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
    
    @Override public String toString(){
    	return name + (parent != null ? "(" + parent.getName() + ")" : "") + ":" + events;
    }
}
