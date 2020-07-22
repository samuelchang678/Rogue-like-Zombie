package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;


public class ChooseShotgunAction extends Action {
    /**
     * weapon (shotgun)
     */
    private Weapon weapon; 
    /**
     * Constructor
     * @param weapon the shotgun 
     */
    public ChooseShotgunAction( Weapon weapon){
        this.weapon = weapon ;
    }

    /**
	 * Execute the ChooseShotgunAction
	 * @param actor the actpr that chooses to use the shotgun
	 * @param map the current gamemap
	 * @return String that indicate the success of using the shotgun  
	 */
    @Override
    public String execute(Actor actor, GameMap map) {
        Menu submenu = new Menu ();
        Actions actions = new Actions();
        for (Exit e : map.locationOf(actor).getExits()){
            actions.add(new AoeAction(e, weapon));
        }
        Action action = submenu.showMenu(actor, actions, new Display());
        String result = action.execute(actor, map);
        return result;
    }

    /**
	 * @param actor 
	 * @return String 
	 */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " choose to use " + weapon ;
    }
}