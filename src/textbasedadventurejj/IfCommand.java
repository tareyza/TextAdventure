package textbasedadventurejj;

import java.util.ArrayList;

public class IfCommand implements Command {

    private LocationManager lmanager = LocationManager.getInstance();
    private Interpreter interp = Interpreter.getInstance();

    @Override
    public void execute(String[] words) {
        if (words.length > 4) {
            return;
        }
        boolean not = (words[0].equals("not"));
        if (not) {
            words = java.util.Arrays.copyOfRange(words, 1, words.length);
        }
        if (words[1].equals("is")) {// if state e.g. lamp is (state) off
            if (lmanager.getObject(words[0]).getState().equals(words[2])) {
                if (not) {
                    interp.skipUntilNewline();
                }
                return;
            }
            if (!not) {
                interp.skipUntilNewline();
            }
        } else if (words[1].equals("exists")) {
            if (lmanager.getObject(words[0]) == null && lmanager.getSubLocation(words[0]) == null) {
                if (!not) {
                    interp.skipUntilNewline();
                }
                return;
            }
            if (not) {
                interp.skipUntilNewline();
            }
        } else if (words[1].equals("in")){
            System.out.println(words[0]);
            if (lmanager.getSubLocation(words[2]).getChildren().containsKey(lmanager.getObject(words[0]).getName())) { //lmanager.getObject returns null
                if (not) {
                    interp.skipUntilNewline();
                }
                return;
            }
            if (!not) {
                interp.skipUntilNewline();
            }
        }

    }

}
