package game;

import edu.monash.fit2099.engine.Location;


public class Corpse extends PortableItem {
    /**
     * name of the Corpse
     */
    private String name;
    /**
     * round left to turn into a zombie
     */
    private int roundToTurn;

    /**
     * base round to turn into a zombie
     */
    private final static int BASETURN = 5 ;
    /**
     * Constructor for Coprse ,Having it to be an Item so two or more Human may die at the same place
     * @param name name of the Coprse
     * @param roundToTurn at least 5 , up to 10
     */
    public Corpse( String name,int roundToTurn){
        super(name,'D');
        this.roundToTurn = BASETURN+roundToTurn;
        if (this.roundToTurn > 10 )
            this.roundToTurn=10;
        this.name = name;
    }

    /** 
     * Used to determine whether this is the turn to respawn as Zombie
     * @param currentLocation location of the Coprse
     */
    @Override
    public void tick(Location currentLocation) {
        roundToTurn--;
        if (roundToTurn<=0){
            if (!currentLocation.containsAnActor())
                currentLocation.addActor(new Zombie(name));
        }
    }
}