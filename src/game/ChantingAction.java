package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ChantingAction extends Action {
    /**
     * base zombie constant
     */
    private final static int ZOMBIESPAWN = 5;
    /** 
     * spawn 5 random zombies
     * @param actor
     * @param map
     * @return String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String retVal = actor + " performs an enchantment spell ";
        int x, y;
		for (int i=0 ; i < ZOMBIESPAWN ; i ++){
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (map.at(x, y).containsAnActor());
            map.at(x,  y).addActor(new Zombie("Voodooo"+Integer.toString(i)));
            retVal+= System.lineSeparator() + " Spawned Voodooo "+Integer.toString(i) + " at Location "+Integer.toString(x)+" , "+Integer.toString(y);
        }
        return retVal;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Spawn zombie";
    }
}