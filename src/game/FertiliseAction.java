package game;

import edu.monash.fit2099.engine.Action;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
// import edu.monash.fit2099.engine.Crop;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


public class FertiliseAction extends Action {
    /**
	 * The Crop that is to be Harvest
	 */
	protected Ground target;
	/**
	 * the location of the actor
	 */
	protected Location location;
    /**
	 * Constructor.
	 * 
	 * @param target the Crop to fertilise
	 */
	public FertiliseAction(Ground crop,Location location) {
		this.target = crop;
		this.location = location;
	}
	
	/**
	 * Execute the FertiliseAction
	 * @param actor the actpr to fertilise the crop 
	 * @param map the current gamemap
	 * @return String that indicate the success of fertilising the crop 
	 */
    @Override
	public String execute(Actor actor, GameMap map) {
        String result = actor + " " + "fertilise a crop";
		for(int i =0 ; i <10;i++)
			target.tick(location);
		return result;
	}

	/**
	 * @param actor 
	 * @return String 
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilise " + target;
	}
    
    
}