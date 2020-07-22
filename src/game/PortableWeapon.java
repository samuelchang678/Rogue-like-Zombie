package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Base class for any WeaponItem 
 */
public abstract class PortableWeapon extends WeaponItem{

    /**
     * Constructor for PortableWeapon 
     * Dummy class for WeaponItem so we can have some specified method such as healingEffect,craft
     * @param name name of the item
     * @param displayChar display on the game map
     * @param damage damage dealt to enemy
     * @param verb verb of the action 
     */
    public PortableWeapon(String name, char displayChar,int damage,String verb) {
        super(name, displayChar, damage,verb);
        addCapability(WeaponCapability.MELEE);
    }
    
    /** 
     * To be Override by subclass if they have an effect of healing
     * healing Effect for the Weapon to dealt
     * @return int always return 0 
     */
    @Override
    public int effect() {
        return 0;
    }
    
    /**
     * To be Override by subclass if they can be crafted
     * Craft a Weapon into another Item
     * @return Item null
     */
    public Item craft() {
        return null;
    }  

   @Override
   public Boolean reload(int rounds) {
       return false;
   }
   @Override
   public Boolean fires() {
       return false;
   }
}