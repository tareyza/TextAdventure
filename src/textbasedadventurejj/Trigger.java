package textbasedadventurejj;

import java.util.Map;

public class Trigger implements Comparable<Trigger>{
    
    private String verb;
    private GameObject object;//indirect object
    private static String[] verbs;//all possible verbs
    private static Map<String, Integer> aliases;

    public Trigger(String verb, GameObject object){
        this.verb = verb;
        this.object = object;
        generateVerbs();
    }

    public Trigger(String verb){
        this(verb, GameObject.NOTHING);
    }

    public GameObject getObject(){
        return object;
    }

    public String getVerb(){
        return verb;
    }

    @Override public int compareTo(Trigger trigger){
        GameObject compare = trigger.getObject();
        if(object.equals(compare)){
            return 0;
        }
        if(object.isA(compare)){
            return 1;
        }
        return -1;
    }
    
    public void generateVerbs(){
    //implement logic to take acceptable verbs from text file here
    }
    
    public static boolean verifyVerbName(String name){
        return aliases.containsKey(name);
    }
    
    public static String modifyVerbName(String name){
        if(!aliases.containsKey(name))
            return name;
        return verbs[aliases.get(name)];
    }
    
    public String[] getList(){
        return verbs;
    }
    
}
