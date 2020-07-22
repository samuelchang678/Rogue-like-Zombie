package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import edu.monash.fit2099.engine.Weapon;

/**
 * Action to calculate the area of effect of the Shotgun 
 */
public class AoeAction extends Action {

    private Exit exit;
    private Weapon weapon ; 
    private Random rand = new Random();
    /**
     * Constructor 
     * @param exit exit which the player decides to shoot the Shotgun at
     * @param weapon Weapon(shotgun)
     */
    public AoeAction(Exit exit , Weapon weapon ){
        this.exit = exit;
        this.weapon = weapon; 
    }

    /**
     * local method to check if the actor is conscious after taking damage
     * this only applies to human , friendly fire is on  
     * @param target the actor 
     * @param map the curernt gamemap
     * @return String 
     */
    protected String actorDead(Actor target , GameMap map ) {
        String result = "";
		if(!target.isConscious()){
			if (target.hasCapability(ZombieCapability.ALIVE)){
				int turnToRaise=rand.nextInt(5);
				Corpse body = new Corpse("dead " + target,turnToRaise);
				map.locationOf(target).addItem(body);
			}
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
	}
    /**
     * Execute the AoeAction 
     * Calculate the difference of x and y coordinates of chosen exit and player location 
	 * @param actor the actpr to shooting the shotgun 
	 * @param map the current gamemap
	 * @return String that indicate the success of firing the weapon 
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Item weaponItem = (Item) weapon;
        System.out.println(weaponItem.effect());
        
        Random rand = new Random();
        NumberRange xnumberRange =  map.getXRange();
        NumberRange yNumberRange = map.getYRange();

        final int maxYrange = yNumberRange.max();
        final int maxXrange = xnumberRange.max();
        final int minYrange = yNumberRange.min();
        final int minXrange = xnumberRange.min();

        String result = actor + " fires shotgun in "+ exit.getName() + " direction.";
        Location locationOfExit= exit.getDestination();
        Location locationOfActor = map.locationOf(actor);
        int x =  locationOfExit.x() -  locationOfActor.x();
        int y = locationOfExit.y() -  locationOfActor.y();
        if (rand.nextInt(100)<25){
            return actor + " misses";
        }
        Boolean hasBullet = weaponItem.fires();
        if (!hasBullet){
            return weapon + " is out of ammunition !!!!";
        }
        
        /**
         * south 
         */
        if(x==0 && y == -1){
            for (int i = 1*y; i>=3*y ; i+=y){
                for (int j = -i; j>=i; j+=y){
                    int xCoordinates = locationOfActor.x()+j;
                    int yCoordinates = locationOfActor.y()+i;
                    if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                        Actor target = map.getActorAt(map.at(locationOfActor.x()+j, locationOfActor.y()+i));
                        if (target !=null) {
                            target.hurt(weapon.damage());
                            result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                            result += actorDead(target,map);
                        }
                    }

                }
            }
        }
        /**
         * north 
         */
        else if (x==0 && y==1){
            for (int i = 1*y; i<=3*y ; i+=y){
                for (int j = -i; j<=i; j+=y){
                    int xCoordinates = locationOfActor.x()+j;
                    int yCoordinates = locationOfActor.y()+i;
                    if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                        Actor target = map.getActorAt(map.at(locationOfActor.x()+j, locationOfActor.y()+i));
                        if (target !=null) {
                            target.hurt(weapon.damage());
                            result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                            result += actorDead(target,map);
                        }
                    }
                }
            }
        }
        /**
         * east
         */
        else if (y==0 && x==1){
            for (int i = 1*x; i<=3*x; i+=x){
                for (int j = -i; j<=i; j+=x){
                    int xCoordinates = locationOfActor.x()+i;
                    int yCoordinates = locationOfActor.y()+j;
                    if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                        Actor target = map.getActorAt(map.at(locationOfActor.x()+i, locationOfActor.y()+j));
                        if (target !=null) {
                            target.hurt(weapon.damage());
                            result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                            result += actorDead(target,map);
                        }
                    }
                }
            }
            
        }
        /**
         * west
         */
        else if (y==0 && x==-1){
            for (int i = 1*x; i>=3*x; i+=x){
                for (int j = -i; j>=i; j+=x){
                    int xCoordinates = locationOfActor.x()+i;
                    int yCoordinates = locationOfActor.y()+j;
                    if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                        Actor target = map.getActorAt(map.at(locationOfActor.x()+i, locationOfActor.y()+j));
                        if (target !=null) {
                            target.hurt(weapon.damage());
                            result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                            result += actorDead(target,map);
                        }
                    }
                }
            }
        }
        /**
         * northwest
         */
        else if(y==1 && x ==-1){
            for (int i =0 ; i>=3*x; i+=x){
                for (int j = 0 ; j<= 3*y ; j+=y){
                    if(!(i == 0 && j == 0) ){
                        int xCoordinates = locationOfActor.x()+i;
                        int yCoordinates = locationOfActor.y()+j;
                        if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                            Actor target = map.getActorAt(map.at(locationOfActor.x()+i, locationOfActor.y()+j));
                            if (target !=null) {
                                target.hurt(weapon.damage());
                                result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                                result += actorDead(target,map);
                            }
                        }
                    }
                }
            }
        }
        /**
         * norteast
         */
        else if(y==1 && x ==1){
            for (int i =0 ; i<=3*x; i+=x){
                for (int j = 0 ; j<= 3*y ; j+=y){
                    if( !(i == 0 && j == 0)){
                        int xCoordinates = locationOfActor.x()+i;
                        int yCoordinates = locationOfActor.y()+j;
                        if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                            Actor target = map.getActorAt(map.at(locationOfActor.x()+i, locationOfActor.y()+j));
                            if (target !=null) {
                                target.hurt(weapon.damage());
                                result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                                result += actorDead(target,map);
                            }
                        }
                    }
                }
            }

        }
        /**
         * southwest
         */
        else if(y==-1 && x ==-1){
            for (int i =0 ; i>=3*x; i+=x){
                for (int j = 0 ; j>= 3*y ; j+=y){
                    if( !(i == 0 && j == 0)){
                        int xCoordinates = locationOfActor.x()+i;
                        int yCoordinates = locationOfActor.y()+j;
                        if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                            Actor target = map.getActorAt(map.at(locationOfActor.x()+i, locationOfActor.y()+j));
                            if (target !=null) {
                                target.hurt(weapon.damage());
                                result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                                result += actorDead(target,map);
                            }
                        }
                    }
                }
            }
        }
        /**
         * southeast
         */
        else if(y==-1 && x ==1){
            for (int i =0 ; i<=3*x; i+=x){
                for (int j = 0 ; j>= 3*y ; j+=y){
                    if(!(i == 0 && j == 0)){
                        int xCoordinates = locationOfActor.x()+i;
                        int yCoordinates = locationOfActor.y()+j;
                        if((xCoordinates>=minXrange && xCoordinates<=maxXrange) && (yCoordinates>=minYrange && yCoordinates<=maxYrange)){
                            Actor target = map.getActorAt(map.at(locationOfActor.x()+i, locationOfActor.y()+j));
                            if (target !=null) {
                                target.hurt(weapon.damage());
                                result += System.lineSeparator()+ target + " received " + weapon.damage()+ " from " + weapon;
                                result = result += actorDead(target,map);
                            }
                        }
                    }
                }
            }

        }
        return result;
    }

    /**
	 * @param actor 
	 * @return String 
	 */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fires shotgun in " + exit.getName() + " direction ";
    }
}