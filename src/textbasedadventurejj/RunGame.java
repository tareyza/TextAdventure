package textbasedadventurejj;

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
            Logger.getLogger(RunGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void newGame() {
        System.out.println("Your game is being restarted.");
        gameIsRunning = false;
        String events;
        try {
            events = Utils.readFile(Constants.ROOT + Constants.NEW_GAME_FILE);
            events = events.trim();
            FileWriter writer = new FileWriter(Constants.ROOT + Constants.SAVE_FILE, false);
            writer.write(events);
        } catch (IOException ex) {
            Logger.getLogger(RunGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        runGame();
    }

    public static void exitGame() {
        System.out.println("Your game progress is automatically saved. Thank you for playing.");
        gameIsRunning = false;
    }

    public static void runGame() {
        
        gameIsRunning = true;
        loadSavedGame();
        Scanner scanner = new Scanner(System.in);
        boolean commandExecuted = false;
        while (gameIsRunning) {
            //System.out.println(LocationManager.getInstance().getRoot());
            String nextLine = scanner.nextLine();
            Interpreter.getInstance().interpret("do " + nextLine + " player");
            Interpreter.getInstance().interpret(nextLine);
        }
    }
}
