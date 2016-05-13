
package textbasedadventurejj;

public class DoCommand implements Command {

	@Override
	public void execute(String[] words) {
		System.out.println(java.util.Arrays.toString(words));
		if(words.length == 2 && words[0].equals("debug")){
			System.out.println(LocationManager.getInstance().getContext());
		}
		Interpreter.getInstance().interpretSentence(words);
	}
}
