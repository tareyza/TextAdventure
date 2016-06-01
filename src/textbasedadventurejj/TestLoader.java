package textbasedadventurejj;

import java.io.IOException;

public class TestLoader {

    public static void main(String[] argv) throws IOException {

        Constants constants = new Constants();

        Interpreter interp = Interpreter.getInstance();
        interp.addCommand("make", new MakeCommand());
        interp.addCommand("set", new SetCommand());
        interp.addCommand("if", new IfCommand());
        interp.addCommand("say", new SayCommand());
        interp.addCommand("do", new DoCommand());
        
        RunGame.printNewGameText();        
        RunGame.runGame();
    }
}
