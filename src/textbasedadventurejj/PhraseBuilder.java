
package textbasedadventurejj;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhraseBuilder {

	static{
		generateVerbStructures();
	}
	
	private static String[] verbStructures;
	private static LocationManager lmanager = LocationManager.getInstance();

	private PhraseBuilder() {
	}

	public static void generateVerbStructures() {
		try {
			String verbStruct = Utils.readFile(Constants.ROOT + Constants.STRUCTURE_FILE);
			verbStruct.trim();
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
		System.out.println(java.util.Arrays.toString(inputArr));
		inputArr = java.util.Arrays.copyOf(inputArr, inputArr.length - 1);
		System.out.println(java.util.Arrays.toString(inputArr));
		for (String sentence : verbStructures) {
			String[] phraseArr = sentence.split(" ");
			boolean match = true;
			if (phraseArr.length == inputArr.length) {
				for (int i = 0; i < phraseArr.length; i++) {
					if (!(phraseArr[i].equals("INDIRECT") || phraseArr[i].equals("OBJECT")
							|| phraseArr[i].equals("RESPONSE") || phraseArr[i].equals(inputArr[i]))) {
						match = false;
						break;
					}
				}
			} else {
				match = false;
			}
			if (match) {
				String verb = "";
				for (int i = 0; i < phraseArr.length; i++) {
					if (phraseArr[i].equals("INDIRECT")) {
						phrase.setIndirectObject(lmanager.getObjectInCurrentRoom(inputArr[i]));
					} else if (phraseArr[i].equals("OBJECT")) {
						phrase.setDirectObject(lmanager.getObjectInCurrentRoom(inputArr[i]));
					} else if (phraseArr[i].equals("RESPONSE")) {
						phrase.setResponse(inputArr[i]);
					}else{
						verb += phraseArr[i] + " ";
					}
				}
				phrase.setVerb(verb.trim());
				System.out.println(phrase);
				return phrase;
			}
		}
		return null;
	}
}
