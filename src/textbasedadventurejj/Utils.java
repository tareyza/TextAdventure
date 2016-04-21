package textbasedadventurejj;

class Utils {
	
	public GameObject getObjectInCurrentRoom(String name){
		return Interpreter.getInstance().getContext().getChildren().get(name);
	}
}
