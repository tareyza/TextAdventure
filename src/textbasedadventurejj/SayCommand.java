package textbasedadventurejj;

public class SayCommand implements Command {

    @Override
    public void execute(String[] words) {
        if (words.length == 1 && words[0].length() > 5 && words[0].substring(0, 5).equals("World")) {
            int index = words[0].indexOf(".");
            while (index != -1) {
                words[0] = words[0].substring(index+1);
                index = words[0].indexOf(".");
            }
            words[0] = words[0].toUpperCase();
            if(words[0].contains("ROOM")){
            words[0] = words[0].substring(0,words[0].indexOf("ROOM"));
            words[0]+=" ROOM";
            }
            System.out.println("");
            System.out.println(words[0]);
        } else {
            String buffer = "";
            for (String s : words) {
                buffer += s + " ";
            }
            System.out.println(buffer.trim());
        }
    }

}
