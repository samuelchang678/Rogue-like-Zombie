package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */

public class Zombie extends ZombieActor {
	/**
	 * Behaviour of a Zombie
	 */
	private Behaviour[] behaviours = {
			new PickUpBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new SpeakBehaviour(),
			new WanderBehaviour()
	};
	/**
	 * Constant of limbs for a single zombie 
	 */
	private static final int LIMBSCOUNT=2;
	/**
	 * random number/boolean generator
	 */
	private Random rng = new Random();
	/**
	 * game map to track zombie position
	 */
	private GameMap map ;
	/**
	 * HandCount of a Zombie
	 */
	private int handCount=LIMBSCOUNT;
	/**
	 * LegCount of a zombie
	 */
	private int legCount=LIMBSCOUNT;
	/**
	 * the current turn number
	 */
	private int turn=0;

	/**
	 * Zombie Constructtor
	 * @param name name of Zombie
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}

	
	/** 
	 * if the zombie have hand it will choose at random to return a punch or bite
	 * if the zombie has no hands then it will be able to bite only
	 * @return IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if (this.handCount>0){
			if (Math.random()<0.5)
			return new IntrinsicWeapon(10, "punch");
		}
		return new IntrinsicWeapon(30, "bite");
	}

	/**
	 * method exclusive for Zombie to do drop hand
	 * If we have two hand and we have a 50% change to droping the Weapon that the Zombie holding
	 * Else we only have one hand left we always drop the Weapon that the Zombie holding
	 * Drop Zombie Hand at last
	 */
	private void dropHand(){
		try {
			if (handCount==LIMBSCOUNT){
				if (rng.nextBoolean()){
					for (Item item : getInventory()) {
						if (item.asWeapon() != null)
							item.getDropAction().execute(this,map);
							break;
					}
				}
			}	
			else if (handCount <=LIMBSCOUNT){
				for (Item item : getInventory()) {
					if (item.asWeapon() != null)
						item.getDropAction().execute(this,map);
						break;
				}
			}	
			Hand hand = new Hand(name+"'s hand");
			hand.getDropAction().execute(this,map);
			handCount--;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * method exclusive for Zombie to do drop leg
	 * Drop leg 
	 * Set the current turn to 0 so that the zombie can always move at next turn after they drop their leg 
	 */
	private void dropLeg(){
		Leg leg = new Leg(name+"'s Leg");
		leg.getDropAction().execute(this,map);
		turn=0;
		legCount--;
	}
	
	
	/** 
	 * Override the Actor's hurt function
	 * There is a 25% to drop one of the Zombie Limbs if the zombie get hurts
	 * randomly drop hand / leg , if the first priority no longer exist ,drop the other part
	 * @param points
	 */
	@Override
	public void hurt(int points) {
		hitPoints-=points;
		if (rng.nextDouble()<0.25){
			int random=rng.nextInt(2);
			if (random==0){
				if(handCount>0){
					dropHand();
				}
				else if (legCount>0){
					dropLeg();
				}
			}
			else{
				if (legCount>0){
					dropLeg();
				}
				else if (handCount>0){
					dropHand();
				}
			}
		}
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		turn++;
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			this.map = map;
			if (action != null){
				return action;
			}
		}
		return new DoNothingAction();	
	}

	/** 
	 * if the weapon have the word bite that mean that a bite action is being carried out
	 * and the zombie will heal a set amount of health(5)
	 * @param weapon
	 * @return boolean
	 */
	@Override
	public Boolean attack (Weapon weapon, Actor target, GameMap map){
		Random rand = new Random();
		if(weapon.verb() == "bite"){
			if (rand.nextInt(100)<75){
				heal(5);
				return true;
			}
			return false;
		}
		else{
			return rand.nextBoolean();
		}
		
	}

	/**
	 * @return boolean 
	 * if dont have leg return false (cant move)
	 * else if single leg return true at alternative round
	 * else true (can move)
	 */
	@Override
	public boolean canMove(){
		try {
			if (legCount==0){
				return false;
			}
			else if (legCount==1){
				return turn%2==1?true: false;
			}
		} catch (NullPointerException nullPointerException) {
			nullPointerException.printStackTrace();
		}
		return true;
	}
		
	/** 
	 * check if the zombie still have any hands remaining
	 * @return boolean
	 */
	@Override
	public boolean checkhand(){
		if(this.handCount>0){
			return true;
		}
		return false;
	}

	@Override
	public int getFocus(Actor actor) {
		return 0 ;
	}

	@Override
	public boolean setFocus(Actor actor) {
		return false;
	}
	
}
