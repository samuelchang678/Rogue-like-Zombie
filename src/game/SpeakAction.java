package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SpeakAction extends Action {
    /**
     * An array of String that the zombies can say 
     */
    protected String[] sentence = {
        "bruhh","brainsssss","undead", "humanzzzz", "arjdfsd","give me food","hungrrr...."};
    /**
     * constructor 
     */
    public SpeakAction(){}

    /** 
	 * Execute the SpeakAction()
	 * @param actor actor to speak
	 * @param map current game map
	 * @return String indicates what did the actor said 
	 */
    @Override
    public String execute(Actor actor , GameMap map){
        int rnd = new Random().nextInt(sentence.length);
        String result = actor  + " says " +"\""+ sentence[rnd] +"\"";
        return result ;
    }

    /** 
	 * @param actor
	 * @return String
	 */
    @Override
	public String menuDescription(Actor actor) {
		return actor + " said something  ";
	}
}