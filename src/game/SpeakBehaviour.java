package game;

import edu.monash.fit2099.engine.*;

public class SpeakBehaviour implements Behaviour{
      /** 
     * Allows the zombie to speak
     * @param actor actor to harvest (Zombie)
     * @param map current gamemap
     * @return Action ,Speak which will say something zombielike  
     */
    @Override
    public Action getAction(Actor actor , GameMap mao){
        if(Math.random()<= 0.1)
            return new SpeakAction();
        return null;
    }
}
    
  

    