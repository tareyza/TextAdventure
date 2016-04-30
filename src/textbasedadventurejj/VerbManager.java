
package textbasedadventurejj;

import java.util.Map;


public class VerbManager {

    private static String[] verbs;//all possible verbs
    private static String[] responses;//all possible responses
    private static Map<String, Integer> aliases;

    public static void generateVerbs() {
        //implement logic to take acceptable verbs & responses from text file here
    }

    public static boolean verifyVerbName(String name) {
        return aliases.containsKey(name);
    }

    public static String modifyVerbName(String name) {
        if (!aliases.containsKey(name)) {
            return name;
        }
        return verbs[aliases.get(name)];
    }

    public static boolean verifyResponseName(String response) {
        boolean contains = false;
        for (String element : responses) {
            if (element.equals(response)) {
                contains = true;
            }
        }
        return contains;
    }

    public String[] getList() {
        return verbs;
    }
}
