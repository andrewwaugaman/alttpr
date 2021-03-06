package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of Desert Palace for the Tracker
 * @author Andrew
 */
public class DesertPalace extends Dungeon {

    //Desert Palace has 6 possible item locations
    private final Location mapChest;
    
    private final Location torch;
    
    private final Location compassChest; 
    private final Location bigKeyChest; 
    
    private final Location bigChest;
    
    private final Location lanmolas;  
    
    //Desert Palace has a Big Key and a Small Key
    private final String SMALL_KEY = "Desert Palace - Small Key";
    private final String BIG_KEY = "Desert Palace - Big Key";
 
    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 1;
    
    public final static String NAME = "Desert Palace";
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 6 locations with their description
     */
    public DesertPalace() {
        super();
        
        mapChest = new Location("Desert Palace - Map Chest");
        
        torch = new Location("Desert Palace - Torch");
        
        compassChest = new Location("Desert Palace - Compass Chest");
        bigKeyChest = new Location("Desert Palace - Big Key Chest");
        
        bigChest = new Location("Desert Palace - Big Chest");
        
        lanmolas = new Location("Desert Palace - Lanmolas");
    }
    
    /**
     * Check to see if there is a way to enter the dungeon
     * Desert Palace can be entered if either:
     * 1) The book is obtained
     * 2) The flute, Titan's Mitt, and Mirror are obtained
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        if (inventory.getItem(KeyItem.BOOK).isOwned())
            return false;
        
        return !((inventory.getItem(KeyItem.FLUTE).isOwned() &&
                ((ProgressiveItem)inventory.getItem(Gloves.GLOVES))
                        .getDescription().equals(Gloves.TITANS_MITTS)
                && inventory.getItem(KeyItem.MIRROR).isOwned()));
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Desert Palace requires the following to full clear:
     * 1) Boots
     * 2) Power Gloves or Titan's Mitts
     * 3) A fire source (Lantern or Fire Rod)
     * 4) A weapon to kill Lanmolas 
     *    - Bow, Fire Rod, Ice Rod, Hammer, 
     *    - Cane of Somaria, Cane of Byrna, or Sword
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (!inventory.getItem(KeyItem.BOOTS).isOwned())
            return false;
        
        if (!inventory.getItem(Gloves.GLOVES).isOwned())
            return false;
        
        //Check the Fire Source
        //Fire Rod works as a fire source and a weapon
        if (inventory.getItem(KeyItem.FIRE_ROD).isOwned())
            return true;
        
        //No Fire Rod, Check the Lantern
        if (!inventory.getItem(KeyItem.LANTERN).isOwned())
            return false;
        
        if (inventory.getItem(KeyItem.BOW).isOwned())
            return true;
        
        if (inventory.getItem(KeyItem.ICE_ROD).isOwned())
            return true;
        
        if (inventory.getItem(KeyItem.HAMMER).isOwned())
            return true;
        
        if (inventory.getItem(KeyItem.SOMARIA).isOwned())
            return true;
        
        if (inventory.getItem(KeyItem.BYRNA).isOwned())
            return true;
        
        return inventory.getItem(Sword.SWORD).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {mapChest, torch, compassChest, 
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
     * @return The number of small keys that have been acquired
     */
    private int smallKeysAcquired() {
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {mapChest, torch, bigChest};
        
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

        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        
        if (logicTorch(inventory))
            inLogic.add(torch);
        
        if (smallKeysAcquired() == TOTAL_SMALL_KEYS) {
            if (logicCompassChest(inventory))
                inLogic.add(compassChest);
            if (logicBigKeyChest(inventory))
                inLogic.add(bigKeyChest);
        }
        
        if (bigKeyAcquired()) {
            if (logicBigChest(inventory))
                inLogic.add(bigChest);
            
            if (smallKeysAcquired() == TOTAL_SMALL_KEYS) {
                if (logicLanmolas(inventory))
                    inLogic.add(lanmolas);  
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
     * Check to see if the torch is in logic
     * It's in logic if it's not opened and the boots are acquired
     * @param inventory The current inventory
     * @return True or False if the chest is in logic
     */
    private boolean logicTorch(Inventory inventory) {
        if (torch.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.BOOTS).isOwned();
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
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened and the big key is acquired
     * @param inventory The current inventory (unusued)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        if (bigChest.isAcquired())
            return false;
    
        return bigKeyAcquired();
    }
    
    /**
     * Check to see if Lanmolas are in logic
     * They're in logic if the item isn't acquired and 
     * the big key is acquired.  
     * Items Required:
     * 1) Power Gloves or Titan's Mitts
     * 2) Fire Source (Lantern or Fire Rod)
     * 3) A weapon to kill Lanmolas 
     *    - Bow, Fire Rod, Ice Rod, Hammer, 
     *    - Cane of Somaria, Cane of Byrna, or Sword
     * @param inventory The current inventory
     * @return True or False if the Lanmolas are in logic
     */
    private boolean logicLanmolas(Inventory inventory) {
        if (lanmolas.isAcquired())
            return false;
        
        if (!bigKeyAcquired())
            return false;
               
        if(!inventory.getItem(Gloves.GLOVES).isOwned())
            return false;
        
        //Check the Fire Source
        //Fire Rod works as a fire source and a weapon
        if(inventory.getItem(KeyItem.FIRE_ROD).isOwned())
            return true;
        
        //No Fire Rod, Check the Lantern
        if(!inventory.getItem(KeyItem.LANTERN).isOwned())
            return false;
        
        if(inventory.getItem(KeyItem.BOW).isOwned())
            return true;
        
        if(inventory.getItem(KeyItem.ICE_ROD).isOwned())
            return true;
        
        if(inventory.getItem(KeyItem.HAMMER).isOwned())
            return true;
        
        if(inventory.getItem(KeyItem.SOMARIA).isOwned())
            return true;
        
        if(inventory.getItem(KeyItem.BYRNA).isOwned())
            return true;
        
        return inventory.getItem(Sword.SWORD).isOwned();
    }          
    
    //Getters and Setters for the locations below
    
    /**
     * Get a list of all the locations
     * @return All the locations in Desert Palace
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(mapChest);
        
        locations.add(torch);
        
        locations.add(compassChest);
        locations.add(bigKeyChest);
        
        locations.add(bigChest);
        
        locations.add(lanmolas);
        
        return locations; 
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
     * @return the torch
     */
    public Location getTorch() {
        return torch;
    }
    
    /**
     * @param contents The new item on the torch
     */
    public void setTorch(Item contents) {
        torch.setContents(contents);
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
     * @return the lanmolas
     */
    public Location getLanmolas() {
        return lanmolas;
    }
    
    /**
     * @param contents Lanmolas new item
     */
    public void setLanmolas(Item contents) {
        lanmolas.setContents(contents);
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
