package textbasedadventurejj;

public class TestLoader {

	public static void main(String[] argv){
		
		Interpreter interp = Interpreter.getInstance();
		interp.addCommand("make", new MakeCommand());
		interp.addCommand("set", new SetCommand());
		
		GameObjectManager gmanager = GameObjectManager.getInstance();
		LocationManager lmanager = LocationManager.getInstance();

		gmanager.loadObjects();
		lmanager.load();
		
		Location root = lmanager.getRoot();
		System.out.println(root);
		System.out.println(lmanager.parseObject("World.Ship.Closet.control_room_door"));
	}
}
