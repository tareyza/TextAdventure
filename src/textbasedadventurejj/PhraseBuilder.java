/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textbasedadventurejj;

import java.util.ArrayList;

/**
 *
 * @author Jerry
 */
public class PhraseBuilder {

    ArrayList<String> verbStructures = new ArrayList<>();

    private PhraseBuilder() {
    }

    public void generateVerbStructures() {
        //implement later
    }

    public String[] getVerbStructures() {
        return (String[]) verbStructures.toArray();
    }

    public Phrase getPhrase(String input) {
        String[] inputArr = input.split(" ");
        for (String phrase : verbStructures) {
            String[] phraseArr = phrase.split(" ");
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
                return 
            }
        }
    }
}
