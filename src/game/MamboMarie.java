package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class MamboMarie extends ZombieActor{
    /**
     * tracking the turn on the map , if turn =-1 , indicates not on map
     */
    private int turn =-1;

    public MamboMarie(String name){
        super(name, 'P', 100, ZombieCapability.UNDEAD);
    }

    
    /** 
     * increment turn every round
     * remove from map if turn >=30
     * spawn 5 new zombies (Chanting Aciton) every 10 turn
     * else wander around 
     * @param actions
     * @param lastAction
     * @param map
     * @param display
     * @return Action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.turn++;
        if (this.turn>= 30 ){
            this.turn =-1;
            map.removeActor(this);
        }
        else if (turn%10==0 && turn>0){
            return new ChantingAction();
        }
        else if (new WanderBehaviour().getAction(this, map)!=null){
            return new WanderBehaviour().getAction(this, map);
        }
        return new DoNothingAction();
    }
}