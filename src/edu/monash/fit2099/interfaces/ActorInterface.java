package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Weapon;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
    void harvest(Ground item,GameMap map);
    boolean craft(Item item);
    boolean eat(Item item,GameMap map);
    Boolean attack (Weapon weapon,Actor target, GameMap map);
    boolean checkhand();
    boolean canMove();
    int getFocus(Actor actor);
    boolean setFocus(Actor actor);
}
