package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of Swamp Palace for the Tracker
 * @author Andrew
 */
public class SwampPalace extends Dungeon {

    //Swamp Palace has 10 possible item locations
    private final Location entrance;
    
    private final Location mapChest;
    
    private final Location compassChest;
    private final Location westChest;
    private final Location bigKeyChest;
    
    private final Location bigChest;
    
    private final Location floodedRoomLeft;
    private final Location floodedRoomRight;
    private final Location waterfallRoom;
    
    private final Location aarghus; 
    
    //Swamp Palace has a Big Key and 1 Small Keys
    private final String SMALL_KEY = "Swamp Palace - Small Key";
    private final String BIG_KEY = "Swamp Palace - Big Key";
    
    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 1;
    
    public final static String NAME = "Swamp Palace";
    
    //Used to check if Swamp Palace is Open
    private final DarkWorld darkWorld;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 10 locations with their description
     * @param darkWorld The Dark World the Swamp Palace is in
     */
    public SwampPalace(DarkWorld darkWorld) {
        super();
        
        entrance = new Location("Swamp Palace - Entrance");
        
        mapChest = new Location("Swamp Palace - Map Chest");     
        
        compassChest = new Location("Swamp Palace - Compass Chest");
        westChest = new Location("Swamp Palace - West Chest");
        bigKeyChest = new Location("Swamp Palace - Big Key Chest");
        
        bigChest = new Location("Swamp Palace - Big Chest");
        
        floodedRoomLeft = new Location("Swamp Palace - Flooded Room - Left");
        floodedRoomRight = new Location("Swamp Palace - Flooded Room - Right");
        waterfallRoom = new Location("Swamp Palace - Waterfall Room");
        
        aarghus = new Location("Swamp Palace - Aarghus");
        
        this.darkWorld = darkWorld;
    }
   
    /**
     * Check to see if there is a way to enter the dungeon
     * Dark Palace can be entered if the south side of the
     * Dark World is accessible and both:
     * 1) The mirror is acquired
     * 2) The flippers are acquired
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        if (!darkWorld.southDarkAccess(inventory))
            return true;
        
        if (!inventory.getItem(KeyItem.MIRROR).isOwned())
            return true;
        
        return !inventory.getItem(KeyItem.FLIPPERS).isOwned();
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Swamp Palace requires the following to full clear:
     * 1) Hammer
     * 2) Hookshot
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {entrance, mapChest, compassChest, 
            westChest, bigKeyChest, floodedRoomLeft, floodedRoomRight,
            waterfallRoom, aarghus};
        
        for (Location location : possibleBigKey) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(BIG_KEY))
                return true;
        }
        
        return false;
    }
    
    /**
     * Check to see if the small key has been picked up
     * Note -- Only the entrance in Swamp Palace can be a key
     * @return Whether or not the small key has been picked up
     */
    private int smallKeysAcquired() {      
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {entrance};
        
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
        
        if (logicEntrance(inventory))
            inLogic.add(entrance); 
        
        if (smallKeysAcquired() == TOTAL_SMALL_KEYS) {
            if (logicMapChest(inventory))
                inLogic.add(mapChest);
            
            if (logicCompassChest(inventory))
                inLogic.add(compassChest);
            if (logicWestChest(inventory))
                inLogic.add(westChest);
            if (logicBigKeyChest(inventory))
                inLogic.add(bigKeyChest);
            
            if (logicBigChest(inventory))
                inLogic.add(bigChest);
            
            if (logicFloodedRoomLeft(inventory))
                inLogic.add(floodedRoomLeft);
            if (logicFloodedRoomRight(inventory))
                inLogic.add(floodedRoomRight);
            if (logicWaterfallRoom(inventory))
                inLogic.add(waterfallRoom);
            
            if (logicAarghus(inventory))
                inLogic.add(aarghus);
        }
 
        return inLogic;
    }
    
    /**
     * Check to see if the entrance is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the location is in logic
     */
    private boolean logicEntrance(Inventory inventory) {
        return !entrance.isAcquired();
    }
    
    /**
     * Check to see if the map chest room is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicMapChest(Inventory inventory) {
        return !mapChest.isAcquired();
    }
     
    /**
     * Check to see if the compass chest is in logic
     * It's in logic if it's not opened and the hammer is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicCompassChest(Inventory inventory) {
        if (compassChest.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HAMMER).isOwned();
    }
    
    /**
     * Check to see if the west chest is in logic
     * It's in logic if it's not opened and the hammer is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicWestChest(Inventory inventory) {
        if (westChest.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HAMMER).isOwned();
    }
 
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened and the hammer is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        if (bigKeyChest.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HAMMER).isOwned();
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
       
        if (!bigKeyAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HAMMER).isOwned();
    }
    
    /**
     * Check to see if the flooded room left chest is in logic
     * It's in logic if it's not opened and the following are acquired:
     * 1) The hammer
     * 2) The hookshot
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicFloodedRoomLeft(Inventory inventory) {
        if (floodedRoomLeft.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }
    
    /**
     * Check to see if the flooded room right chest is in logic
     * It's in logic if it's not opened and the following are acquired:
     * 1) The hammer
     * 2) The hookshot
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicFloodedRoomRight(Inventory inventory) {
        if (floodedRoomRight.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }
    
    /**
     * Check to see if the waterfall room chest is in logic
     * It's in logic if it's not opened and the following are acquired:
     * 1) The hammer
     * 2) The hookshot
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicWaterfallRoom(Inventory inventory) {
        if (waterfallRoom.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }
    
    /**
     * Check to see if Aarghus is in logic
     * It's in logic if the item isn't acquired and the following are acquired:
     * 1) The hammer
     * 2) The hookshot
     * @param inventory The current inventory
     * @return True or False if Aarghus is in logic
     */
    private boolean logicAarghus(Inventory inventory) {
        if (aarghus.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }          
    
    //Getters and Setters for the locations below
    
    /**
     * Get a list of all the locations
     * @return All the locations in Swamp Palace
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(entrance);
        
        locations.add(mapChest);
        
        locations.add(compassChest);
        locations.add(westChest);
        locations.add(bigKeyChest);
        
        locations.add(bigChest);
        
        locations.add(floodedRoomLeft);
        locations.add(floodedRoomRight);
        locations.add(waterfallRoom);
        
        locations.add(aarghus);
        
        return locations; 
    }

    /**
     * @return the entrance
     */
    public Location getEntrance() {
        return entrance;
    }
    
    /**
     * @param contents The new contents of the entrance
     */
    public void setEntrance(Item contents) {
        entrance.setContents(contents);
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
     * @return the westChest
     */
    public Location getWestChest() {
        return westChest;
    }
    
    /**
     * @param contents The new contents of the west chest
     */
    public void setWestChest(Item contents) {
        westChest.setContents(contents);
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
     * @return the floodedRoomLeft
     */
    public Location getFloodedRoomLeft() {
        return floodedRoomLeft;
    }
    
    /**
     * @param contents The new contents of the flooded room left chest
     */
    public void setFloodedRoomLeft(Item contents) {
        floodedRoomLeft.setContents(contents);
    }
    
    /**
     * @return the floodedRoomRight
     */
    public Location getFloodedRoomRight() {
        return floodedRoomRight;
    }
    
    /**
     * @param contents The new contents of the floodedRoom right chest
     */
    public void setFloodedRoomRight(Item contents) {
        floodedRoomRight.setContents(contents);
    }
    
    /**
     * @return the waterfallRoom
     */
    public Location getWaterfallRoom() {
        return waterfallRoom;
    }
    
    /**
     * @param contents The new contents of the waterfall room
     */
    public void setWaterfallRoom(Item contents) {
        waterfallRoom.setContents(contents);
    }
    
    /**
     * @return the aarghus
     */
    public Location getAarghus() {
        return aarghus;
    }
    
    /**
     * @param contents The Aarghus new item
     */
    public void setAarghus(Item contents) {
        aarghus.setContents(contents);
    }
    
    /**
     * Used to print the name of the area
     * @return The name of the area
     */
    @Override
    public String toString() {
        return NAME;
    }
}