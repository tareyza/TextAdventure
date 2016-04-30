
package textbasedadventurejj;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunGame {

	static boolean gameIsRunning;

	private void RunGame() {
	}

	public static void loadSavedGame() {
		try {
			String events = Utils.readFile("SAVE_FILE");
			events = events.trim();
			String[] eventArr = events.split("\\n");
			for (String element : eventArr) {
				Interpreter.getInstance().interpret(element);
			}
		} catch (IOException ex) {
			Logger.getLogger(RunGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void newGame() {
		System.out.println("Your game is being restarted.");
		gameIsRunning = false;
		runGame(false);
	}

	public static void exitGame() {
		System.out.println("Your game progress is saved. Thank you for playing.");
		gameIsRunning = false;
	}

	public static void runGame(boolean load) {
		gameIsRunning = true;
		if (load) {
			loadSavedGame();
		}
		Scanner scanner = new Scanner(System.in);
		boolean commandExecuted = false;
		while (gameIsRunning) {
			System.out.println("");
			String nextLine = scanner.nextLine();
			Interpreter.getInstance().interpret(nextLine);
		}
	}
}
