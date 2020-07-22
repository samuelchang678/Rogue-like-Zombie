package game;

import edu.monash.fit2099.engine.*;

public class GameWorld extends World{
    /**
     * Constructor for Game World
     * subclass the original world 
     * @param display
     */
    public GameWorld(Display display){
        super(display);
    }
    
    /**
     * process all the actor turns including the ground underneath the actor  
     * @param actor
     */
    @Override
    protected void processActorTurn(Actor actor) {
        
        Location here = actorLocations.locationOf(actor);
        GameMap map = here.map();

        Actions actions = new Actions();
        for (Item item : actor.getInventory()) {
            actions.add(item.getAllowableActions());
            // Game rule. If you're carrying it, you can drop it.
            actions.add(item.getDropAction());
        }
        
        actions.add(here.getGround().allowableActions(actor, here, "UnderNeath"));
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // Game rule. You don't get to interact with the ground if someone is standing
            // on it.
            if (actorLocations.isAnActorAt(destination)) {
                actions.add(actorLocations.getActorAt(destination).getAllowableActions(actor, exit.getName(), map));
            } else {
                actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
            }
            actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
        }

        for (Item item : here.getItems()) {
            actions.add(item.getAllowableActions());
            // Game rule. If it's on the ground you can pick it up.
            actions.add(item.getPickUpAction());
        }
        actions.add(new DoNothingAction());
        actions.add(new QuitAction("Exit Game Successfully "));
        Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
        lastActionMap.put(actor, action);
        
        String result = action.execute(actor, map);
        display.println(result);
    }
    
    
    /** 
     * Override from original World class that end when player die
     * Condition added : if player exist but all human die , player loses
*                        if player exist but all zombie die , player win
     * @return boolean
     */
    @Override
    protected boolean stillRunning() {
        if (actorLocations.contains(player)){
            if (!humanAlive()){
                display.println("Player lose");
                return false;
            }
            else if (!zombieAlive()){
                display.println("Player wins");
                return false;

            }
            return true;
        }
        return false;
    }
    
    /** 
     * check all the human in the world
     * @return boolean true if there is at elast one human alive
     *                      else false 
     */
    private boolean humanAlive() {
        for(Actor actor: actorLocations){
            if (actor.hasCapability(ZombieCapability.ALIVE) && actor.isConscious() && !actor.equals(player)){
                return true;
            }
        }
        return false;
    }
    
    /** 
     * check all zombie in the world
     * @return boolean true if there is at least one zombie alive
     *                 else false
     */
    private boolean zombieAlive() {
        for(Actor actor: actorLocations){
            if (actor.hasCapability(ZombieCapability.UNDEAD) && actor.isConscious()){
                return true;
            }
        }
        return false;
    }
}