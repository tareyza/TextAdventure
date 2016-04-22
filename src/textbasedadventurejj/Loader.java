package textbasedadventurejj;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader{

    public GameObject loadObject(String name) throws IOException{
    	String fp = Constants.OBJECT_ROOT + name;
    	File fobject = new File(fp);
    	Map<Trigger, Event> events;
    	for(File file : fobject.listFiles()){
    		if(file.getName().equals(Constants.EVENT_FILE))
    			events = parseEvents(file);
    	}
    }
    
    
    public Location loadLocation(String name){
    	return null;
    }
    
    private Map<Trigger, Event> parseEvents(File eventFile) throws IOException{
    	BufferedReader reader = new BufferedReader(new FileReader(eventFile));
    	Map<String, Event> events = new HashMap<>();
    	Map<String, Event> cache = new HashMap<>();
    	String s = null;
    	while((s = reader.readLine()) != null){
    		String[] keyVal = s.split(":");
    		String[] trigger = keyVal[0].split(" ");
    		String name = trigger[0];
    		String object = null;
    		object = trigger[1];
    		String fname = keyVal[1];
    		Event event = null;
    		if(cache.containsKey(fname)){
    			event = cache.get(fname);
    		}else{
    			event = readEvent(new File(eventFile.getAbsolutePath() + File.separatorChar + name));
    			cache.put(fname, event);
    		}
    		events.put(new Trigger(name), event);
    	}
    	return events;
    }
    
    private Event readEvent(File event){
    	
    }
}