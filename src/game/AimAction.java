package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


public class AimAction extends Action {
    private Actor target ;
    /**
     * 
     * @param target the actor that the player is aiming at 
     */
    public AimAction(Actor target ){
        this.target = target ;
    }
    /**
     * Execute the AimAction 
     * @param actor the player 
     * @param map the current gamemap
     * @return String indicating the sucess of aiming 
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.setFocus(this.target);
        String result = actor + " aims " + target +" for " + actor.getFocus(this.target) +" turns";
        return result;
    }

    /**
	 * @param actor 
	 * @return String 
	 */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " aims at " + target;
    }
    
    
}