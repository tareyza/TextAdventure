package textbasedadventurejj;

public class TestLoader {

    public static void main(String[] argv) {

        Interpreter interp = Interpreter.getInstance();
        interp.addCommand("make", new MakeCommand());
        interp.addCommand("set", new SetCommand());
        interp.addCommand("if", new IfCommand());
        interp.addCommand("say", new SayCommand());
        interp.addCommand("do", new DoCommand());
        
        System.out.println("Welcome to Jerry and Jared's text based adventure. \nHere's some basic tips: In general to travel between rooms you have to type \"enter door.\" If there is more than door in a room, type something like \"enter hallway door\".\nWhen in doubt, type \"look around\" or \"examine\" to examine your surroundings and objects and \"take\" to take items. \nMake sure you start each command you type in with a verb, like \"say yes\" or \"open door\"");
        System.out.println("Your game automatically saves progress. If you want to exit game, type \"exit.\" Typing \"inventory\" will show your current items. If you want to restore to a new game, type \"restore.\"");
        System.out.println("");
        System.out.println("CLOSET");
        GameObjectManager gmanager = GameObjectManager.getInstance();
        LocationManager lmanager = LocationManager.getInstance();

        gmanager.loadObjects();
        lmanager.load();

        lmanager.setContext(LocationManager.getInstance().getSubLocation("World.Ship.Closet"));
        lmanager.setInventory(LocationManager.getInstance().getSubLocation("Inventory"));
        
        RunGame.runGame();
    }
}
