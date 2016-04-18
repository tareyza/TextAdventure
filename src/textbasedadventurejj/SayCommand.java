
package textbasedadventurejj;


public class SayCommand implements Command {

    @Override
    public boolean execute(String[] words) {
        if(words.length!=1)
            return false;
        System.out.println(words[0]);
        return true;
    }
    
}
