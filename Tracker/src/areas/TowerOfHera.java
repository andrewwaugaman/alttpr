package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of Tower of Hera for the Tracker
 * @author Andrew
 */
public class TowerOfHera extends Dungeon {
    
    //Tower of Hera has 6 possible item locations
    private final Location basementCage;
    private final Location mapChest;
    
    private final Location bigKeyChest; 
    
    private final Location compassChest; 
    private final Location bigChest;
    private final Location moldorm;  
    
    //Tower of Hera has a Big Key and a Small Key
    private final String SMALL_KEY = "Tower of Hera - Small Key";
    private final String BIG_KEY = "Tower of Hera - Big Key";
    
    public final static String NAME = "Tower of Hera";
    
    //Used to check if Tower of Hera is Open
    private final DeathMountain deathMountain;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 6 locations with their description
     * @param deathMountain The mountain Hera sits on
     */
    public TowerOfHera(DeathMountain deathMountain) {
        super();
        
        basementCage = new Location("Tower of Hera - Basement Cage");
        mapChest = new Location("Tower of Hera - Map Chest");      
        
        bigKeyChest = new Location("Tower of Hera - Big Key Chest"); 
        
        compassChest = new Location("Tower of Hera - Compass Chest");
        bigChest = new Location("Tower of Hera - Big Chest");
        moldorm = new Location("Tower of Hera - Moldorm");
        
        this.deathMountain = deathMountain;
    }
    
    /**
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (closed(inventory)){
            return inLogic;
        }

        if (logicBasementCage(inventory))
            inLogic.add(basementCage);
        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        
        if (logicBigKeyChest(inventory))
            inLogic.add(bigKeyChest);
        
        if (logicCompassChest(inventory))
            inLogic.add(compassChest);
        if (logicBigChest(inventory))
            inLogic.add(bigChest);
        if (logicMoldorm(inventory))
            inLogic.add(moldorm);  
        
        return inLogic;
    }
       
    /**
     * Check to see if there is a way to enter the dungeon
     * Tower of Hera can be entered if Death Mountain is 
     * accessible and if either:
     * 1) The Mirror is obtained
     * 2) The Hookshot and Hammer are obtained
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        if (deathMountain.closed(inventory))
            return true; 

        if (inventory.getItem(Item.MIRROR).isOwned())
            return false;
        
        return !(inventory.getItem(Item.HOOKSHOT).isOwned()
                && inventory.getItem(Item.HAMMER).isOwned());
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Desert Palace requires the following to full clear:
     * 1) A fire source (Lantern or Fire Rod)
     * 2) A weapon to kill Moldorm
     *    - Hammer or Sword
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if(closed(inventory))
            return false;
        
        if(!inventory.getItem(Item.FIRE_ROD).isOwned() && 
                !inventory.getItem(Item.LANTERN).isOwned())
            return false;
        
        return inventory.getItem(Item.HAMMER).isOwned() || 
                inventory.getItem(Sword.SWORD).isOwned();
    }
   
    /**
     * Check to see if the basement cage is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBasementCage(Inventory inventory) {
        return !basementCage.isAcquired();
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
     * Check to see if the small key has been picked up
     * This checks all the locations possible without the big key
     * @return True or False if the big key is acquired
     */
    private boolean smallKeyAcquired() {
        if (mapChest.isAcquired() && 
                mapChest.getContents().getDescription().equals(SMALL_KEY))
            return true;
        
        if (basementCage.isAcquired() && 
                basementCage.getContents().getDescription().equals(SMALL_KEY))
            return true;
        
        if (compassChest.isAcquired() &&
                compassChest.getContents().getDescription().equals(SMALL_KEY))
            return true;
        
        if (bigChest.isAcquired() &&
                bigChest.getContents().getDescription().equals(SMALL_KEY))
            return true;
        
        return moldorm.isAcquired() && 
               moldorm.getContents().getDescription().equals(SMALL_KEY);
    }
    
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened, the small key is acquired,
     * and a fire source is acquired
     * @param inventory The current inventory
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        if (bigKeyChest.isAcquired())
            return false;
        
        if (!smallKeyAcquired())
            return false;
        
        return inventory.getItem(Item.FIRE_ROD).isOwned() || 
                inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible without the big key
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        if (basementCage.isAcquired() &&
                basementCage.getContents().getDescription().equals(BIG_KEY))
            return true;
                
        if (mapChest.isAcquired() && 
                mapChest.getContents().getDescription().equals(BIG_KEY))
            return true;
        
        return bigKeyChest.isAcquired() &&
                bigKeyChest.getContents().getDescription().equals(BIG_KEY);
    }

    /**
     * Check to see if the compass chest is in logic
     * It's in logic if it's not opened and the big key is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicCompassChest(Inventory inventory) {
        if (compassChest.isAcquired())
            return false;
        
        return bigKeyAcquired();
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
     * Check to see Moldorm is in logic
     * They're in logic if the item isn't acquired and 
     * the big key is acquired.  
     * Items Required:
     * 1) A weapon to kill Moldorm 
     *    - Hammer or Sword 
     * @param inventory The current inventory
     * @return True or False if Moldorm is in logic
     */
    private boolean logicMoldorm(Inventory inventory) {
        if (moldorm.isAcquired())
            return false;
        
        if (!bigKeyAcquired())
            return false;
        
        return inventory.getItem(Item.HAMMER).isOwned() || 
                inventory.getItem(Sword.SWORD).isOwned();
    }          
    
    //Getters and Setters for the locations below  
    
    /**
     * @return the basementCage
     */
    public Location getBasementCage() {
        return basementCage;
    }
 
    /**
     * @param contents The new item in the basement cage
     */
    public void setBasementCage(Item contents) {
        basementCage.setContents(contents);
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
     * @param contents The new contents of the chest
     */    
    public void setBigKeyChest(Item contents) {
        bigKeyChest.setContents(contents);
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
     * @return the moldorm
     */    
    public Location getMoldorm() {
        return moldorm;
    }

    /**
     * @param contents Moldorm's new item
     */
    public void setMoldorm(Item contents) {
        moldorm.setContents(contents);
    }
    
}
