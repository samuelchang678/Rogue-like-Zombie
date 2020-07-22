package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


public class HarvestAction extends Action {
    /**
	 * The Crop that is to be Harvest
	 */
	protected Ground target;
	/**
	 * the location of the Crop
	 */
	protected Location location;
    /**
	 * Constructor.
	 * throw IllegalArgumentException if crop or location is empty
	 * @param crop the Crop to harvest
	 * @param location  the location of the crop
	 */
	public HarvestAction(Ground crop,Location location) {
		if (crop!=null){
			this.target = crop;
		}
		else{
			throw new IllegalArgumentException("crop is null");
		}

		if (location!=null){
			this.location = location;
		}
		else{
			throw new IllegalArgumentException("location is null");
		}
    }
	
    
	/** 
	 * Execute the HarvestAction
	 * @param actor actor to Harvest the Crop
	 * @param map current game map
	 * @return String indicates its success or failed harvesting the crop
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor +" harvest " +target.toString();
		// able to harvest the target
		try {
			actor.harvest(target,map);
			location.setGround(new Dirt());
			result+=" success";
			return result;
	
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return result+" Failed";
	}

	
	/** 
	 * menu Description mainly for player
	 * @param actor actor to show the menu 
	 * @return String description of the action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvest " + target + " ?";
	}
}