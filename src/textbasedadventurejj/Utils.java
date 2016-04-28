package textbasedadventurejj;

class Utils {
	
	public static GameObject getObjectInCurrentRoom(String name){
		return Interpreter.getInstance().getContext().getChildren().get(name);
	}
        
}
