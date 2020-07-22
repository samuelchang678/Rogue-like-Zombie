package game;

import edu.monash.fit2099.engine.*;

public class EatBehaviour implements Behaviour{
    
    /** 
     * Eat behaviour 
     * Always eat the one on the ground first then only Inventory 
     * @param actor actor who have the eat bahaviour (Player & human)
     * @param map current game map
     * @return Action EatAction 
     */
    @Override
    public Action getAction(Actor actor, GameMap map){
        
        Location locationOfActor = map.locationOf(actor);
        
        //check the patch of ground player standing on
        for (Item item : locationOfActor.getItems()){
            if (item.hasCapability(EatCapability.EDIBLE)){
                try {
                    return new EatAction(item);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
		return null;
    }
}