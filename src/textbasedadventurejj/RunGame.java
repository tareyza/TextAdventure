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
    
    public static void loadSavedGame(){
    Event load = loadEvents();
        Interpreter.getInstance().interpret(load);//redos all previous events executed by player
    }
    
    private static Event loadEvents(){
    return null; //implement once loader is made
    }
    
    public static void runGame(){
    boolean gameIsRunning = true;
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
