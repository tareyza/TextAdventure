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
        System.out.println("Your game is being restarted.\n\n\n\n");
        gameIsRunning = false;
        File file = new File(Constants.ROOT + Constants.SAVE_FILE);
        file.delete();
        System.out.println("Welcome to Jerry and Jared's text based adventure. \nHere's some basic tips: In general to travel between rooms you have to type \"enter door.\" If there is more than door in a room, type something like \"enter hallway door\".\nWhen in doubt, type \"look around\" or \"examine\" to examine your surroundings and objects and \"take\" to take items. \nMake sure you start each command you type in with a verb, like \"say yes\" or \"open door\"");
        System.out.println("Your game automatically saves progress. If you want to exit game, type \"exit.\" Typing \"inventory\" will show your current items. If you want to restore to a new game, type \"restore.\"");
        System.out.println("");
        System.out.println("CLOSET");
        runGame();
    }

    public static void exitGame() {
        System.out.println("Your game progress is automatically saved. Thank you for playing.");
        gameIsRunning = false;
    }

    public static void runGame() {
        
        GameObjectManager gmanager = GameObjectManager.getInstance();
        LocationManager lmanager = LocationManager.getInstance();

        gmanager.loadObjects();
        lmanager.load();

        lmanager.setContext(LocationManager.getInstance().getSubLocation("World.Ship.Closet"));
		try {
			loadSavedGame();
			gameIsRunning = true;
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
