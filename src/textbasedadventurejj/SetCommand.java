package textbasedadventurejj;

public class SetCommand implements Command {

	private LocationManager lmanager = LocationManager.getInstance();

	@Override
	public void execute(String[] words) {
		//System.out.println("FIRST WORD OF COMMAND IS: " + words[0]);
		if (words[1].equals("is")) {
			GameObject object = lmanager.getObject(words[0]);
			object.setState(words[2]);
		} else if (words[1].equals("in")) {// "set" + objectName "in" +									// newLocation
			System.out.println("object: "+words[0]);
			System.out.println("from: " +lmanager.getContext().getName());
			GameObject object = lmanager.getObject(words[0]);
			Location currentLocation = lmanager.getContext();
			String objectName = object.getName();
			System.out.println("to: "+words[2]);
			Location newLocation = lmanager.getSubLocation(words[2]);
			currentLocation.getChildren().remove(object.getName());
			newLocation.getChildren().put(objectName, object);
		} else if (words[1].equals("as")) {// set PROPERTY as VALUE
			GameObject object = lmanager.getObject(Utils.getPathHead(words[0]));
			object.getProperties().put(Utils.getPathTail(words[0]), words[2]);
		} else if (words[0].equals("context")){
			//System.out.println("context");
			Location location = lmanager.getSubLocation(words[1]);
			//System.out.println(location);
			
			lmanager.setContext(location);
		}
	}
}