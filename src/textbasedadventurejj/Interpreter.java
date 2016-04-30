package textbasedadventurejj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    private static volatile Interpreter INSTANCE;

    static Structure getStructure(String verb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
        String[] words = line.split(" ");
    	System.out.println("[Interpreter] interpreting command");
        return interpretCommand(words);

    }

    public void printError() {
        //print stuff for non-executed commands
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
        System.out.println("[Interpreter] type " + command);
        if (commands.containsKey(words[0])) {
            return commands.get(words[0]).execute(Arrays.copyOfRange(words, 1, words.length));
        }
        return false;
    }

    public void skipUntilNewline() {
        while (!lines[programCounter++].equals("\n")) {
        }
    }

    public boolean interpretSentence(String[] words) {//modified user typed sents are possible here
        if (words.length < 1) {
            return false;
        }

        String verb = "";
        boolean containsVerb = false;
        if (VerbManager.verifyVerbName(words[0])) {
            verb = words[0];
            containsVerb = true;
        }

        if (containsVerb) {
            Structure struct = Interpreter.getStructure(verb);
            if (struct == null) {
                return false;
            }
            Phrase phrase = struct.parse(verb, Arrays.copyOf(words, 1));
            GameObject object = phrase.getSubject();
            Event event = object.getEvent(new Trigger(verb));
            interpret(event);
            return true;
        } else {
            return interpretNonVerbSentence(words);
        }
    }

    public boolean interpretNonVerbSentence(String[] words) {
        return false;//stuff goes here later
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
