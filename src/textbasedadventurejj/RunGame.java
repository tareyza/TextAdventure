/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textbasedadventurejj;

/**
 *
 * @author Jerry
 */
public class RunGame {
    
    public void loadSavedGame(){
    Event load = loadEvents();
        Interpreter.getInstance().interpret(load);
    }
    
    public Event loadEvents(){
    return null; //implement once loader is made
    }
    
}
