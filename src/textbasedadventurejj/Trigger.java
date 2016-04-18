package textbasedadventurejj;

public class Trigger implements Comparable<Trigger>{
    
    private String verb;
    private GameObject object;//indirect object

    public Trigger(String verb, GameObject object){
        this.verb = verb;
        this.object = object;
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
}
