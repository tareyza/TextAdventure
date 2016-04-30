package textbasedadventurejj;

public class MakeCommand implements Command {

    @Override
    public boolean execute(String[] words) {
        if (words.length != 5) {
            return false;
        }
        if (words[1].equals("in") && words[3].equals("as")) {//"make object in location as name"
            String object = words[0];
            Location location = LocationManager.getInstance().getSubLocation(words[2]);
            System.out.println(location.getName());
            String objectName = words[4];
            location.getChildren().put(objectName, GameObjectManager.getInstance().newObject(objectName, object));
            return true;
        } else {
            return false;
        }
    }
}