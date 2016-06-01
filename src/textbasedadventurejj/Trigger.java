package textbasedadventurejj;

public class Trigger implements Comparable<Trigger> {

	private String verb;
	private GameObject object;// indirect object

	public Trigger(String verb, GameObject object) {
		this.verb = verb;
		this.object = object;
	}

	public Trigger(String verb) {
		this(verb, GameObject.NOTHING);
	}

	public GameObject getObject() {
		return object;
	}

	public String getVerb() {
		return verb;
	}

	@Override
	public String toString() {
		if (object != null)
			return String.format("(%s,%s)", verb, object);
		return "(" + verb + ")";
	}

	@Override
	public boolean equals(Object obj) {
		Trigger trigger = (Trigger) obj;
		if(trigger.object == null && object == null)
			return trigger.verb.equals(verb);
		if(trigger.object != null && object != null)
			return trigger.verb.equals(verb) && trigger.object.equals(object);
		return false;
	}

	@Override
	public int compareTo(Trigger trigger) {
		GameObject compare = trigger.getObject();
		if (object == null)
			return -1;
		if (object.equals(compare)) {
			return 0;
		}
		if (object.isA(compare)) {
			return 1;
		}
		return -1;
	}
	
	@Override public int hashCode(){
		if(object == null)
			return verb.hashCode();
		return object.hashCode() + verb.hashCode();
	}
}
