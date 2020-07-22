package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class HarvestBehaviour implements Behaviour{

    /** 
     * Harvest the Crop on the standing location of the Actor
     * @param actor actor to harvest (Player , Farmer)
     * @param map current gamemap
     * @return Action ,HarvestAction which will harvest the crop at the location 
     */
    @Override
    public Action getAction(Actor actor, GameMap map){
        
        List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
        Collections.shuffle(exits);
        Location locationOfActor = map.locationOf(actor);
        
        for (Exit e: exits) {
            if (e.getDestination().getGround().hasCapability(HarvestCapability.HARVESTABLE)){
                try {
                    return new HarvestAction(e.getDestination().getGround(),e.getDestination());
                } catch (IllegalArgumentException b) {
                    b.printStackTrace();
                    return null;
                }
            }
        }
        if (locationOfActor.getGround().hasCapability(HarvestCapability.HARVESTABLE)){
            try{
                return new  HarvestAction(locationOfActor.getGround(),map.locationOf(actor));
            }
            catch (IllegalArgumentException b) {
                b.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
