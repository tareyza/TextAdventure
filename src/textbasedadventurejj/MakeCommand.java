package textbasedadventurejj;

public class MakeCommand implements Command {

    @Override
    public boolean execute(String[] words) {
        if (words.length < 5) {
            return false;
        }
        if (words[1].equals("in") && words[3].equals("as")) {//"make object in location as name"
            String object = words[0];
            Location location = Interpreter.getInstance().getRoot().getSubLocation(words[2]);
            String objectName = words[4];

            location.getChildren().put(objectName, new GameObject(object));
            return true;
        } else {
            return false;
        }
    }
}
