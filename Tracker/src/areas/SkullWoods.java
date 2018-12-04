package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of Skull Woods for the Tracker
 * @author Andrew
 */
public class SkullWoods extends Dungeon {

    //Skull Woods has 8 possible item locations
    private final Location compassChest;
    private final Location potPrison;
    private final Location pinballRoom;
    private final Location mapChest;
    private final Location bigKeyChest;
    
    private final Location bigChest;
        
    private final Location bridgeRoom;
    
    private final Location mothula; 
    
    //Skull Woods has a Big Key and 3 Small Keys in locations
    private final String SMALL_KEY = "Skull Woods - Small Key";
    private final String BIG_KEY = "Skull Woods - Big Key";

    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 3;
    
    public final static String NAME = "Skull Woods";
    
    //Used to check if Skull Woods is Open
    private final DarkWorld darkWorld;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 8 locations with their description
     * @param darkWorld The Dark World the Skull Woods is in
     */
    public SkullWoods(DarkWorld darkWorld) {
        super();
        
        compassChest = new Location("Skull Woods - Compass Chest");
        potPrison = new Location("Skull Woods - Pot Prison");
        pinballRoom = new Location("Skull Woods - Pinball Room");
        mapChest = new Location("Skull Woods - Map Chest");
        bigKeyChest = new Location("Skull Woods - Big Key Chest");
        
        bigChest = new Location("Skull Woods - Big Chest");
                
        bridgeRoom = new Location("Skull Woods - Bridge Room");
        
        mothula = new Location("Skull Woods - Mothula");
        
        this.darkWorld = darkWorld;
    }
    
    /**
     * Check to see if there is a way to enter the dungeon
     * Skull Woods can be entered if the north west side of 
     * the Dark World is accessible
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        return !darkWorld.northWestDarkAccess(inventory);
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Skull Woods requires the following to full clear:
     * 1) Fire Rod
     * 2) A sword (any level - Cut curtains to get to Mothula)
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (!inventory.getItem(KeyItem.FIRE_ROD).isOwned())
            return false;
        
        return inventory.getItem(Sword.SWORD).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {compassChest, potPrison, pinballRoom,
            mapChest, bigKeyChest, bridgeRoom, mothula};
        
        for (Location location : possibleBigKey) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(BIG_KEY))
                return true;
        }
        
        return false;
    }
    
    /**
     * Check to see how many small keys have been picked up
     * Note -- Not all locations in Skull Woods can be keys
     * @return The number of small keys that have been acquired
     */
    private int smallKeysAcquired() {
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {compassChest, potPrison, pinballRoom,
            mapChest, bigKeyChest, bigChest, bridgeRoom};
        
        for (Location location : possibleSmallKeys) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(SMALL_KEY))
                numSmallKeys++;
        }
        
        return numSmallKeys;
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
        
        if (logicCompassChest(inventory))
            inLogic.add(compassChest);
        if (logicPotPrison(inventory))
            inLogic.add(potPrison);
        if (logicPinballRoom(inventory))
            inLogic.add(pinballRoom);
        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        if (logicBigKeyChest(inventory))
            inLogic.add(bigKeyChest);
        
        if (bigKeyAcquired())
            if (logicBigChest(inventory))
                inLogic.add(bigChest);
        
        if (logicBridgeRoom(inventory))
            inLogic.add(bridgeRoom);
        
        if (smallKeysAcquired() == TOTAL_SMALL_KEYS)
            if (logicMothula(inventory))
                inLogic.add(mothula);
        
        return inLogic;
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
     * Check to see if the pot prison chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicPotPrison(Inventory inventory) {
        return !potPrison.isAcquired();
    }
    
    /**
     * Check to see if the pinball chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicPinballRoom(Inventory inventory) {
        return !pinballRoom.isAcquired();
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
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        return !bigKeyChest.isAcquired();
    }
    
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        return !bigChest.isAcquired();
    }
  
    /**
     * Check to see if the bridge room chest is in logic
     * It's in logic if it's not opened and the fire rod is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicBridgeRoom(Inventory inventory) {
        if (bridgeRoom.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.FIRE_ROD).isOwned();
    }
    
    /**
     * Check to see if Mothula is in logic
     * It's in logic if the item isn't acquired and the following are acquired:
     * 1) The Fire Rod
     * 2) A Sword (any level)
     * @param inventory The current inventory
     * @return True or False if Mothula is in logic
     */
    private boolean logicMothula(Inventory inventory) {
        if (mothula.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.FIRE_ROD).isOwned())
            return false;
        
        return inventory.getItem(Sword.SWORD).isOwned();
    }          
    
    //Getters and Setters for the locations below
    
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
     * @return the potPrison
     */
    public Location getPotPrison() {
        return potPrison;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setPotPrison(Item contents) {
        potPrison.setContents(contents);
    }
    
    /**
     * @return the pinballRoom
     */
    public Location getPinballRoom() {
        return pinballRoom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setPinballRoom(Item contents) {
        pinballRoom.setContents(contents);
    }
    
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
     * @return the bridgeRoom
     */
    public Location getBridgeRoom() {
        return bridgeRoom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBridgeRoom(Item contents) {
        bridgeRoom.setContents(contents);
    }

    /**
     * @return the mothula
     */
    public Location getMothula() {
        return mothula;
    }
    
    /**
     * @param contents The Mothula's new item
     */
    public void setMothula(Item contents) {
        mothula.setContents(contents);
    }
}