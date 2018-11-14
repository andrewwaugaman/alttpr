package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of MiseryMire for the Tracker
 * @author Andrew
 */
public class MiseryMire extends Dungeon {
    
    //Misery Mire has 8 possible item locations
    private final Location bridgeChest;
    private final Location spikeChest;
    
    private final Location bigChest;

    private final Location mapChest;
    private final Location mainLobby;
    
    private final Location compassChest;
    private final Location bigKeyChest;
                
    private final Location vitreous; 
    
    //Misery Mire has a Big Key and 3 Small Keys in locations
    private final String SMALL_KEY = "Misery Mire - Small Key";
    private final String BIG_KEY = "Misery Mire - Big Key";

    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 3;
    
    public final static String NAME = "Misery Mire";
    
    //Used to check if Misery Mire is Open
    private final DarkWorld darkWorld;
    private Item medallion;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 8 locations with their description
     * @param darkWorld The Dark World the Misery Mire is in
     */
    public MiseryMire(DarkWorld darkWorld) {
        super();
        
        bridgeChest = new Location("Misery Mire - Bridge Chest");
        spikeChest = new Location("Misery Mire - Spike Chest");
        
        bigChest = new Location("Misery Mire - Big Chest");
        
        mapChest = new Location("Misery Mire - Map Chest");
        mainLobby = new Location("Misery Mire - Ambush Chest");
        
        compassChest = new Location("Misery Mire - Compass Chest");
        bigKeyChest = new Location("Misery Mire - Big Key Chest");     
                       
        vitreous = new Location("Misery Mire - Vitreous");
        
        this.darkWorld = darkWorld;
        medallion = new Item(Item.UNKNOWN_MEDALLION);
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
        
        if (logicBridgeChest(inventory))
            inLogic.add(bridgeChest);
        if (logicSpikeChest(inventory))
            inLogic.add(spikeChest);
        
        if (bigKeyAcquired())
            inLogic.addAll(bigKeyLogic(inventory));
        else
            inLogic.addAll(smallKeyLogic(inventory));

        return inLogic;
    }
       
    /**
     * Check to see if there is a way to enter the dungeon
     * Misery Mire can be entered if:
     * 1) The Mire Area of the Dark World is accessible
     * 2) The Medallion to open it and any sword are obtained
     * 3) The Hookshot or the Boots are obtained
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        if (!darkWorld.mireAreaAccess(inventory))
            return true;
        
        if (medallion.getDescription().equals(Item.UNKNOWN_MEDALLION))
            return true;
        else
            if (!(inventory.getItem(medallion.getDescription()).isOwned() &&
                    inventory.getItem(Sword.SWORD).isOwned()))
                return true;
        
        return !(inventory.getItem(Item.HOOKSHOT).isOwned() &&
               inventory.getItem(Item.BOOTS).isOwned());
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Misery Mire requires the lantern to full clear:
     * Note - Sword is required to enter so that's checked by closed
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
              
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the bridge chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBridgeChest(Inventory inventory) {
        return !mapChest.isAcquired();
    }
 
    /**
     * Check to see if the spike chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicSpikeChest(Inventory inventory) {
        return !mapChest.isAcquired();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {bridgeChest, spikeChest, mapChest, 
            mainLobby, compassChest, bigKeyChest};
        
        for (Location location : possibleBigKey) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(BIG_KEY))
                return true;
        }
        
        return false;
    }
    
    /**
     * Check to see what's in logic if the big key is acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired 
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> bigKeyLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //Available with no keys
        if (logicBigChest(inventory))
            inLogic.add(bigChest);
        
        //If the lantern is acquired, Vitreous and a peg 
        //switch are immediately accessible (which opens 
        //the map chest and the main lobby chest)
        if (inventory.getItem(Item.LANTERN).isOwned()) {
            if (logicVitreous(inventory))
                inLogic.add(vitreous);
            if (logicMapChest(inventory))
                inLogic.add(mapChest);
            if (logicMainLobby(inventory))
                inLogic.add(mainLobby);  
        }
        //The player can "waste" two keys in Misery Mire.
        //Opening the rupee room (could be done dark on accident)
        //and opening the fish room from the south give no chest
        //access or peg switch access.  One key is picked up for 
        //free under a pot in the spike room, the other would have 
        //to be in a chest.  The next key that is obtained would 
        //have to be used on the bottom left door in the main lobby
        //or the other door leading to the fish room (The door next
        //to the map chest is blocked by a blue peg).  Both of those
        //doors lead to a peg switch, so the map chest and main lobby
        //chest would be in logic with 2 keys obtained from chests
        else {
            if (smallKeysAcquired() >= 2) {
                if (logicMapChest(inventory))
                    inLogic.add(mapChest);
                if (logicMainLobby(inventory))
                    inLogic.add(mainLobby);  
            }
        }

        //These last two chests in Misery Mire are locked behind a door.
        //Since this door leads to no other locked doors, every key can 
        //be used on other doors before this one.  Therefore this door 
        //requires all 3 small keys that can be obtained from locations
        if (smallKeysAcquired() == TOTAL_SMALL_KEYS) {
            if (logicCompassChest(inventory))
                inLogic.add(compassChest);
            if (logicBigKeyChest(inventory))
                inLogic.add(bigKeyChest);
        }
        
        //Corner Case.  If the big key was obtained in either of these 
        //chests, the other chest will be a small key that wouldn't show
        //up as available even though the door to it would be opened
        if (compassChest.getContents().getDescription().equals(BIG_KEY) ||
                bigKeyChest.getContents().getDescription().equals(BIG_KEY)) {
            if (logicCompassChest(inventory))
                inLogic.add(compassChest);
            if (logicBigKeyChest(inventory))
                inLogic.add(bigKeyChest);
        }   
            
        return inLogic;
    }
    
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened and the hammer is acquired
     * @param inventory The current inventory 
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        return !bigChest.isAcquired();
    }
    
    /**
     * Check to see how many small keys have been picked up
     * @return The number of small keys that have been acquired
     */
    private int smallKeysAcquired() {
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {bridgeChest, spikeChest, mapChest,
            bigChest, mainLobby, compassChest, bigKeyChest, vitreous};
        
        for (Location location : possibleSmallKeys) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(SMALL_KEY))
                numSmallKeys++;
        }
        
        return numSmallKeys;
    }
    
    /**
     * Check to see what's in logic if the big key is not acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired 
     * Note -- One less locked door is accessible because the big 
     * key is required to reach the Rupee Room 
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> smallKeyLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //The player can "waste" one key in Misery Mire.
        //Opening the fish room from the south give no chest access 
        //or peg switch access.  One key is picked up for  free under 
        //a pot in the spike room.  The next key that is obtained would 
        //have to be used on the bottom left door in the main lobby
        //or the other door leading to the fish room (The door next
        //to the map chest is blocked by a blue peg).  Both of those
        //doors lead to a peg switch, so the map chest and main lobby
        //chest would be in logic with 1 key obtained from a chest
        if (smallKeysAcquired() >= 1) {
            if (logicMapChest(inventory))
                inLogic.add(mapChest);
            if (logicMainLobby(inventory))
                inLogic.add(mainLobby);  
        }

        //These last two chests in Misery Mire are locked behind a door.
        //Since this door leads to no other locked doors, every key can 
        //be used on other doors before this one.  Therefore this door 
        //requires 2 small keys that can be obtained from locations
        //Note - One less than max because the Rupee Room isn't accessible
        if (smallKeysAcquired() == TOTAL_SMALL_KEYS - 1) {
            if (logicCompassChest(inventory))
                inLogic.add(compassChest);
            if (logicBigKeyChest(inventory))
                inLogic.add(bigKeyChest);
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
     * Check to see if the main lobby chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicMainLobby(Inventory inventory) {
        return !mainLobby.isAcquired();
    }
    
    /**
     * Check to see if the compass chest is in logic
     * It's in logic if it's not opened and a fire source is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicCompassChest(Inventory inventory) {
        if (compassChest.isAcquired())
            return false;
        
        if (inventory.getItem(Item.FIRE_ROD).isOwned())
            return true;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened and a fire source is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        if (bigKeyChest.isAcquired())
            return false;
        
        if (inventory.getItem(Item.FIRE_ROD).isOwned())
            return true;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
        
    /**
     * Check to see if Vitreous is in logic
     * It's in logic if the item isn't acquired and the lantern is acquired:

     * @param inventory The current inventory
     * @return True or False if Vitreous is in logic
     */
    private boolean logicVitreous(Inventory inventory) {
        if (vitreous.isAcquired())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }          
    
    //Getters and Setters for the locations below
        
    /**
     * @return the bridgeChest
     */
    public Location getBridgeChest() {
        return bridgeChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBridgeChest(Item contents) {
        bridgeChest.setContents(contents);
    }
    
    /**
     * @return the spikeChest
     */
    public Location getSpikeChest() {
        return spikeChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setSpikeChest(Item contents) {
        spikeChest.setContents(contents);
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
     * @return the mainLobby
     */
    public Location getMainLobby() {
        return mainLobby;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setMainLobby(Item contents) {
        mainLobby.setContents(contents);
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
     * @return vitreous
     */
    public Location getVitreous() {
        return vitreous;
    }
    
    /**
     * @param contents Vitreous' new item
     */
    public void setVitreous(Item contents) {
        vitreous.setContents(contents);
    }
    
    /**
     * @return the medallion to open Mire
     */
    public Item getMedallion() {
        return medallion;
    }
    
    /**
     * @param medallion The new medallion to open Mire
     */
    public void setMedallion(Item medallion) {
        this.medallion = medallion;
    }
} 
