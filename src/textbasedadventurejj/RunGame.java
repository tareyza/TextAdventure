/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textbasedadventurejj;

import java.util.Scanner;

/**
 *
 * @author Jerry
 */
public class RunGame {
    
     static boolean gameIsRunning;
    
    public static void loadSavedGame(){
    Event load = loadEvents();
        Interpreter.getInstance().interpret(load);//redos all previous events executed by player
    }
    
    private static Event loadEvents(){
        //Event event = new Event("Test","open door");
        return null;//implement later
    }
    
    public static void newGame(){
        System.out.println("Your game is being restarted.");
        gameIsRunning = false;
        runGame();
    }
    
    public static void exitGame(){
        System.out.println("Your game progress is saved. Thank you for playing.");
        gameIsRunning = false;
    }
    
    public static void runGame(){
    gameIsRunning = true;
        Scanner scanner = new Scanner(System.in);
        boolean commandExecuted = false;
        while (gameIsRunning) {
            System.out.println("");
            String nextLine = scanner.nextLine();
            commandExecuted = Interpreter.getInstance().interpret(nextLine);
            if(!commandExecuted){
                Interpreter.getInstance().printError();
            }
        }
    }
}
