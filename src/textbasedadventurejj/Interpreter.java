package textbasedadventurejj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    
    private static volatile Interpreter INSTANCE;
    
    static Structure getStructure(String verb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Location root;
    private Location context;
    private String[] lines;
    private GameObjectManager gameObjectManager;
    private Map<String, Command> commands;
    private int programCounter = 0;
    private boolean finished;
    
    private Interpreter() {
        commands = new HashMap<>();
    }
    
    public void setRoot(Location root) {
        this.root = root;
    }
    
    public Location getRoot() {
        return root;
    }
    
    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }
    
    public void removeCommand(String name) {
        commands.remove(name);
    }
    
    public void interpret(Event event) {
        programCounter = 0;
        lines = event.getLines();
        while(programCounter < lines.length){
            interpret(lines[programCounter++]);
        }
    }
    
    public boolean interpret(String line) {//line is command typed by user, object is the gameobject
        String[] words = line.split(" ");
        return interpretCommand(words);
        
    }
    
    private int countWords(String line) {
        int counter = 1;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                counter++;
            }
        }
        return counter;
    }
    
    public boolean interpretCommand(String[] words) {
        if (words.length == 0) {
            return false;
        }
        String command = words[0];
        if (commands.containsKey(words[0])) {
            return commands.get(words[0]).execute(Arrays.copyOfRange(words, 1, words.length));
        }
        return false;
    }
    
    public void skipUntilNewline(){
        while(!lines[programCounter++].equals("\n")){}
    }
    
    public boolean interpretSentence(String[] words) {//modified user typed sents are possible here
        if(words.length<1)
                return false;
        
        String verb = words[0];
        Structure struct = Interpreter.getStructure(verb);
        if (struct == null) {
            return false;
        }
        Phrase phrase = struct.parse(verb, Arrays.copyOf(words, 1));
        GameObject object = phrase.getSubject();
        Event event = object.getEvent(new Trigger(verb));
        interpret(event);
        return true;
    }
    
    public GameObject parseObject(String objectName) {
        String[] words = objectName.split("\\.");
        if (words[0].equals(root.getName())) {
            return root.getSubLocation(words).getChildren().get(objectName);
        }
        else{
            return context.getSubLocation(words).getChildren().get(objectName);
        }
    }

    public Location getContext() {
        return context;
    }
    
    public void setContext(Location context) {
        this.context = context;
    }
    
    public static Interpreter getInstance() {
        if (INSTANCE == null) {
            synchronized (Interpreter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Interpreter();
                }
            }
        }
        return INSTANCE;
    }
    
}
