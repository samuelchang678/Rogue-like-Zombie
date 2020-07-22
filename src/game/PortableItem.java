package game;

import edu.monash.fit2099.engine.Item;


/**
 * Base class for any item that can be picked up and dropped.
 */
public abstract class PortableItem extends Item {

	/**
	 * Base class for any item that can be picked up and dropped.
	 * @param name name of the Item
	 * @param displayChar character on the GameMap
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
	
	/** 
     * To be Override by subclass if they have an effect of healing
     * healing Effect for the Item to dealt
     * @return int always return 0 
     */
	@Override
	public int effect() {
		return 0 ;
	}

	/**
     * To be Override by subclass if they can be crafted
     * Craft a Item into another Item
     * @return Item null
     */
	@Override
	public Item craft() {
		return null;
	}

	@Override
	public Boolean reload(int rounds) {
		return false;
	}

	@Override
	public Boolean fires() {
		return false;
	}
	
}
