package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	protected int focus ;
	protected Actor target = null;
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.focus = 0 ;
	}
	
	/** Player available action at curernt turn
	 * @param actions List of available action
	 * @param lastAction last action done
	 * @param map current game map
	 * @param display 
	 * @return Action , available Action can be chosen
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		Action action= menu.showMenu(this, actions, display);
		if (lastAction.getClass()!= action.getClass()){
			this.focus = 0;
		}
		return action;
	}

	@Override
	public void hurt(int points) {
		this.focus = 0;
		super.hurt(points);
	}
	
	/** 
	 * harvest the Ground and add into inventory
	 * @param item Ground to be harvested
	 * @param map current game map
	 * @return 
	 */
	@Override
	public void harvest(Ground item,GameMap map) {
		try {
			addItemToInventory(new Food("berry",10));
			return;
		} catch (NullPointerException nullPointer) {
			nullPointer.printStackTrace();
		}
		return;
	}
	
	/** 
	 * Craft the Item
	 * @param item item to be crafted
	 * @return boolean true if successfully crafted else false
	 */
	@Override
	public boolean craft(Item item) {
		if (item.craft()!=null){
			removeItemFromInventory(item);
			try{
				addItemToInventory(item.craft());
				return true;
			}catch (NullPointerException nullPointer){
				nullPointer.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Weapon getWeapon() {
		for (Item item : inventory){
			if(item.asWeapon() != null){
				return item.asWeapon();
			}
		}
		return getIntrinsicWeapon(); 
	}
	
	/** 
	 * eating the Item
	 * @param item item to be eaten
	 * @param location location of the item
	 * @return boolean true
	 */
	@Override
	public boolean eat(Item item,GameMap map) {
		try {
			heal(item.effect());

			//go thru inventory check if its in inventory and remove
			for (Item i : getInventory()){
				if (item.equals(i)){
					removeItemFromInventory(item);
					return true;
				}
			}
			//eating from ground so remove from current location
			map.locationOf(this).removeItem(item);
			return true;

		} catch (NullPointerException nullPointer) {
			nullPointer.printStackTrace();
		}
		return false;
	}


	/** 
	 * @param Weapon
	 * @return boolean
	 */
	@Override 
	public Boolean attack(Weapon weapon, Actor target,GameMap map ){
		return true;
	}

	@Override
	public int getFocus(Actor actor) {
		if (this.target != actor){
			this.focus = 0;
		}
		return this.focus;
	}

	@Override
	public boolean setFocus(Actor actor ) {
		if(this.target == null){
			this.focus ++ ;
			this.target = actor ; 
		}
		else if (this.target==actor){
			this.focus ++;
		}
		else if(this.target!=actor){
			this.target = actor ; 
			this.focus = 0 ;
			this.focus ++;
		}
		return true; 
	}
}
