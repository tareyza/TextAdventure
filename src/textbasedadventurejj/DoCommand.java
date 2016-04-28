
package textbasedadventurejj;

public class DoCommand implements Command {
  
    @Override public boolean execute(String[] words) {  
        return Interpreter.getInstance().interpretSentence(words);        
    } 
}
