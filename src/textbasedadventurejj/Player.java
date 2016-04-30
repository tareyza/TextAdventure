
package textbasedadventurejj;

public class Player extends GameObject {
    
    private int health;
    private GameObject equippedWeapon;
    private int weaponDamage;
    
    public Player(String name) {
        super(name);
    }
    
    public void equipWeapon(GameObject newWeapon) {
        equippedWeapon = newWeapon;
        weaponDamage = (int) equippedWeapon.getProperties().get("damage");
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
        return LocationManager.getInstance().getObject(name);
    }
}
