
package textbasedadventurejj;

import java.awt.SystemColor;

public class SayCommand implements Command {

    @Override
    public boolean execute(String[] words) {
        String buffer = "";
        for(String s : words){
        	buffer += s + " ";
        }
        System.out.println(buffer.trim());
        return true;
    }
    
}
