package edu.monash.fit2099.interfaces;
import edu.monash.fit2099.engine.Item;
/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
    int effect();
    Item craft();
    Boolean reload(int rounds);
    Boolean fires();
    
}
