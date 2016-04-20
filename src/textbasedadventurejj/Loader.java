package textbasedadventurejj;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Loader{

    public GameObject loadObject(String name){
    	String fp = Constants.OBJECT_ROOT + name;
    	File fobject = new File(fp);
    	for(File file : fobject.listFiles()){
    		if(file.getName().equals(Constants.EVENT_FILE))
    			parseEvents(file);
    	}
    }
    
    private void parseEvents(File eventFile){
    	BufferedReader reader = new BufferedReader(new FileReader(eventFile));
    	List<Event> cache = new ArrayList<>();
    	String s = null;
    	while((s = reader.readLine()) != null){
    		String[] keyVal = s.split(":");
    		String name = 
    		Event event = readEvent(keyVal[1]);
    	}
    }
}
