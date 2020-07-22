package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {

	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints);
		addCapability(team);
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}
	
	@Override
	public void harvest(Ground item,GameMap map) {
		return;
	}

	@Override
	public boolean craft(Item item){
		return false;
	}

	@Override
	public Boolean attack(Weapon weapon, Actor target, GameMap map) {
		return false;
	}

	@Override
	public boolean eat(Item item,GameMap location) {
		return false;
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean checkhand() {
		return false;
	}

	@Override
	public int getFocus(Actor actor) {
		return 0;
	}

	@Override
	public boolean setFocus(Actor actor) {
		return false;
	}
}
