package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class ReloadAction extends Action {
    /**
     * ammunition item and weapon item in player inventory 
     */
    private Item ammunition , weapon;
    /**
     * Constuctor 
     * @param ammunition ammunition item
     * @param weapon weapon item
     */
    public ReloadAction(Item ammunition , Item weapon){
        this.ammunition = ammunition;
        if(weapon.asWeapon() != null){
            this.weapon = weapon ; 
        }
    }

    /**
	 * Execute the ReloadAction
	 * @param actor the actpr to do the reload action 
	 * @param map the current gamemap
	 * @return String that indicate the success of reloading the weapon 
	 */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = String.format("%s reloads %s with %d rounds", actor,weapon,ammunition.effect());
        weapon.reload(ammunition.effect());
        actor.removeItemFromInventory(ammunition);
        return result;
    }
    
    /**
	 * @param actor 
	 * @return String 
	 */    
    @Override
    public String menuDescription(Actor actor) {
        return actor + " reloads " + weapon ;
    }
}