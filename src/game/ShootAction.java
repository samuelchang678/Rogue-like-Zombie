package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;


public class ShootAction extends AttackAction {
    private Weapon weapon ;
    /**
     * Constructor 
     * @param target the target to be shot at 
     * @param weapon the sniper 
     */
    public ShootAction(Actor target , Weapon weapon){
        super(target);
        this.weapon = weapon;
    }
    /** 
     * Execute the ShootAction 
     * return the focus of the player and depending on the focus set the damge to the target
     * @param actor the player using the sniper
     * @param map the current gamemap
     * @return String indicating the damgage done to the target 
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        
        Item weaponItem = (Item) weapon;
        Boolean hasBullet = weaponItem.fires();
        if (!hasBullet){
            return weapon + " is out of ammunition !!!!";
        }
        String result = actor + " snipes " +target +" for ";
        int focus = actor.getFocus(this.target);
        if (focus == 0){
            if (rand.nextInt(100)<25){
                return actor + "misses" + target;
            }
            target.hurt(weapon.damage());
            result += weapon.damage();
        }
        else if(focus == 1){
            if(rand.nextInt(100)<10){
                return actor + "misses" + target;
            }
            target.hurt(weapon.damage()*2);
            result += weapon.damage()*2;
        }
        else{
           target.hurt(weapon.damage()*40000);
        }
        actorDead(target,map,result);
        return result;
    }

    /**
	 * @param actor 
	 * @return String 
	 */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots " + target;
    }
    
    
}