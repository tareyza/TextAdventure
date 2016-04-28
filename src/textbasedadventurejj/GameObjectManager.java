package textbasedadventurejj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameObjectManager {
	
	private static volatile GameObjectManager INSTANCE;
	
    private Map<String, GameObject> objects;
    
    public void loadObjects(){
    	objects = new HashMap<>();
    	try{
    		for(String s : Utils.readFile(Constants.OBJECT_ROOT + Constants.OBJECT_FILE).split("\n")){
    			String[] fullObj = s.split(":");
    			String name = fullObj[0];
    			GameObject object = Loader.loadObject(name);
    			if(fullObj.length == 2){
    				object.setParent(objects.get(fullObj[1]));
    			}
    			objects.put(name, object);
    		}
    	}catch(IOException ex){
    		ex.printStackTrace();
    	}
    }
    
    public boolean verifyGameObjectName(String name){
        return objects.containsKey(name);
    }
    
    public GameObject getObject(String name){
        return objects.get(name);
    }
     
    private static class Loader{
    	
    	public static GameObject loadObject(String name) throws IOException{
        	File file = new File(Constants.OBJECT_ROOT + name + File.separator + Constants.EVENT_FILE);
        	GameObject object = new GameObject(name);
        	System.out.println(file);
        	object.addEvents(parseEvents(file));
        	return object;
        } 
        
        private static Map<Trigger, Event> parseEvents(File eventFile) throws IOException{
        	BufferedReader reader = new BufferedReader(new FileReader(eventFile));
        	Map<Trigger, Event> events = new HashMap<>();
        	Map<String, Event> cache = new HashMap<>();
        	String s = null;
        	while((s = reader.readLine()) != null){
        		String[] keyVal = s.split(":");
        		String[] trigger = keyVal[0].split(",");
        		String name = null, object = null;
        		name = trigger[0];
        		if(trigger.length == 2){
        			object = trigger[1];
        		}
        		String fname = keyVal[1];
        		Event event = null;
        		if(cache.containsKey(fname)){
        			event = cache.get(fname);
        		}else{
        			event = readEvent(fname.split("\\.")[0], new File(eventFile.getParentFile().getAbsolutePath() + File.separatorChar + fname));
        			cache.put(fname, event);
        		}
        		events.put(new Trigger(name, GameObjectManager.getInstance().getObject(object)), event);
        	}
        	reader.close();
        	return events;
        }
        
        private static Event readEvent(String name, File event) throws IOException{
        	return new Event(name, Utils.readFile(event));
        }
    }
    
    public static GameObjectManager getInstance() {
        if (INSTANCE == null) {
            synchronized (Interpreter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GameObjectManager();
                }
            }
        }
        return INSTANCE;
    }
}