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
		if ((event == null) || event.getLines().length == 0) {
			return;
		}
		lines = event.getLines();
		while (programCounter < lines.length) {
			interpret(lines[programCounter++]);
		}
	}

	public void interpret(String line) {// line is command typed by user,
											// object is the gameobject
		line = line.trim();
		if (line.startsWith("#"))
			return;
		String[] words = line.split(" +");
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

	public boolean interpretSentence(String[] words) {// modified user typed
														// sents are possible
														// here

		if (words.length < 1) {
			return false;
		}
		Phrase phrase = PhraseBuilder.getPhrase(words);

		if (phrase == null) {
			GameObject object = phrase.getSubject();
			Event event = object.getEvent(new Trigger(phrase.getVerb()));
			interpret(event);
			return true;
		} else {
			return interpretNonVerbSentence(words);
		}
	}

	public boolean interpretNonVerbSentence(String[] words) {
		if (words[0].equals("exit") || words[0].equals("quit")) {
			RunGame.exitGame();
			return true;
		} else if (words[0].equals("restart") || words[0].equals("restore")) {
			RunGame.newGame();
			return true;
		} else if (words[0].equals("save")) {
			System.out.println("Your game is automatically saved every action you make.");
			return true;
		} else if (words[0].equals("say")){
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
			return true;
		}
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
