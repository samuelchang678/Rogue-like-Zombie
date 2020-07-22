package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;


public class ChooseTargetAction extends Action {
    private Actor target;
    private Weapon weapon ;
    private GameMap map;
    /**
     * Constructor 
     * @param target the actor to be shot/aim at 
     * @param weapon the sniper
     * @param map the current gamemap 
     */
    public ChooseTargetAction(Actor target, Weapon weapon, GameMap map){
        this.target = target;
        this.weapon = weapon;
        this.map = map ;

    }

    /**
     * Execute the ChooseTargetAction
     * return the focus counter of the player if the player was aiming the previous round
     * then increase the focus counter 
     * @param actor  the player using the sniper
     * @param map the current gamemap
     * @return String indicating the sucess of the aim/shoot action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Menu submenu = new Menu();
        int focus  = actor.getFocus(this.target);
        Actions actions = new Actions();
        if (focus < 2){ 
            actions.add(new AimAction(target));
        }
        actions.add(new ShootAction(target, weapon));

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
        return String.format(" %s selects %s at x = %d, y = %d", actor,target,this.map.locationOf(target).x(),this.map.locationOf(target).y());
    }
}