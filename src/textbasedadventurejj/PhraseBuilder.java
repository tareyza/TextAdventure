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
        for (String sentence : gameObjectAliases) {
            String replaceWith = sentence.substring(0, sentence.indexOf(" "));
            String matchTo = sentence.substring(sentence.indexOf(" ") + 1);
            String wordsString = "";
            for (int i = 0; i < words.length; i++) {
                wordsString += words[i];
            }
            if(wordsString.contains(matchTo)){
                int index = wordsString.indexOf(matchTo);
                wordsString = wordsString.substring(0, index) + replaceWith + wordsString.substring(index+replaceWith.length());
                return wordsString.split(" ");
            }
            
        }
        return words;
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
                        phrase.setIndirectObject(lmanager.getObjectInCurrentRoom(inputArr[i]));
                    } else if (phraseArr[i].equals("OBJECT")) {
                        phrase.setDirectObject(lmanager.getObjectInCurrentRoom(inputArr[i]));
                    } else if (phraseArr[i].equals("RESPONSE")) {
                        phrase.setResponse(inputArr[i]);
                    } else {
                        verb += phraseArr[i] + " ";
                    }
                }
                phrase.setVerb(verb.trim());
                System.out.println("phrase is: " + phrase.toString());
                return phrase;
            }
        }
        return null;
    }
}
