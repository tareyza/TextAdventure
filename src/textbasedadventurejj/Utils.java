package textbasedadventurejj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Utils {

	public static String readFile(String fname) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fname));
		String s = null;
		String buffer = "";
		while ((s = reader.readLine()) != null) {
			buffer += s + "\n";
		}
		reader.close();
		return buffer;
	}

	public static void writeError(String input) throws IOException {
		FileWriter writer = new FileWriter(Constants.ROOT + Constants.ERROR_FILE, true);
		writer.write(input+"\n");
                
		writer.close();
	}
        
        public static void writeEvent(String input) throws IOException {
        if(input.equals("do exit player") || input.equals("do quit player")){
        	return;
        }
		FileWriter writer = new FileWriter(Constants.ROOT + Constants.SAVE_FILE, true);
		writer.write(input + "\n");
		writer.close();
	}

	public static String readFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String s = null;
		String buffer = "";
		while ((s = reader.readLine()) != null) {
			buffer += s + "\n";
		}
		reader.close();
		return buffer;
	}

	public static String getPathRoot(String fullPath) {
		String[] pathItems = fullPath.split("\\.");
		return pathItems[0];
	}

	public static String getPathTail(String fullPath) {
		String[] pathItems = fullPath.split("\\.");
		return pathItems[pathItems.length - 1];
	}

	public static String getPathHead(String fullPath) {
		int index = fullPath.lastIndexOf(".");
		if (index == -1)
			return "";
		return fullPath.substring(0, index);
	}

}
