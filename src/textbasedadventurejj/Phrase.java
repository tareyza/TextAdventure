
package textbasedadventurejj;

public class Phrase {

	private GameObject object = GameObject.NOTHING, subject = GameObject.NOTHING, indirect = GameObject.NOTHING;
	private String response;
	private String verb;

	public Phrase() {

	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public GameObject getDirectObject() {
		return object;
	}

	public void setDirectObject(GameObject object) {
		this.object = object;
	}

	public GameObject getIndirectObject() {
		return indirect;
	}

	public void setIndirectObject(GameObject indirect) {
		this.indirect = indirect;
	}

	public GameObject getSubject() {
		return subject;
	}

	public void setSubject(GameObject subject) {
		this.subject = subject;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}
	
	@Override public String toString(){
		return "verb:" + verb 
				+ "\nsubject:" + subject + "\nobject:" 
				+ object + "\niobject:" + indirect + "\nresponse:" + response;
	}
}
