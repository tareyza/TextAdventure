package textbasedadventurejj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunGame {

    static boolean gameIsRunning;

    private RunGame() {
    }

    public static void loadSavedGame() {
        try {
            String events = Utils.readFile(Constants.ROOT + Constants.SAVE_FILE);
            events = events.trim();
            String[] eventArr = events.split("\n");
            for (String element : eventArr) {
                Interpreter.getInstance().interpret(element);
            }
        } catch (IOException ex) {
            File file = new File(Constants.ROOT + Constants.SAVE_FILE);
            try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    public static void newGame() {
        System.out.println("Your game is being restarted.");
        gameIsRunning = false;
        File file = new File(Constants.ROOT + Constants.SAVE_FILE);
        file.delete();
        runGame();
    }

    public static void exitGame() {
        System.out.println("Your game progress is automatically saved. Thank you for playing.");
        gameIsRunning = false;
    }

    public static void runGame() {
		try {
			loadSavedGame();
			gameIsRunning = true;
			
			loadSavedGame();
	        Scanner scanner = new Scanner(System.in);
	        while (gameIsRunning) {
	            String nextLine = "do " + scanner.nextLine() + " player";
	            Interpreter.getInstance().interpret(nextLine);
	            Utils.writeEvent(nextLine);
	        }
	        scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}
