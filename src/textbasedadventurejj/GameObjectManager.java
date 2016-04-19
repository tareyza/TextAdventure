
package textbasedadventurejj;

import java.util.Map;

public class GameObjectManager {
    
    private String[] gameObjects;
    private Map<String, Integer> aliases;
    
    public GameObjectManager(){
        generateList();
    }
    
    public void generateList(){
    //implement logic to take object names from text file here
    }
    
    public boolean verifyGameObjectName(String name){
        return aliases.containsKey(name);
    }
    
    public String modifyGameObjectName(String name){
        if(!aliases.containsKey(name))
            return name;
        return gameObjects[aliases.get(name)];
    }
    
    public String[] getList(){
        return gameObjects;
    }
    
    public int maxWordLength(){
    //implement logic here to find max word length, or just hardcode it
        return 2;
    }
    
}
