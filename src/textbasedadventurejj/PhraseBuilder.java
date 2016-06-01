package textbasedadventurejj;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhraseBuilder {

    static {
        generateVerbStructures();
        generateGameObjects();
    }

    private static String[] verbStructures;
    private static String[] gameObjectAliases;

    private static LocationManager lmanager = LocationManager.getInstance();

    private PhraseBuilder() {
    }

    public static String[] replaceGameObjects(String[] words) {
        String wordsString = "";
        for (int i = 0; i < words.length; i++) {
            wordsString += words[i] + " ";
        }
        for (String sentence : gameObjectAliases) {
            String replaceWith = sentence.substring(0, sentence.indexOf(" "));
            String matchTo = sentence.substring(sentence.indexOf(" ") + 1).trim();

            wordsString = wordsString.trim();

            if (wordsString.contains(matchTo)) {
                if (wordsString.charAt(wordsString.indexOf(matchTo) - 1) == ' ' && wordsString.charAt(wordsString.indexOf(matchTo) + matchTo.length()) == ' ') {
                    //System.out.println("match to: " + matchTo);
                    //System.out.println("replace with: " + replaceWith);
                    wordsString = wordsString.replace(matchTo, replaceWith);
                    //System.out.println("we are returning: " + wordsString);
                }
            }

        }

        return wordsString.split(" ");
    }

    public static void generateGameObjects() {
        try {
            String gameObjectNames = Utils.readFile(Constants.ROOT + Constants.GAME_OBJECT_FILE);
            gameObjectNames = gameObjectNames.trim();
            gameObjectAliases = gameObjectNames.split("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateVerbStructures() {
        try {
            String verbStruct = Utils.readFile(Constants.ROOT + Constants.STRUCTURE_FILE);
            verbStruct = verbStruct.trim();
            verbStructures = verbStruct.split("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String[] getVerbStructures() {
        return verbStructures;
    }

    public static Phrase getPhrase(String[] inputArr) {
        Phrase phrase = new Phrase();
        phrase.setSubject(lmanager.getObject(inputArr[inputArr.length - 1]));
        inputArr = java.util.Arrays.copyOf(inputArr, inputArr.length - 1);
        for (String sentence : verbStructures) {
            String[] phraseArr = sentence.split(" ");
            String[] phraseAlias = Arrays.copyOf(phraseArr, phraseArr.length);
            boolean sentenceMatches = true;
            if (phraseArr.length == inputArr.length) {
                for (int i = 0; i < inputArr.length; i++) {
                    if (phraseArr[i].equals("INDIRECT") || phraseArr[i].equals("OBJECT") || phraseArr[i].equals("RESPONSE")) {
                        phraseAlias[i] = inputArr[i];
                    }
                }
                for (int i = 0; i < inputArr.length; i++) {
                    if (!phraseAlias[i].equals(inputArr[i])) {
                        sentenceMatches = false;
                    }
                }
            } else {
                sentenceMatches = false;
            }
            if (sentenceMatches) {
                String verb = "";
                for (int i = 0; i < phraseArr.length; i++) {
                    if (phraseArr[i].equals("INDIRECT")) {
                        phrase.setIndirectObject(lmanager.getObject(inputArr[i]));
                    } else if (phraseArr[i].equals("OBJECT")) {
                        phrase.setDirectObject(lmanager.getObject(inputArr[i]));
                    } else if (phraseArr[i].equals("RESPONSE")) {
                        phrase.setResponse(inputArr[i]);
                    } else {
                        verb += phraseArr[i] + " ";
                    }
                }
                //System.out.println("here");
                if ((phraseArr[0].equals("look") || phraseArr[0].equals("examine")) && (phrase.getDirectObject() == GameObject.NOTHING || phrase.getDirectObject() == null)) {
                  //  System.out.println("inside look/examine part, here's getName: "+lmanager.getContext().getName());
                    phrase.setDirectObject(lmanager.getObject(lmanager.getContext().getName().toLowerCase()));
                }
                phrase.setVerb(verb.trim());
                //System.out.println(phrase.toString());
                return phrase;
            }
        }
        return null;
    }

}
