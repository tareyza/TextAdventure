
package textbasedadventurejj;

public class DoCommand implements Command {
  
    @Override public boolean execute(String[] words) {  
        Interpreter.getInstance().interpretSentence(words);        
        return true;
    } 
}
