package textbasedadventurejj;

import java.util.Scanner;

public class Test {

    public static void main(String[] argv) {

        Location closet = new Location("closet");
        closet.getChildren().put(null, GameObject.NOTHING)
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
            String nextLine = scanner.nextLine();
            Interpreter.getInstance().interpret(nextLine);
        }

    }
}
