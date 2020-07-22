package game;


public class Food extends PortableItem{
    /**
     * heal indicates how much should the item heal if consumed
     */
    private int heal; 
    
    /**
     * Constructor for Food class
     * @param name  the name of the food
     * @param heal  int how much should the food heal
     * Adding a EatCapability to determine if it is EDIBLE
     */
    public Food (String name , int heal ){
        super(name,'f');
        addCapability(EatCapability.EDIBLE);
        this.heal = heal ;
        allowableActions.add(new EatAction(this));
    }
    
    /** 
     * @return int indicating the how much should the food heal
     */
    @Override
    public int effect() {
        return heal;
    }
}