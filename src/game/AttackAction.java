
package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;


/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	protected Exit exit;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();
	protected boolean val;

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	/**
	 * check if an actor is dead and spawn corpse
	 * @param target the target being hurt
	 * @param map current game map
	 * @param result string indicating whether the target is dead
	 * @return
	 */
	protected String actorDead(Actor target , GameMap map , String result) {
		
		if(!target.isConscious()){
			if (target.hasCapability(ZombieCapability.ALIVE)){
				int turnToRaise=rand.nextInt(5);
				Corpse body = new Corpse("dead " + target,turnToRaise);
				map.locationOf(target).addItem(body);
			}
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";

		}
		return result;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		
		Weapon weapon = actor.getWeapon();
		String result = actor + " " + weapon.verb() + " "+  target + " for " + weapon.damage() + " damage.";
		Boolean val = actor.attack(weapon,actor,map);
		
		if(!val){
			return actor + " misses " + target;
		}
		int damage = weapon.damage();
		if(weapon instanceof WeaponGuns){
			damage = (int) damage/3;
			result = actor + " swings "+  target + " for " + damage/3;
		}
		target.hurt(damage);
		return actorDead(target,map,result);
	}

	@Override
	public String menuDescription(Actor actor) {
		return  actor + " attacks " + target;
	}
}
