package textbasedadventurejj;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Loader{

    public Location load(File root){
        if(!root.isDirectory())
            throw new IllegalArgumentException("Not a directory");
        File[] files = root.listFiles();
        for(File file : files){
            if(file.getName().equals("head")){
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(root));
                    String s;
                    while((s=reader.readLine()) != null){
                        
                    }
                }catch(Exception ex){ }
            }
        }
        return null;
    }

    public void write(Location location){
        
    }
}
