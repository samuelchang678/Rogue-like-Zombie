package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class Sniper  extends WeaponGuns{

    /**
     * constructor for Sniper
     * @param name weapon name 
     */
    public Sniper(String name){
        super(name,'S',45,"snipes");
        addCapability(WeaponCapability.RANGE);
    }
    
    /**
     * if its in the player inventory we always clear the actions and add a craft action
     * @param currentLocation curennt location 
     * @param actor actor carrying the item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        allowableActions.clear();
        allowableActions.add(new ChooseSniperAction(this));
    }

    /**
     * if its on the ground then we are not going to have any action available
     * @param currentLocation curennt location 
     */
    @Override
    public void tick(Location currentLocation) {
        allowableActions.clear();
    }
}
    