package textbasedadventurejj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    private static volatile Interpreter INSTANCE;
    private String[] lines;
    private Map<String, Command> commands;
    private int programCounter;
    
    private Interpreter() {
        commands = new HashMap<>();
    }

    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void removeCommand(String name) {
        commands.remove(name);
    }

    public void interpret(Event event) {
        programCounter = 0;
        if ((event == null) || event.getLines().length == 0 ) {
            return;
        }
        lines = event.getLines();
        while (programCounter < lines.length) {
            interpret(lines[programCounter++]);
        }
    }

    public boolean interpret(String line) {//line is command typed by user, object is the gameobject
        line = line.trim();
    	if(line.startsWith("#"))
    		return true;
    	String[] words = line.split(" +");
        return interpretCommand(words);

    }

    public void printError() {
        //print stuff for non-executed commands
    }

    public boolean interpretCommand(String[] words) {
        if (words.length == 0) {
            return false;
        }
        String command = words[0];
        if (commands.containsKey(command)) {
            return commands.get(command).execute(Arrays.copyOfRange(words, 1, words.length));
        }
        return false;
    }

    void skipUntilNewline() {
        while (programCounter < lines.length && !lines[programCounter++].equals("")) {}
    }

    public boolean interpretSentence(String[] words) {//modified user typed sents are possible here
        return false;
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
