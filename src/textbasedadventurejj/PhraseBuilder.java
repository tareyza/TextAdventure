
package textbasedadventurejj;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhraseBuilder {

    static String[] verbStructures;
    private static LocationManager lmanager = LocationManager.getInstance();

    private PhraseBuilder() {
    }

    static public void generateVerbStructures() {
        try {
            String verbStruct = Utils.readFile("STRUCTURE_FILE");
            verbStruct.trim();
            verbStructures = verbStruct.split("\\n");
        } catch (IOException ex) {
            Logger.getLogger(PhraseBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static public String[] getVerbStructures() {
        return verbStructures;
    }

    static public Phrase getPhrase(String[] inputArr) {
        Phrase phrase = new Phrase();
        phrase.setSubject(lmanager.parseObject(inputArr[inputArr.length - 1]));
        inputArr = java.util.Arrays.copyOf(inputArr, inputArr.length - 1);
        for (String sentence : verbStructures) {
            String[] phraseArr = sentence.split(" ");
            boolean match = true;
            if (phraseArr.length == inputArr.length) {
                for (int i = 0; i < phraseArr.length; i++) {
                    if (!(phraseArr[i].equals("INDIRECT") || phraseArr[i].equals("OBJECT") || phraseArr[i].equals("RESPONSE") || phraseArr[i].equals(inputArr[i]))) {
                        match = false;
                        break;
                    }
                }
            } else {
                match = false;
            }
            if (match) {
                for (int i = 1; i < phraseArr.length; i++) {
                        if(phraseArr[i].equals("INDIRECT")){
                            phrase.setIndirectObject(Utils.getObjectInCurrentRoom(inputArr[i]));
                        } else if(phraseArr[i].equals("OBJECT")){
                            phrase.setDirectObject(Utils.getObjectInCurrentRoom(inputArr[i]));
                        } else if(phraseArr[i].equals("RESPONSE")){
                            phrase.setResponse(inputArr[i]);
                        } 
                        phrase.setVerb(phraseArr[0]);
                }
                return phrase;
            }
        }
        return null;
    }
}
