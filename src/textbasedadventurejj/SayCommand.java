
package textbasedadventurejj;

public class SayCommand implements Command {

	@Override
	public void execute(String[] words) {
		String buffer = "";
		for (String s : words) {
			buffer += s + " ";
		}
		System.out.println(buffer.trim());
	}

}
