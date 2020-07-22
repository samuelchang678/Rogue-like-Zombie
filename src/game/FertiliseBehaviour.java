package game;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

import edu.monash.fit2099.engine.*;

public class FertiliseBehaviour implements Behaviour {
    /**
     * Fertilise the crop if the a Farmer is standing on top of that crop
     * @param actor actor that will fertilise the crop (Farmer)
     * @param map current gamemap
     * @return Action , fertilise action that will fertlise the crop and reduce its turn to ripe
     */
    @Override
    public Action getAction(Actor actor , GameMap map){
        Location locationOfActor  = map.locationOf(actor);
        
            
        if (locationOfActor.getGround().hasCapability(HarvestCapability.NOTHARVESTABLE)){
            return new FertiliseAction(locationOfActor.getGround(),locationOfActor);
        }
        return null ;
   
    }

}