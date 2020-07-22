package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class SowAction extends Action {

    /**
     * the location at which the crop will be planted
     */
    protected Location adjacentLocation ; 
    /**
     * constructor 
     * @param adjacent location where the crop will be planted
     */
    public SowAction(Location adjacent){
        this.adjacentLocation = adjacent ;
    }

    /**
     * Execute the SowAction 
     * @param actor actor that will be planting the crop 
     * @param map current gamemap
     * @return String indicate the succes of the action
     */
    @Override
	public String execute(Actor actor, GameMap map) {
        String result = actor + " " + "planted a crop";
        adjacentLocation.setGround(new Crop("crop"));
		return result;
	}

    /** 
	 * @param actor
	 * @return String
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " planted  a crop  ";
	}
}