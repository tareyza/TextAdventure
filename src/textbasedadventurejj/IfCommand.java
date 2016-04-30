package textbasedadventurejj;

import java.util.ArrayList;

public class IfCommand implements Command {
	
	private LocationManager lmanager = LocationManager.getInstance();

    @Override
    public boolean execute(String[] words) {
        if (words.length < 1) {
            return false;
        }
        GameObject object = lmanager.getObject(words[0]);
        if (words[1].equals("contains")) {
            GameObject subObject = lmanager.getObject(words[2]);
            if (subObject.getParent().equals(object)) {
                return true;
            } else {
                Interpreter.getInstance().skipUntilNewline();
                return false;
            }
        } else if (words[1].equals("is")) {//if state e.g. lamp is (state) off
            if (object.getState().equals(words[1])) {
                return true;
            } else {
                Interpreter.getInstance().skipUntilNewline();
                return false;
            }
        } else if (words[1].equals("exists")) {
            GameObject target = lmanager.parseObject(words[2]);
            Location currentLocation = lmanager.getContext();
            ArrayList<GameObject> allObjects = new ArrayList<GameObject>();

            for (GameObject obj : currentLocation.getGameObjects()) {
                allObjects.add(obj);
            }
            boolean exist = false;
            for (GameObject obj : allObjects) {
                if (obj.equals(target)) {
                    exist = true;
                }
            }
            if (exist) {
                return true;
            } else {
                Interpreter.getInstance().skipUntilNewline();
                return false;
            }
        } else if (words[2].equals("in")) {
            GameObject target = lmanager.getObject(words[1]);
            Location targetLocation;
            if (words[3].equals("here")) {
                targetLocation = lmanager.getContext();
            } else if (words[3].equals("inventory")) {
                targetLocation = lmanager.getContext().getSubLocation("inventory");
            } else {
                targetLocation = lmanager.getContext().getSubLocation(words[3]);//does this actually work?!?
            }
            return targetLocation.getChildren().containsValue(words[1]);

        } else {
            return false;//nothing is executed
        }
    }

}
