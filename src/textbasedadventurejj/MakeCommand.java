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
        //"make object in location as name"
        String object = words[0];
        Location location = Interpreter.getInstance().getRoot().getSubLocation(words[2]);
        String objectName = words[4];
        
        location.getChildren().put(objectName, new GameObject(object));
        return true;
    }
    
}
