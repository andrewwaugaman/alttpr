package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of Ice Palace for the Tracker
 * @author Andrew
 */
public class IcePalace extends Dungeon {
    
    //Ice Palace has 8 possible item locations
    private final Location compassChest;
    private final Location freezorChest;
    private final Location icedTRoom;
    
    private final Location bigChest;
        
    private final Location spikeRoom;
    
    private final Location mapChest;
    private final Location bigKeyChest;
    
    private final Location kholdstare; 
    
    //Ice Palace has a Big Key and 2 Small Keys in locations
    private final String SMALL_KEY = "Ice Palace - Small Key";
    private final String BIG_KEY = "Ice Palace - Big Key";

    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 2;
    
    public final static String NAME = "Ice Palace";
    
    //Used to check if Ice Palace is Open
    private final DarkWorld darkWorld;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 8 locations with their description
     * @param darkWorld The Dark World the Ice Palace is in
     */
    public IcePalace(DarkWorld darkWorld) {
        super();
        
        compassChest = new Location("Ice Palace - Compass Chest");
        freezorChest = new Location("Ice Palace - Freezor Chest");
        icedTRoom = new Location("Ice Palace - Iced T Room");

        bigChest = new Location("Ice Palace - Big Chest");

        spikeRoom = new Location("Ice Palace - Spike Room");
        
        mapChest = new Location("Ice Palace - Map Chest");
        bigKeyChest = new Location("Ice Palace - Big Key Chest");
                        
        kholdstare = new Location("Ice Palace - Kholdstare");
        
        this.darkWorld = darkWorld;
    }
    
    /**
     * Check to see if there is a way to enter the dungeon
     * Ice Palace can be entered if the north west side of 
     * the Dark World is accessible and either:
     * 1) The fire rod is acquired
     * 2) Bombos and a sword are acquired
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        if (!darkWorld.lakeHyliaAccess(inventory))
            return true;
        
        if (inventory.getItem(KeyItem.FIRE_ROD).isOwned())
            return false;
        
        return !(inventory.getItem(KeyItem.BOMBOS).isOwned() && 
                inventory.getItem(Sword.SWORD).isOwned());
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Ice Palace requires the following to full clear:
     * 1) Hammer
     * 2) Hookshot (Don't know key logic)
     * 3) Cane of Somaria (Don't know key logic)
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        if (!inventory.getItem(KeyItem.HOOKSHOT).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.SOMARIA).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {compassChest, freezorChest, icedTRoom,
            spikeRoom, mapChest, bigKeyChest};
        
        for (Location location : possibleBigKey) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(BIG_KEY))
                return true;
        }
        
        return false;
    }
    
    /**
     * Check to see how many small keys have been picked up
     * Note -- All locations in Ice Palace can be keys
     * @return The number of small keys that have been acquired
     */
    private int smallKeysAcquired() {
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {compassChest, freezorChest, icedTRoom,
            bigChest, spikeRoom, mapChest, bigKeyChest, kholdstare};
        
        for (Location location : possibleSmallKeys) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(SMALL_KEY))
                numSmallKeys++;
        }
        
        return numSmallKeys;
    }
    
    /**
     * Check to see if it's possible to reach the small key in the map room
     * Note -- will only be called if the spike room is accessible
     * It can be reached if the following are true:
     * 1) The hookshot, cape or Cane of Byrna are acquired 
     *          (To get past the spike room)
     * 2) The hammer is acquired
     */
    private boolean canReachSmallKeyInMapRoom(Inventory inventory) {
        if (!(inventory.getItem(KeyItem.HOOKSHOT).isOwned() 
                || inventory.getItem(KeyItem.CAPE).isOwned() 
                || inventory.getItem(KeyItem.BYRNA).isOwned()))
            return false;
        
        return inventory.getItem(KeyItem.HAMMER).isOwned();              
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
        if (logicFreezorChest(inventory))
            inLogic.add(freezorChest);
        if (logicIcedTRoom(inventory))
            inLogic.add(icedTRoom);
        
        if (logicBigChest(inventory))
            inLogic.add(bigChest);
         
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned()) 
            inLogic.addAll(keyLogicHookshot(inventory));
        else
            inLogic.addAll(keyLogicNoHookshot(inventory));
        
        //Checking Kholdstare.  Kholdstare requires the big key and can be
        //reached with:
        //3 small keys
        //2 small keys and the Cane of Somaria
        //Note -- The key count does not include the key that can be picked
        //up in the pot room on the ice floor.  That key is cancelled out by
        //being "Used" on the door in the giant ice floor room that doesn't
        //actually open up any extra rooms or item locations
        if (bigKeyAcquired()) {
            int numSmallKeys = smallKeysAcquired();
            if (canReachSmallKeyInMapRoom(inventory))
                numSmallKeys++;
            
            if (numSmallKeys == 3 || (numSmallKeys == 2 && 
                    inventory.getItem(KeyItem.SOMARIA).isOwned()))
                if (logicKholdstare(inventory))
                    inLogic.add(kholdstare);
        }
        
        return inLogic;
    }
    
    /**
     * Check to see what's in logic if the hookshot is acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired 
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogicHookshot(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (logicSpikeRoom(inventory))
            inLogic.add(spikeRoom);
        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        if (logicBigKeyChest(inventory))
            inLogic.add(bigKeyChest);

        return inLogic;
    }
    
    /**
     * Check to see what's in logic if the hookshot isn't acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired 
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogicNoHookshot(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        //Two key doors are located behind the big key so if that's 
        //obtained then all the keys are required for these chests
        if (bigKeyAcquired()) {
            if (smallKeysAcquired() == TOTAL_SMALL_KEYS) {
                if (logicSpikeRoom(inventory))
                    inLogic.add(spikeRoom);
                if (logicMapChest(inventory))
                    inLogic.add(mapChest);
                if (logicBigKeyChest(inventory))
                    inLogic.add(bigKeyChest);          
            }
        }
        //Two key doors are located behind the big key so if that's 
        //not obtained then 1 key is required for these chests
        else {
            if (smallKeysAcquired() == 1) {
                if (logicSpikeRoom(inventory))
                    inLogic.add(spikeRoom);
                if (logicMapChest(inventory))
                    inLogic.add(mapChest);
                if (logicBigKeyChest(inventory))
                    inLogic.add(bigKeyChest);          
            }
        }

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
     * Check to see if the freezor chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicFreezorChest(Inventory inventory) {
        return !freezorChest.isAcquired();
    }
    
    /**
     * Check to see if the iced t room is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicIcedTRoom(Inventory inventory) {
        return !icedTRoom.isAcquired();
    }
    
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        if (bigChest.isAcquired())
            return false;
        
        return bigKeyAcquired();
    }
    
    /**
     * Check to see if the spike room chest is in logic
     * It's in logic if it's not opened and either:
     * 1) The hookshot is acquired
     * 2) The cape or the Cane of Byrna is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicSpikeRoom(Inventory inventory) {
        if (spikeRoom.isAcquired())
            return false;
        
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned())
            return true;
        
        return inventory.getItem(KeyItem.CAPE).isOwned() || 
                inventory.getItem(KeyItem.BYRNA).isOwned();
    }
    
    /**
     * Check to see if the map chest is in logic
     * It's in logic if it's not opened and:
     * 1) The hammer is acquired
     * 2) The hookshot or the cape or the Cane of Byrna is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicMapChest(Inventory inventory) {
        if (mapChest.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned() ||
                inventory.getItem(KeyItem.CAPE).isOwned() ||
                inventory.getItem(KeyItem.BYRNA).isOwned();
    }
    
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened and:
     * 1) The hammer is acquired
     * 2) The hookshot or the cape or the Cane of Byrna is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        if (bigKeyChest.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned() ||
                inventory.getItem(KeyItem.CAPE).isOwned() ||
                inventory.getItem(KeyItem.BYRNA).isOwned();
    }
    
    /**
     * Check to see if Kholdstare is in logic
     * It's in logic if the item isn't acquired and the hammer is acquired
     * @param inventory The current inventory
     * @return True or False if Kholdstare is in logic
     */
    private boolean logicKholdstare(Inventory inventory) {
        if (kholdstare.isAcquired())
            return false;
               
        return inventory.getItem(KeyItem.HAMMER).isOwned();
    }          
    
    //Getters and Setters for the locations below
    
    /**
     * Get a list of all the locations
     * @return All the locations in Ice Palace
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(compassChest);
        locations.add(freezorChest);
        locations.add(icedTRoom);
        
        locations.add(bigChest);
        
        locations.add(spikeRoom);
        
        locations.add(mapChest);
        locations.add(bigKeyChest);
        
        locations.add(kholdstare);
        
        return locations; 
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
     * @return the freezorChest
     */
    public Location getFreezorChest() {
        return freezorChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setFreezorChest(Item contents) {
        freezorChest.setContents(contents);
    }
    
    /**
     * @return the icedTRoom
     */
    public Location getIcedTRoom() {
        return icedTRoom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setIcedTRoom(Item contents) {
        icedTRoom.setContents(contents);
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
     * @return the spikeRoom
     */
    public Location getSpikeRoom() {
        return spikeRoom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setSpikeRoom(Item contents) {
        spikeRoom.setContents(contents);
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
     * @return the kholdstare
     */
    public Location getKholdstare() {
        return kholdstare;
    }
    
    /**
     * @param contents The Kholdstare's new item
     */
    public void setKholdstare(Item contents) {
        kholdstare.setContents(contents);
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