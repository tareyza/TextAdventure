package textbasedadventurejj;

public class SetCommand implements Command {

    @Override
    public boolean execute(String[] words) {
        if (words.length != 3) {
            return false;
        }
        if (words[0].equals("state")) {
            GameObject object = Interpreter.getInstance().parseObject(words[1]);
            object.setState(words[2]);
            return true;
        } else if (words[0].equals("property")) {
            GameObject object = Interpreter.getInstance().parseObject(words[1]);
            object.setProperty(words[2], words[3]);
            return true;
        } else if (words[0].equals("location")) {
            GameObject object = Interpreter.getInstance().parseObject(words[1]);
            Location currentLocation = Interpreter.getInstance().getContext();
            String objectName = object.getName();
            Location newLocation = Interpreter.getInstance().getRoot().getSubLocation(words[2]);
            currentLocation.getChildren().remove(object.getName());
            newLocation.getChildren().put(objectName, object);
            return true;
        } else {
            return false;//nothing is executed
        }
    }
}
