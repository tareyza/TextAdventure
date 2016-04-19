
package textbasedadventurejj;

import java.util.ArrayList;


public class Player extends GameObject {
    
    private int health;
    private GameObject equippedWeapon;
    private int weaponDamage;
    ArrayList<GameObject> items = new ArrayList<>();
    
    public Player(String state, String name) {
        super(state, name);
    }
    
    public void addGameObject(GameObject object) {
        items.add(object);
    }
    
    public GameObject[] getInventory() {
        GameObject[] arr = items.toArray(new GameObject[items.size()]);
        return arr;
    }
    
    public boolean hasObject(GameObject target) {
        boolean exists = false;
        for (GameObject obj : items) {
            if (obj.equals(target)) {
                exists = true;
            }
        }
        return exists;
    }
    
    public void equipWeapon(GameObject newWeapon) {
        equippedWeapon = newWeapon;
        weaponDamage = (int) equippedWeapon.getProperty("damage");
    }
    
    public GameObject getEquippedWeapon() {
        return equippedWeapon;
    }
    
    public void setHealth(int n) {
        health = n;
    }
    
    public void changeHealth(int n) {
        health += n;
    }
    
    public boolean checkIsDead() {
        return health <= 0;
    }
    
    public void setGameObjectState(GameObject target, String newState) {
        for (GameObject obj : items) {
            if (obj.equals(target)) {
                obj.setState(newState);
            }
        }
    }
    
    public GameObject getObjectFromInventory(String name) {
        return Interpreter.getInstance().parseObject(name);
    }
}
