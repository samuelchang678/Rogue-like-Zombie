package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.GameMap;


public class CraftAction extends Action {
    /**
	 * The Item that is to be Crafted
	 */
    protected Item target;

    /**
	 * Constructor.
	 * throws IllegalArgumentException if the weapon is null
	 * @param target the Item to be crafted
	 */
    public CraftAction(Item weapon) {
        if (weapon!=null){
            this.target = weapon;
        }
        else{
            throw new IllegalArgumentException("weapon is null");
        }
    }

    
    /** 
	 * Execute the CraftAction
	 * @param actor actor to craft the item
	 * @param map current game map
	 * @return String indicates its success or failed crafting the Item
	 */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " " + "crafted" + " " +target;
        try {
            if (actor.craft(target)){
                result+=" success";
                return result;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		return result+" failed";
    }

    
    /** 
	 * menu Description mainly for player
	 * @param actor actor to show the menu 
     * @return String description of the action
     */
    @Override
	public String menuDescription(Actor actor) {
		return actor + " craft " + target ;
	}
}