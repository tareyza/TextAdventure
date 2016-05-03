package textbasedadventurejj;

public class SetCommand implements Command {

	private LocationManager lmanager = LocationManager.getInstance();

	@Override
	public void execute(String[] words) {
		if (words.length != 3) {
			return;
		}
		if (words[1].equals("is")) {
			GameObject object = lmanager.getObject(words[0]);
			object.setState(words[2]);
		} else if (words[1].equals("in")) {// "set" + objectName "in" +
											// newLocation
			System.out.println(words[0]);
			System.out.println(lmanager.getContext().getName());
			GameObject object = lmanager.getObject(words[0]);
			Location currentLocation = lmanager.getContext();
			String objectName = object.getName();
			System.out.println(words[2]);
			Location newLocation = lmanager.getSubLocation(words[2]);
			currentLocation.getChildren().remove(object.getName());
			newLocation.getChildren().put(objectName, object);
		} else if (words[1].equals("as")) {// set PROPERTY as VALUE
			GameObject object = lmanager.getObject(Utils.getPathHead(words[0]));
			object.getProperties().put(Utils.getPathTail(words[0]), words[2]);
		}
	}
}