package textbasedadventurejj;

public class Event{
    
    private String[] lines;

    public Event(String... lines){
        this.lines = lines;
    }

    public String[] getLines(){
        return lines;
    }
}
