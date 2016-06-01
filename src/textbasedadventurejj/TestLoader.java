package textbasedadventurejj;

public class TestLoader {

    public static void main(String[] argv) {

        

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
