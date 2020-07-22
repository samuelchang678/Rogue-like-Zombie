package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Crop extends Ground {
    /**
     * the numbers of turn left to ripe
     */
    private int numberOfTurns;
    /**
     * the name of the crop
     */
    private String name;   
    /**
     * the constant of turn to ripe
     */
    private static final int turnToRipe = 20;


    /**
     * Constructor for Crop class 
     * @param name name of the crop
     * Adding HarvestCapability.NotHarvestable so it can be tracked whether its ripe
     * Adding Sowcapability so that the farmer wont be able to plant on the same land twice
     */
    public Crop(String name){
        super('c');
        addCapability(HarvestCapability.NOTHARVESTABLE);
        this.numberOfTurns = turnToRipe;
        this.name=name;
    }
    
    /** 
     * @return return the name of the crop
     */
    @Override
    public String toString() {
        return name;
    }
    /**
     * if the Crop is harvestable then we add an harvest action to it
     * @param actor the actor to do the action
     * @param location the location of the crop
     * @param direction at which direction the crop to the actor
     * @return actions if its not harvestable else actions with harvest action
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (hasCapability(HarvestCapability.HARVESTABLE)){
            Actions actions=super.allowableActions(actor, location, direction);
            actions.add(new HarvestAction(this,location));
            return actions;
        }
        return super.allowableActions(actor, location, direction);
    }

    /** 
     * tick every turn and reducing its turn to ripe ,
     * if turn is <=0 , changing the capability from NOTHARVESTABLE -> HARVESTABLE
     * @param currentLocation the current location of the crop 
     */
    @Override
    public void tick(Location currentLocation) {
        numberOfTurns--;
        if (numberOfTurns <= 0){
           this.removeCapability(HarvestCapability.NOTHARVESTABLE);
           this.addCapability(HarvestCapability.HARVESTABLE);
        }
    }
}