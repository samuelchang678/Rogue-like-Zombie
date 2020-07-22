package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;


public class Hand extends PortableWeapon{
    /**
     * Constructor for Hand
     * @param name name of the Hand
     * Having a CraftCapability.CRAFTABLE which means craft() will return a new PortableWeapon
     */
    public Hand(String name){
        super(name,'y',20,"swing");
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
     * @return Item ZombieClub 
     */
    @Override
    public Item craft() {
        return new ZombieClub(name+"'s ZombieClub");
    }


    
}
