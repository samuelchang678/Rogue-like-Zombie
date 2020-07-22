package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class Vehicle extends Ground {
    private GameMap destinationMap;
    private int x , y ;
    
    public Vehicle(GameMap destinationMap,int x ,int y){
        super('V');
        this.destinationMap = destinationMap;
        this.x = x;
        this.y = y;
    }
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {

        Actions actions =  super.allowableActions(actor, location, direction);
        actions.add(new MoveActorAction(destinationMap.at(this.x,this.y),"To <?> Map"));
        return actions;
    }
}