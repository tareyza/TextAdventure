package textbasedadventurejj;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interpreter {

    private static volatile Interpreter INSTANCE;
    private String[] lines;
    private Map<String, Command> commands;
    private int programCounter;
    private Phrase phrase;

    private Interpreter() {
        commands = new HashMap<>();
    }

    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void removeCommand(String name) {
        commands.remove(name);
    }

    public void reset() {
        programCounter = 0;
    }

    public void interpret(Event event) {

        if ((event == null) || event.getLines().length == 0) {
            System.out.println("You cannot do that unfortuantely.");
            return;
        }
        reset();
        lines = event.getLines();
        while (programCounter < lines.length) {
            //System.out.println(lines[programCounter].toString());
            interpret(lines[programCounter++]);
        }
    }

    public void interpret(String line) {// line is command typed by user,
        // object is the gameobject

        line = line.trim();
        try {
            Utils.writeEvent(line);
        } catch (IOException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (line.startsWith("#")) {
            return;
        }
        String[] words = line.split(" +");
        words = substitute(words);
        interpretCommand(words);

    }

    public void printError(String[] line) {
        String input = "";
        for (int i = 0; i < line.length; i++) {
            input += line;
            input += " ";
        }
        printError(input);
    }

    public void printError(String line) {
        System.out.println("I cannot understand that unfortunately.");
        try {
            Utils.writeError(line);
        } catch (IOException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void interpretCommand(String[] words) {
        if (words.length == 0) {
            return;
        }
        String command = words[0];
        if (commands.containsKey(command)) {
            commands.get(command).execute(Arrays.copyOfRange(words, 1, words.length));
        }
    }

    void skipUntilNewline() {
        while (programCounter < lines.length && !lines[programCounter++].equals("")) {
        }
    }

    public void interpretSentence(String[] words) {
        if (words.length < 1) {
            return;
        }
        words = PhraseBuilder.replaceGameObjects(words);
        phrase = PhraseBuilder.getPhrase(words);

        if (phrase != null) {
            //System.out.println("phrase is not null");
            //System.out.println("phrase is: "+phrase);
            GameObject object = phrase.getDirectObject();
            if (object == null) {
                printError(words);
                //System.out.println("obj: "+object);
                return;
            }
            //System.out.println("verb is: "+phrase.getVerb());
            Trigger trigger = new Trigger(phrase.getVerb(), phrase.getIndirectObject());
            //System.out.println(LocationManager.getInstance().getContext());
            Event event = object.getEvent(trigger);
            interpret(event);
        } else {
            interpretNonVerbSentence(words);
        }
    }

    public void interpretNonVerbSentence(String[] words) {
        if (words[0].equals("exit") || words[0].equals("quit")) {
            RunGame.exitGame();
        } else if (words[0].equals("restart") || words[0].equals("restore")) {
            RunGame.newGame();
        } else if (words[0].equals("save")) {
            System.out.println("Your game is automatically saved every action you make.");
        } else if (words[0].equals("say")) {
            String[] say = new String[words.length + 1];
            say[0] = "say";
            for (int i = 0; i < words.length; i++) {
                say[i + 1] = words[i];
            }
            Phrase phrase = PhraseBuilder.getPhrase(say);
            GameObject object = phrase.getSubject();
            Event event = object.getEvent(new Trigger("say"));
            interpret(event);
            printError(words);
        }
    }

    private String[] substitute(String[] words) {
        String[] substituted = new String[words.length];
        for (int i = 0; i < words.length; ++i) {
            String sub = words[i];
            if (sub.startsWith("$")) {
                //System.out.println(sub);
                sub = phrase.getDirectObject().getProperties().get(sub.substring(1)).toString();
            } else if (sub.startsWith("@")) {
                switch (sub.substring(1)) {
                    case "object":
                        sub = phrase.getDirectObject().getName();
                        break;
                    case "subject":
                        sub = phrase.getSubject().getName();
                        break;
                    case "indirect":
                        sub = phrase.getIndirectObject().getName();
                        break;
                    case "verb":
                        sub = phrase.getVerb();
                }
            }
            substituted[i] = sub;
        }
        return substituted;
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
