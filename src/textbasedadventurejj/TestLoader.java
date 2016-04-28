package textbasedadventurejj;

public class TestLoader {

	public static void main(String[] argv){
		
		GameObjectManager manager = GameObjectManager.getInstance();
		manager.loadObjects();
		
		System.out.println(manager.getObject("Weapon"));
		System.out.println(manager.getObject("Lamp"));
		System.out.println(manager.getObject("Sword"));
	}
}
