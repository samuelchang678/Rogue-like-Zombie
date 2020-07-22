package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


public class Leg extends PortableWeapon{
    /**
     * Constructor for leg
     * @param name name of the Leg
     * Having a CraftCapability.CRAFTABLE which means craft() will return a new PortableWeapon
     */
    public Leg(String name){
        super(name,'w',10,"bash");
        addCapability(CraftCapability.CRAFTABLE);
    }
    
    /**
     * if its in the player inventory we always clear the actions and add a craft action
     * @param currentLocation curennt location 
     * @param actor actor carrying the item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        allowableActions.clear();
        allowableActions.add(new CraftAction(this));
    }

    /**
     * if its on the ground then we are not going to have any action available
     * @param currentLocation curennt location 
     */
    @Override
    public void tick(Location currentLocation) {
        allowableActions.clear();
    }

    /** 
     * Method to call if were to craft
     * @return Item ZombieMace 
     */
    @Override
    public Item craft() {
        return new ZombieMace(name+"'s ZombieMace");
    }

   
}