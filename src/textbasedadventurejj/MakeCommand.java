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
public class MakeCommand implements Command {

    @Override
    public boolean execute(String[] words) {
        if (words.length < 1) {
            return false;
        }
        String objectName = words[0];
        Location location = Interpreter.getInstance().getRoot().getSubLocation(words[2]);
        
        
    }
    
}
