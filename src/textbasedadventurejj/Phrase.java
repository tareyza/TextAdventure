
package textbasedadventurejj;

public class Phrase {
    
    private GameObject object, subject;
    private String verb;

    /**
     * @return the object
     */
    public GameObject getDirectObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setDirectObject(GameObject object) {
        this.object = object;
    }

    /**
     * @return the subject
     */
    public GameObject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(GameObject subject) {
        this.subject = subject;
    }

    /**
     * @return the verb
     */
    public String getVerb() {
        return verb;
    }

    /**
     * @param verb the verb to set
     */
    public void setVerb(String verb) {
        this.verb = verb;
    }
}
