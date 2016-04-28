/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textbasedadventurejj;

import java.util.Map;

/**
 *
 * @author Jerry
 */
public class VerbManager {

    private static String[] verbs;//all possible verbs
    private static Map<String, Integer> aliases;

    public static void generateVerbs() {
        //implement logic to take acceptable verbs from text file here
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

    public String[] getList() {
        return verbs;
    }
}
