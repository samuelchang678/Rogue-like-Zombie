
package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	/**
	 * Behaviour of a Human Actor
	 */
	protected Behaviour[] behaviours = {new EatBehaviour(),
		new WanderBehaviour()};
	private Random rng = new Random();

	/**
	 * The default constructor creates default Humans
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	/**
	 * Randomly choose an action in defined behaviour
	 * @param actions actions available
	 * @param lastAction last action made
	 * @param map current game map
	 * @param display the Display where the Human's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Action action = behaviours[rng.nextInt(behaviours.length)].getAction(this, map);
		if (action!=null)
			return action;
		return new DoNothingAction(); 
	}
	
	/**
	 * Eat the Item given
	 * If the Human is hurted they are going to eat the Item 
	 * @param item item to be eaten
	 * @param location location of the Item
	 * @return true if they eat the Item else false
	 */
	@Override
	public boolean eat(Item item,GameMap map) {
		try {
			if (hitPoints<maxHitPoints){
				heal(item.effect());
				map.locationOf(this).removeItem(item);
				return true;
			}
		} catch (NullPointerException nullPointer) {
			nullPointer.printStackTrace();
		}
		return false;
	}

	

}
