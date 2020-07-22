package game;

import edu.monash.fit2099.engine.*;

/**
 * A Famer 
 * 
 * Farmer is able to sow a crop on any adjacent ground and fertilise a crop if standing on it
 * 
 * @author ram
 */

public class Farmer extends Human {
    /**
     * Constructor
     * @param name name of Farmer
     * @param hitPoints health of Farmer
     */
    public Farmer(String name , int hitPoints){
        super(name,'F', hitPoints);
        behaviours = new Behaviour[] {
            new HarvestBehaviour(),
            new SowBehaviour(),
            new FertiliseBehaviour(),
            new WanderBehaviour()};
    }


    /**
     * Farmer harvesting the Crop and it will drop underneath the farmer
     * @param item the crop to be hervest
     * @param map the current game map
     * @return boolean 
     */
    @Override
	public void harvest(Ground item,GameMap map) {
        try {
            new Food("berry", 10).getDropAction().execute(this, map);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return;
    }
    
    /**
     * Farmer eat the Item
     * @param item food to be eaten
     * @param lcoation the location of the food 
     * @return false ,Farmer cannot eat any item so it return false
     */
    @Override
    public boolean eat(Item item,GameMap location) {
        return false;
    }
}