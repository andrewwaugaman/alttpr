package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of ThievesTown for the Tracker
 * @author Andrew
 */
public class ThievesTown extends Dungeon {

    //Thieves Town has 8 possible item locations
    private final Location mapChest;
    private final Location ambushChest;
    private final Location compassChest;
    private final Location bigKeyChest;
    
    private final Location blindsCell;
    
    private final Location attic;
    
    private final Location bigChest;
            
    private final Location blind; 
    
    //Thieves Town has a Big Key and 1 Small Keys in locations
    private final String SMALL_KEY = "Thieves Town - Small Key";
    private final String BIG_KEY = "Thieves Town - Big Key";

    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 1;
    
    public final static String NAME = "Thieves Town";
    
    //Used to check if Thieves Town is Open
    private final DarkWorld darkWorld;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 8 locations with their description
     * @param darkWorld The Dark World the Thieves Town is in
     */
    public ThievesTown(DarkWorld darkWorld) {
        super();
        
        mapChest = new Location("Thieves Town - Map Chest");
        ambushChest = new Location("Thieves Town - Ambush Chest");
        compassChest = new Location("Thieves Town - Compass Chest");
        bigKeyChest = new Location("Thieves Town - Big Key Chest");
        
        blindsCell = new Location("Thieves Town - Blind's Cell");
        
        attic = new Location("Thieves Town - Attic");
        
        bigChest = new Location("Thieves Town - Big Chest");
                       
        blind = new Location("Thieves Town - Blind");
        
        this.darkWorld = darkWorld;
    }
    
    /**
     * Check to see if there is a way to enter the dungeon
     * Thieves Town can be entered if the north west side of 
     * the Dark World is accessible
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        return !darkWorld.northWestDarkAccess(inventory);
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Thieves Town requires the hammer to full clear:
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
              
        return inventory.getItem(KeyItem.HAMMER).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {mapChest, ambushChest, compassChest, 
            bigKeyChest};
        
        for (Location location : possibleBigKey) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(BIG_KEY))
                return true;
        }
        
        return false;
    }
    
    /**
     * Check to see how many small keys have been picked up
     * Note -- Not all chests in Thieves Town can be keys
     * @return The number of small keys that have been acquired
     */
    private int smallKeysAcquired() {
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {mapChest, ambushChest, compassChest, 
            bigKeyChest, blindsCell, bigChest};
        
        for (Location location : possibleSmallKeys) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(SMALL_KEY))
                numSmallKeys++;
        }
        
        return numSmallKeys;
    }
    
    /**
     * Check to see if there are any small keys that can be locked
     * A small key can be locked if it is in a room that requires a 
     * small key to access.  In Thieves Town a small key could be 
     * locked in the big chest
     * @return The number of small keys that could be locked
     */
    private int smallKeysLocked() {
        int numObtainedKeys = 0;
        
        Location[] possibleSmallKeys = {mapChest, ambushChest, compassChest, 
            bigKeyChest, blindsCell};
        
        //If there's a location outside of the big chest 
        //that hasn't been checked exit the check
        for (Location location : possibleSmallKeys) {
            if (!location.isAcquired())
                return 0;
            else
                if (location.getContents().getDescription().equals(SMALL_KEY))
                    numObtainedKeys++;
        }
        
        //Since this is reached, the big chest has to have the small key 
        //First check to see if it's opened, if not then the key could be
        //locked with poor key usage
        if (bigChest.isAcquired() && bigChest.getContents()
                .getDescription().equals(SMALL_KEY))
            numObtainedKeys++;
        
        return TOTAL_SMALL_KEYS - numObtainedKeys;
    }    
    
    /**
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (closed(inventory)) 
            return inLogic;
        
        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        if (logicAmbushChest(inventory))
            inLogic.add(ambushChest);
        if (logicCompassChest(inventory))
            inLogic.add(compassChest);
        if (logicBigKeyChest(inventory))
            inLogic.add(bigKeyChest);
        
        if (bigKeyAcquired()) {
            if (logicBlindsCell(inventory))
                inLogic.add(blindsCell);
            
            //Theives town has a unique locked chest in that it requires the
            //hammer inside the room to reach the chest, so the key to the 
            //door can be used without the key in the chest being obtainable
            //
            //To get around this, check to see if the key is acquired, or if 
            //it's inside the big chest and the hammer is obtained 
            if (smallKeysAcquired() == TOTAL_SMALL_KEYS ||
                    (smallKeysLocked() == TOTAL_SMALL_KEYS && 
                    inventory.getItem(KeyItem.HAMMER).isOwned())) {
                if (logicAttic(inventory))
                    inLogic.add(attic);
                if (logicBigChest(inventory))
                    inLogic.add(bigChest);
                if (logicBlind(inventory))
                    inLogic.add(blind);
            }
        }
        
        return inLogic;
    }
    
    /**
     * Check to see if the map chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicMapChest(Inventory inventory) {
        return !mapChest.isAcquired();
    }
    
    /**
     * Check to see if the ambush chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicAmbushChest(Inventory inventory) {
        return !ambushChest.isAcquired();
    }
    
    /**
     * Check to see if the compass chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicCompassChest(Inventory inventory) {
        return !compassChest.isAcquired();
    }
    
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        return !bigKeyChest.isAcquired();
    }
    
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBlindsCell(Inventory inventory) {
        return !blindsCell.isAcquired();
    }
    
    /**
     * Check to see if the attic chest is in logic
     * It's in logic if it's not opened 
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicAttic(Inventory inventory) {
        return !attic.isAcquired();
    }
    
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened and the hammer is acquired
     * @param inventory The current inventory 
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        if (bigChest.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HAMMER).isOwned();
    }
        
    /**
     * Check to see if Blind is in logic
     * It's in logic if the item isn't acquired and a weapon is acquired:
     * 1) A Sword (any level)
     * 2) The Cane of Somaria
     * 3) The Cane of Byrna
     * 4) The Hammer
     * @param inventory The current inventory
     * @return True or False if Blind is in logic
     */
    private boolean logicBlind(Inventory inventory) {
        if (blind.isAcquired())
            return false;
        
        if (inventory.getItem(Sword.SWORD).isOwned())
            return true;

        if (inventory.getItem(KeyItem.SOMARIA).isOwned())
            return true;

        if (inventory.getItem(KeyItem.BYRNA).isOwned())
            return true;
        
        return inventory.getItem(KeyItem.HAMMER).isOwned();
    }          
    
    //Getters and Setters for the locations below
    
    /**
     * @return the mapChest
     */
    public Location getMapChest() {
        return mapChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setMapChest(Item contents) {
        mapChest.setContents(contents);
    }
    
    /**
     * @return the ambushChest
     */
    public Location getAmbushChest() {
        return ambushChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setAmbushChest(Item contents) {
        ambushChest.setContents(contents);
    }    
    
    /**
     * @return the compassChest
     */
    public Location getCompassChest() {
        return compassChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCompassChest(Item contents) {
        compassChest.setContents(contents);
    }
    
    /**
     * @return the bigKeyChest
     */
    public Location getBigKeyChest() {
        return bigKeyChest;
    }
    
    /**
     * @param contents The new contents of the big key chest
     */
    public void setBigKeyChest(Item contents) {
        bigKeyChest.setContents(contents);
    }
     
    /**
     * @return the blindsCell
     */
    public Location getBlindsCell() {
        return blindsCell;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBlindsCell(Item contents) {
        blindsCell.setContents(contents);
    }
    
    /**
     * @return the attic
     */
    public Location getAttic() {
        return attic;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setAttic(Item contents) {
        attic.setContents(contents);
    }
    
    /**
     * @return the bigChest
     */
    public Location getBigChest() {
        return bigChest;
    }
    
    /**
     * @param contents The new contents of the big chest
     */
    public void setBigChest(Item contents) {
        bigChest.setContents(contents);
    }

    /**
     * @return blind
     */
    public Location getBlind() {
        return blind;
    }
    
    /**
     * @param contents Blind's new item
     */
    public void setBlind(Item contents) {
        blind.setContents(contents);
    }
}