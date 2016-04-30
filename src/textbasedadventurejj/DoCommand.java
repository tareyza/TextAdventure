
package textbasedadventurejj;

public class DoCommand implements Command {

	@Override
	public void execute(String[] words) {
		Interpreter.getInstance().interpretSentence(words);
	}
}
