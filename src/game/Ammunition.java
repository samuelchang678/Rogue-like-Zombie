package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Ammunition extends PortableItem {
    private int rounds; 
    /**
     * Constructor 
     * @param name item name 
     * @param rounds the number of rounds it has
     */
    public Ammunition (String name , int rounds ){
        super(name,'a');
        addCapability(WeaponCapability.AMMUNITION);
        this.rounds = rounds;
    }   

     /**
     * if its in the player inventory we always clear the actions and add a craft action
     * @param currentLocation curennt location 
     * @param actor actor carrying the item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        for(Item i : actor.getInventory()) {
            if (i.hasCapability(WeaponCapability.RANGE)){
                allowableActions.add(new ReloadAction(this, i));
            }
        }
    }

     /**
     * if its on the ground then we are not going to have any action available
     * @param currentLocation curennt location 
     */
    @Override
    public void tick(Location currentLocation) {
        allowableActions.clear();
    }
    @Override
    public int effect() {
        return this.rounds;
    }
    
    @Override
    public Boolean reload(int rounds) {
        return false;
    }
}