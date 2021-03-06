package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of Eastern Palace for the Tracker
 * @author Andrew
 */
public class EasternPalace extends Dungeon {
    
    //Eastern Palace has 6 possible item locations
    private final Location cannonballChest;
    private final Location mapChest;
    private final Location compassChest; 
    
    private final Location bigKeyChest; 
    
    private final Location bigChest;
    
    private final Location armosKnights;  

    //Eastern Palace has a Big Key, but it does not have any small keys
    public final String BIG_KEY = "Eastern Palace - Big Key";
    
    //Name of the dungeon
    public final static String NAME = "Eastern Palace";

    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon)
     * Instantiate the 6 locations with their description
     */
    public EasternPalace() {
        super();
        
        cannonballChest = new Location("Eastern Palace - Cannonball Chest");
        mapChest = new Location("Eastern Palace - Map Chest");
        compassChest = new Location("Eastern Palace - Compass Chest");
        
        bigKeyChest = new Location("Eastern Palace - Big Key Chest");
        
        bigChest = new Location("Eastern Palace - Big Chest");
        
        armosKnights = new Location("Eastern Palace - Armos Knights");
    }
    
    //Note -- Eastern Palace is always open, no need for a closed method
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Eastern Palace requires the bow and the lantern to full clear
     * (Since Bow is required, that can be used for Armos Knights)
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        return (inventory.getItem(KeyItem.BOW).isOwned() && 
                inventory.getItem(KeyItem.LANTERN).isOwned());      
    }
    
    //Note -- Eastern Palace has no small keys in locations, no need for a check
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {cannonballChest, mapChest, compassChest,
            bigKeyChest};
        
        for (Location location : possibleBigKey) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(BIG_KEY))
                return true;
        }
        
        return false;
    }
    
    /**
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        if (logicCannonballChest(inventory))
            inLogic.add(cannonballChest);
        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        if (logicCompassChest(inventory))
            inLogic.add(compassChest);
        
        if (logicBigKeyChest(inventory))
            inLogic.add(bigKeyChest);
        
        if (bigKeyAcquired()) {
            if (logicBigChest(inventory))
                inLogic.add(bigChest);
        
            if (logicArmosKnights(inventory))
                inLogic.add(armosKnights);  
        }
        
        return inLogic;
    }
    
    //Logic checks for the various locations
    
    /**
     * Check to see if the cannonball chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicCannonballChest(Inventory inventory) {
        return !cannonballChest.isAcquired();
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
     * It's in logic if it's not opened and the lantern is acquired
     * @param inventory The current inventory
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        if (bigKeyChest.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.LANTERN).isOwned();
    }
        
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened and the big key is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        if (bigChest.isAcquired())
            return false;
    
        return bigKeyAcquired();
    }
    
    /**
     * Check to see if Armos Knights are in logic
     * They're in logic if the item isn't acquired and the big key is acquired.  
     * Items Required:
     * 1) Lantern 
     * 2) Bow 
     * @param inventory The current inventory
     * @return True or False if the Armos Knights are in logic
     */
    private boolean logicArmosKnights(Inventory inventory) {
        if (armosKnights.isAcquired())
            return false;
        
        if (!bigKeyAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.LANTERN).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.BOW).isOwned();
    }
    
    //Getters and Setters for the locations below
    
    /**
     * Get a list of all the locations
     * @return All the locations in Eastern Palace
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(cannonballChest);
        locations.add(mapChest);
        locations.add(compassChest);
        
        locations.add(bigKeyChest);
        
        locations.add(bigChest);
        
        locations.add(armosKnights);
        
        return locations; 
    }
    
    /**
     * @return the cannonballChest
     */
    public Location getCannonballChest() {
        return cannonballChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCannonballChest(Item contents) {
        cannonballChest.setContents(contents);
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
     * @return the bigKeyChest
     */
    public Location getBigKeyChest() {
        return bigKeyChest;
    }
    
    /**
     * @param contents The new contents of the chest
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
     * @param contents The new contents of the chest
     */
    public void setBigChest(Item contents) {
        bigChest.setContents(contents);
    }

    /**
     * @return the armosKnights
     */
    public Location getArmosKnights() {
        return armosKnights;
    }
    
    /**
     * @param contents The Armos Knights new item
     */
    public void setArmosKnights(Item contents) {
        armosKnights.setContents(contents);
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
