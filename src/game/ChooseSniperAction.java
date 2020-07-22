package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.NumberRange;
import edu.monash.fit2099.engine.Weapon;

public class ChooseSniperAction extends Action {
    /**
     * weapon (sniper)
     */
    private Weapon weapon;
      /**
     * Constructor
     * @param weapon the sniper 
     */
    public ChooseSniperAction( Weapon weapon) {
        this.weapon = weapon;
    }

     /**
	 * Execute the ChooseSniperAction
	 * @param actor the actpr that chooses to use the sniper
	 * @param map the current gamemap
	 * @return String that indicate the success of using the sniper  
	 */
    @Override
    public String execute(Actor actor, GameMap map) {
        Menu submenu = new Menu();
        Actions actions = new Actions();
    
        Location locationOfActor = map.locationOf(actor);
        
        int x =locationOfActor.x();
        int y = locationOfActor.y();

        NumberRange xnumberRange =  map.getXRange();
        NumberRange yNumberRange = map.getYRange();

        int minXRange = Math.max(x-10, xnumberRange.min());
        int minYRange = Math.max(y-10, yNumberRange.min());
        int maxXRange = Math.min(x+10, xnumberRange.max());
        int maxYRange = Math.min(y+10, yNumberRange.max());


        for (int i = minXRange  ; i <= maxXRange ; i++ ){
            for (int j = minYRange ; j <= maxYRange ; j++){
                if(!(i == x && j == y)){
                    if(map.at(i, j).containsAnActor()){
                       actions.add(new ChooseTargetAction(map.at(i, j).getActor(), weapon, map));
                    }
                }
            }
        }
        if(actions.size() == 0){
            String result = "There is no target near the player";
            Action action = new DoNothingAction();
            action.execute(actor, map);
            return result;
        }
        Action action = submenu.showMenu(actor, actions, new Display());
        String result = action.execute(actor,map);

        return result;
    }
     /**
	 * @param actor 
	 * @return String 
	 */
    @Override
    public String menuDescription(Actor actor) {
        return actor +  " choose to use " + weapon;
    }
}