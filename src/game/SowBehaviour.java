package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// import java.util.Random;

import edu.monash.fit2099.engine.*;

public class SowBehaviour implements Behaviour {
    /**
     * Plant the crop on the crop if there is space
     * @param actor actor that will fertilise the crop (Farmer)
     * @param map current gamemap
     * @return Action , SowAction that will plant the crop at the ground of that location
     */
    @Override
    public Action getAction(Actor actor, GameMap map){
        if(Math.random()<=0.33){
            List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
            Collections.shuffle(exits);
            
            for (Exit e: exits) {
                if (e.getDestination().getGround().hasCapability(SowCapability.SOWABBLE)){
                    return new SowAction(e.getDestination());
                }
            }
		return null;
    }
    return null;   
    }
}

    
