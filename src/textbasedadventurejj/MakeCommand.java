package textbasedadventurejj;

public class MakeCommand implements Command {

    @Override
    public boolean execute(String[] words) {
    	System.out.println("[Make Command] executing...");
        if (words.length < 5) {
            return false;
        }
        if (words[1].equals("in") && words[3].equals("as")) {//"make object in location as name"
            String object = words[0];
            Location location = LocationManager.getInstance().getRoot().getSubLocation(words[2]);
            String objectName = words[4];
            System.out.println(object);
            location.getChildren().put(objectName, GameObjectManager.getInstance().newObject(objectName, object));
            return true;
        } else {
            return false;
        }
    }
}
