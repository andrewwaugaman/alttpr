/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public final static String NAME = "Desert Palace";
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 6 chests with their description
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
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if(closed(inventory)){
            return inLogic;
        }

        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        if (logicTorch(inventory))
            inLogic.add(torch);
        if (logicCompassChest(inventory))
            inLogic.add(compassChest);
        if (logicBigKeyChest(inventory))
            inLogic.add(bigKeyChest);
        if (logicBigChest(inventory))
            inLogic.add(bigChest);
        if (logicLanmolas(inventory))
            inLogic.add(lanmolas);  
        return inLogic;
    }
       
    /**
     * Check to see if there is a way to enter the dungeon
     * Desert Palace can be entered if either:
     * 1) The book is obtained
     * 2) The flute, Titan's Mitt, and Mirror are obtained
     * @param inventory The current inventory 
     * @return True or False if it's accessible
     */
    private boolean closed(Inventory inventory){
        if (inventory.getItem(Item.BOOK).isOwned())
            return false;
        
        return (inventory.getItem(Item.FLUTE).isOwned() &&
                ((ProgressiveItem)inventory.getItem(Gloves.GLOVES))
                        .getDescription().equals(Gloves.TITANS_MITTS)
                && inventory.getItem(Item.MIRROR).isOwned());
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
        if(closed(inventory))
            return false;
        
        //1) Check the Boots
        if(!inventory.getItem(Item.BOOTS).isOwned())
            return false;
        
        //2) Check the Gloves 
        if(!inventory.getItem(Gloves.GLOVES).isOwned())
            return false;
        
        //3) Check the Fire Source
        //Fire Rod works as a fire source and a weapon
        if(inventory.getItem(Item.FIRE_ROD).isOwned())
            return true;
        
        //No Fire Rod, Check the Lantern
        if(!inventory.getItem(Item.LANTERN).isOwned())
            return false;
        
        //4) Check the various weapons
        if(inventory.getItem(Item.BOW).isOwned())
            return true;
        
        if(inventory.getItem(Item.ICE_ROD).isOwned())
            return true;
        
        if(inventory.getItem(Item.HAMMER).isOwned())
            return true;
        
        //I need to check if this damages it
        if(inventory.getItem(Item.SOMARIA).isOwned())
            return true;
        
        if(inventory.getItem(Item.BYRNA).isOwned())
            return true;
        
        return inventory.getItem(Sword.SWORD).isOwned();
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
        if(torch.isAcquired())
            return false;
        
        return inventory.getItem(Item.BOOTS).isOwned();
    }
    
    
    /**
     * Check to see if the small key has been picked up
     * This checks all the locations possible without the big key
     * @return True or False if the big key is acquired
     */
    private boolean smallKeyAcquired() {
        if(mapChest.isAcquired() && 
                mapChest.getContents().getDescription().equals(SMALL_KEY))
            return true;
        
        if(torch.isAcquired() && 
                torch.getContents().getDescription().equals(SMALL_KEY))
            return true;
        
        return bigChest.isAcquired() &&
                bigChest.getContents().getDescription().equals(SMALL_KEY);
    }

    /**
     * Check to see if the compass chest is in logic
     * It's in logic if it's not opened and the small key is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicCompassChest(Inventory inventory) {
        if(compassChest.isAcquired())
            return false;
        
        return smallKeyAcquired();
    }
    
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened and the small key is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        if(bigKeyChest.isAcquired())
            return false;
        
        return smallKeyAcquired();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible without the big key
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        if(mapChest.isAcquired() && 
                mapChest.getContents().getDescription().equals(BIG_KEY))
            return true;
        
        if(torch.isAcquired() && 
                torch.getContents().getDescription().equals(BIG_KEY))
            return true;
        
        if(compassChest.isAcquired() &&
                compassChest.getContents().getDescription().equals(BIG_KEY))
            return true;
        
        return bigKeyChest.isAcquired() &&
                bigKeyChest.getContents().getDescription().equals(BIG_KEY);
    }
    
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened and the big key is acquired
     * @param inventory The current inventory (unusued)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        if(bigChest.isAcquired())
            return false;
    
        return bigKeyAcquired();
    }
    
    /**
     * Check to see if Lanmolas are in logic
     * They're in logic if the item isn't acquired and 
     * the big key and the small key are acquired.  
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
        //If it's acquired it doesn't need to be listed as available
        if(lanmolas.isAcquired())
            return false;
        
        if(!bigKeyAcquired())
            return false;
        
        if(!smallKeyAcquired())
            return false;
               
        //1) Check the Gloves 
        if(!inventory.getItem(Gloves.GLOVES).isOwned())
            return false;
        
        //2) Check the Fire Source
        //Fire Rod works as a fire source and a weapon
        if(inventory.getItem(Item.FIRE_ROD).isOwned())
            return true;
        
        //No Fire Rod, Check the Lantern
        if(!inventory.getItem(Item.LANTERN).isOwned())
            return false;
        
        //3) Check the various weapons
        if(inventory.getItem(Item.BOW).isOwned())
            return true;
        
        if(inventory.getItem(Item.ICE_ROD).isOwned())
            return true;
        
        if(inventory.getItem(Item.HAMMER).isOwned())
            return true;
        
        //I need to check if this damages it
        if(inventory.getItem(Item.SOMARIA).isOwned())
            return true;
        
        if(inventory.getItem(Item.BYRNA).isOwned())
            return true;
        
        return inventory.getItem(Sword.SWORD).isOwned();
    }          
    
    
    //Getters and Setters for the locations below
    public Location getMapChest() {
        return mapChest;
    }
    
    public void setMapChest(Item contents) {
        mapChest.setContents(contents);
    }
    
    public Location getTorch() {
        return torch;
    }
    
    public void setTorch(Item contents) {
        torch.setContents(contents);
    }

    public Location getCompassChest() {
        return compassChest;
    }
    
    public void setCompassChest(Item contents) {
        compassChest.setContents(contents);
    }

    public Location getBigKeyChest() {
        return bigKeyChest;
    }
    
    public void setBigKeyChest(Item contents) {
        bigKeyChest.setContents(contents);
    }

    public Location getBigChest() {
        return bigChest;
    }
    
    public void setBigChest(Item contents) {
        bigChest.setContents(contents);
    }

    public Location getLanmolas() {
        return lanmolas;
    }
    
    public void setLanmolas(Item contents) {
        lanmolas.setContents(contents);
    }
}
