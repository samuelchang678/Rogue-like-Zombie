package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class PickUpBehaviour implements Behaviour{
    /**
     *Check if there is a weapon item on the gorund that the zombie can pick up 
     * @param actor the zombie
     * @param map the current gamemap 
     * @return Action, PickUpAction which will remove the item from the groun and put it in the zombie's inventory
     * 
     */

    @Override
    public Action getAction(Actor actor , GameMap map){
        List<Item> item = new ArrayList<Item> (map.locationOf(actor).getItems());
        if (actor.checkhand()){
            if(!item.isEmpty() && actor.getInventory().isEmpty()){
                Weapon weapon = item.get(0).asWeapon();
                if (weapon !=null){
                    return new PickUpItemAction(item.get(0));
                }
                return null ;
                
               
            }
        }
      
        return null ;
    }  
}