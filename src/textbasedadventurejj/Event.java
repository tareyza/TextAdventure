package textbasedadventurejj;

public class Event {

	private String name;
	private String[] lines;

	public Event(String name, String... lines) {
		this.name = name;
		this.lines = lines;
	}

	public String getName() {
		return name;
	}

	public String[] getLines() {
		return lines;
	}

	public int getLength() {
		return lines.length;
	}

	@Override
	public String toString() {
		return name;
	}
}
