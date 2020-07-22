package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;


/**
 * Base class for all ranged weapon 
 */
public abstract class WeaponGuns extends WeaponItem{
    /**
     * number of round the weapon have
     */
    private  int rounds ;
    /**
     * max number of round for the weapon
     */
    private static final int MAXBULLET = 5 ;

    /**
     * constructor for WeaponGuns 
     * @param name name of the item
     * @param displayChar display char on the map 
     * @param damage damage of thw weapon 
     * @param verb verb of the action 
     */
    public WeaponGuns(String name,char displayChar, int damage, String verb){
        super(name, displayChar, damage, verb);
        this.rounds = MAXBULLET;
    }

    @Override
    public Item craft() {
        return null;
    }

    @Override
    public int effect() {
        return 0;
    }
    /**
     * method overriden to reload the gun 
     */
    @Override
    public Boolean reload(int rounds) {
        this.rounds += rounds;
        if (this.rounds>MAXBULLET)
            this.rounds = MAXBULLET;
        return true ;
    }

    /**
     * reduce weapon rounds everytime it is fired. 
     */
    @Override
    public Boolean fires() {
        if (this.rounds <= 0 ){
            this.rounds = 0; 
            return false;
        }
        this.rounds --;
        return true;
    }
}