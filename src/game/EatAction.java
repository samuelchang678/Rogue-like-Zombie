package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.GameMap;

public class EatAction extends Action {
    /**
	 * The Food that is to be Harvest
	 */
    protected Item target;

    /**
     * The location of food if its picked up from ground
     */
    protected Location location;

    /**
	 * Constructor.
	 * throws IllegalArgumentException if the food is null
	 * @param food the Food to harvest
     * @param location the location of the food
	 */
    public EatAction(Item food) {
        if (food!=null){
            this.target = food;
        }
        else {
            throw new IllegalArgumentException("food is null");
        }
    }

    /** 
     * execute the Action
     * @param actor actor to execute the action
     * @param map current gamemap
     * @return String indicate whether they found the food or they ate it
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " " + "ate" + " " +target.toString();
        try {
            if (actor.eat(target,map)){
                return result;
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        return actor + " found " +target.toString();
    }

    
    /** 
     * menu Description mainly for player
     * @param actor actor of the menu showing
     * @return String description of the action
     */
    @Override
	public String menuDescription(Actor actor) {
		return actor + " eat " + target;
	}
}