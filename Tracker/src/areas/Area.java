package areas;

import java.util.ArrayList;
import items.Inventory;
import items.Location;

/**
 * Interface for an area in the game.  The different areas will need to be 
 * created and have a way to check the locations in logic for them.
 * 
 * Areas to be created:
 * Light World, Hyrule Castle, Eastern Palace, Desert Palace,
 * Death Mountain, Tower of Hera, Castle Tower, Dark World, 
 * Dark Palace, Swamp Palace, Skull Woods, Thieves Town, 
 * Ice Palace, Misery Mire, Turtle Rock, and Ganon's Tower
 * @author Andrew
 */
public abstract class Area {
        
    /**
     * Method to be implemented that returns all locations in the area
     * that are in logic (accessible and not acquired)
     * @param inventory The current inventory
     * @return A list of locations that are in logic
     */
    public abstract ArrayList<Location> locationsInLogic(Inventory inventory);    
}
