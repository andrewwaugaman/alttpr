package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of TurtleRock for the Tracker
 * @author Andrew
 */
public class TurtleRock extends Dungeon {
    
    //Turtle Rock has 12 possible item locations
    private final Location compassChest;
    
    private final Location rollerRoomLeft;
    private final Location rollerRoomRight;
    
    private final Location chainChomps;
    
    private final Location bigKeyChest;
    
    private final Location bigChest;
    
    private final Location crystarollerRoom;
    
    private final Location eyeBridgeTopRight;
    private final Location eyeBridgeTopLeft;
    private final Location eyeBridgeBottomRight;
    private final Location eyeBridgeBottomLeft;
                
    private final Location trinexx; 
    
    //Turtle Rock has a Big Key and 4 Small Keys in locations
    private final String SMALL_KEY = "Turtle Rock - Small Key";
    private final String BIG_KEY = "Turtle Rock - Big Key";

    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 4;
    
    public final static String NAME = "Turtle Rock";
    
    //Used to check if Turtle Rock is Open
    private final DarkWorld darkWorld;
    private KeyItem medallion;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 8 locations with their description
     * @param darkWorld The Dark World the Turtle Rock is in
     */
    public TurtleRock(DarkWorld darkWorld) {
        super();
        
        compassChest = new Location("Turtle Rock - Compass Chest");

        rollerRoomLeft = new Location("Turtle Rock - Roller Room - Left");
        rollerRoomRight = new Location("Turtle Rock - Roller Room - Left");

        chainChomps = new Location("Turtle Rock - Chain Chomps");

        bigKeyChest = new Location("Turtle Rock - Big Key Chest");     

        bigChest = new Location("Turtle Rock - Big Chest");

        crystarollerRoom = new Location("Turtle Rock - Crystalroller Room");

        eyeBridgeTopRight = new Location(
                "Turtle Rock - Eye Bridge - Top Right");
        eyeBridgeTopLeft = new Location(
                "Turtle Rock - Eye Bridge - Top Left");
        eyeBridgeBottomRight = new Location(
                "Turtle Rock - Eye Bridge - Bottom Right");
        eyeBridgeBottomLeft = new Location(
                "Turtle Rock - EyeBridge - Bottom Left");
                       
        trinexx = new Location("Turtle Rock - Trinexx");
        
        this.darkWorld = darkWorld;
        medallion = new KeyItem(KeyItem.UNKNOWN_MEDALLION);
    }
    
    /**
     * Check to see if there is a way to enter the dungeon
     * Turtle Rock can be entered if:
     * 1) East Dark Death Mountain is accessible 
     * 2) The Medallion to open it and any sword are obtained
     * 3) The Cane of Somaria is obtained
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        if (!darkWorld.turtleRockPlatformAccess(inventory))
            return true;
        
        if (medallion.getDescription().equals(KeyItem.UNKNOWN_MEDALLION))
            return true;
        else
            if (!(inventory.getItem(medallion.getDescription()).isOwned() &&
                    inventory.getItem(Sword.SWORD).isOwned()))
                return true;
        
        return !(inventory.getItem(KeyItem.SOMARIA).isOwned());
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Turtle Rock requires:
     * 1) The fire rod
     * 2) The lantern
     * 3) The ice rod
     * 4) The mirror shield or an invincibility item
     * Note - Sword is required to enter so that's checked by closed
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
              
        if (!inventory.getItem(KeyItem.FIRE_ROD).isOwned())
            return false;
        
        if (!inventory.getItem(KeyItem.LANTERN).isOwned())
            return false;
        
        if (!inventory.getItem(KeyItem.ICE_ROD).isOwned())
            return false;
        
        if (((Shield)(inventory.getItem(Shield.SHIELD))).getDescription()
                .equals(Shield.MIRROR_SHIELD))
            return true;
        
        if (inventory.getItem(KeyItem.CAPE).isOwned())
            return true;
        
        return inventory.getItem(KeyItem.BYRNA).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {compassChest, rollerRoomLeft, 
            rollerRoomRight, chainChomps, bigKeyChest};
        
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
        
        Location[] possibleSmallKeys = {compassChest, rollerRoomLeft, 
            rollerRoomRight, chainChomps, bigKeyChest, bigChest, 
            crystarollerRoom, eyeBridgeTopRight, eyeBridgeTopLeft,
            eyeBridgeBottomRight, eyeBridgeBottomLeft};
        
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
     * small key to access.  In Turtle Rock a small key could be 
     * locked in the big key chest
     * @return The number of small keys that could be locked
     */
    private int smallKeysLocked() {
        int numObtainedKeys = 0;
        
        Location[] possibleSmallKeys = {compassChest, rollerRoomLeft, 
            rollerRoomRight, chainChomps, bigChest, crystarollerRoom, 
            eyeBridgeTopRight, eyeBridgeTopLeft, eyeBridgeBottomRight, 
            eyeBridgeBottomLeft};
        
        //If there's a location outside of the big key chest 
        //that hasn't been checked exit the check
        for (Location location : possibleSmallKeys) {
            if (!location.isAcquired())
                return 0;
            else
                if (location.getContents().getDescription().equals(SMALL_KEY))
                    numObtainedKeys++;
        }
        
        //Since this is reached, the big key chest has to have the small key 
        //First check to see if it's opened, if not then the key could be
        //locked with poor key usage
        if (bigKeyChest.isAcquired() && bigKeyChest.getContents()
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
        
        if (logicCompassChest(inventory))
            inLogic.add(compassChest);
        if (logicRollerRoomLeft(inventory))
            inLogic.add(rollerRoomLeft);
        if (logicRollerRoomRight(inventory))
            inLogic.add(rollerRoomRight);
        
        inLogic.addAll(keyLogic(inventory));

        return inLogic;
    }

    /**
     * The item locations that are in logic depend on how many 
     * keys have been acquired 
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //One key is required to reach the chain chomps room
        if (smallKeysAcquired() >= 1)
            if (logicChainChomps(inventory))
                inLogic.add(chainChomps);
        
        //Two keys are required to reach the next section (Northwest door
        //the main room and the chain chomps door both require a key)
        //Note - The room before chain chomps has a key inside it that must
        //be used on that door
        if (smallKeysAcquired() >= 2) {            
            //If the big key isn't obtained (Ex - Locked in Roller Room and no 
            //Fire Rod), the big key chest is in logic due to the key in the 
            //room before only being able to be used on the door leading to it
            if (!bigKeyAcquired()) {
                if (logicBigKeyChest(inventory))
                    inLogic.add(bigKeyChest);
            }
            //Since the Big Key is obtained, the Big Chest 
            //and the Crystaroller Room are in logic.  
            else {
                if (logicBigChest(inventory))
                    inLogic.add(bigChest);
                if (logicCrystarollerRoom(inventory))
                    inLogic.add(crystarollerRoom);
                
                //A 3rd key is required to reach the laser bridge (There's 
                //a key in the room before the big key chest that could be 
                //used on the door to the big key chest, this would be the 
                //only available option)
                if (smallKeysAcquired() >= 3)
                    inLogic.addAll(logicLaserBridge(inventory));
                
                //All 4 keys are required to reach Trinexx and the big key
                //chest (the key from in front of it could be stolen to use
                //on the door in the crystaroller room and then the 3rd key
                //from a location could be used on the door leading to 
                //Trinexx.  If it's used in the intended way, a 4th key is
                //required to use on the door to Trinexx).  A key could be 
                //"locked" in the big key chest if all the other keys are 
                //used and the door before it was never opened, so checking
                //to see if that was a possiblity
                if (smallKeysAcquired() + smallKeysLocked() == 
                        TOTAL_SMALL_KEYS) {
                    if (logicBigKeyChest(inventory))
                        inLogic.add(bigKeyChest);
                    if (logicTrinexx(inventory))
                        inLogic.add(trinexx);
                }
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
     * Check to see if the roller room left chest is in logic
     * It's in logic if it's not opened and the fire rod is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicRollerRoomLeft(Inventory inventory) {
        if (rollerRoomLeft.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.FIRE_ROD).isOwned();
    }
    
    /**
     * Check to see if the roller room right chest is in logic
     * It's in logic if it's not opened and the fire rod is acquired
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicRollerRoomRight(Inventory inventory) {
        if (rollerRoomRight.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.FIRE_ROD).isOwned();    
    }
    
    /**
     * Check to see if the chain chomps chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicChainChomps(Inventory inventory) {
        return !chainChomps.isAcquired();
    }
    
    /**
     * Check to see if the big key chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicBigKeyChest(Inventory inventory) {
        return !bigKeyChest.isAcquired();
    }
 
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory 
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        return !bigChest.isAcquired();
    }
    
    /**
     * Check to see if the crystaroller room is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicCrystarollerRoom(Inventory inventory) {
        return !chainChomps.isAcquired();
    }
    
    /**
     * Get the locations that are currently in logic on laser bridge
     * They're in logic if they're not open, the lantern is acquired, 
     * and either the mirror shield or an invincibility item are acquired
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicLaserBridge(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (!inventory.getItem(KeyItem.LANTERN).isOwned())
            return inLogic;
        
        if (((Shield)(inventory.getItem(Shield.SHIELD))).getDescription()
                .equals(Shield.MIRROR_SHIELD) || 
                inventory.getItem(KeyItem.CAPE).isOwned() ||
                inventory.getItem(KeyItem.BYRNA).isOwned()) {
            if (!eyeBridgeTopRight.isAcquired())
                inLogic.add(eyeBridgeTopRight);
            if (!eyeBridgeTopLeft.isAcquired())
                inLogic.add(eyeBridgeTopLeft);
            if (!eyeBridgeBottomRight.isAcquired())
                inLogic.add(eyeBridgeBottomRight);
            if (!eyeBridgeBottomLeft.isAcquired())
                inLogic.add(eyeBridgeBottomLeft);
        }            

        return inLogic;
    }  
    
    /**
     * Check to see if Trinexx is in logic
     * It's in logic if the item isn't acquired and:
     * 1) The lantern is acquired
     * 2) The ice rod is acquired
     * @param inventory The current inventory
     * @return True or False if Trinexx is in logic
     */
    private boolean logicTrinexx(Inventory inventory) {
        if (trinexx.isAcquired())
            return false;
        
        if (!inventory.getItem(KeyItem.LANTERN).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.ICE_ROD).isOwned();
    }          
    
    //Getters and Setters for the locations below
    
    /**
     * Get a list of all the locations
     * @return All the locations in Turtle Rock
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(compassChest);
        
        locations.add(rollerRoomLeft);
        locations.add(rollerRoomRight);
        
        locations.add(chainChomps);
        
        locations.add(bigKeyChest);
        
        locations.add(bigChest);
        
        locations.add(crystarollerRoom);
        
        locations.add(eyeBridgeTopRight);
        locations.add(eyeBridgeTopLeft);
        locations.add(eyeBridgeBottomRight);
        locations.add(eyeBridgeBottomLeft);
        
        locations.add(trinexx);
        
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
     * @return the rollerRoomLeftChest
     */
    public Location getRollerRoomLeft() {
        return rollerRoomLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setRollerRoomLeft(Item contents) {
        rollerRoomLeft.setContents(contents);
    }
    
    /**
     * @return the rollerRoomRightChest
     */
    public Location getRollerRoomRight() {
        return rollerRoomRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setRollerRoomRight(Item contents) {
        rollerRoomRight.setContents(contents);
    }
    
    /**
     * @return the chainChompsChest
     */
    public Location getChainChomps() {
        return chainChomps;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setChainChomps(Item contents) {
        chainChomps.setContents(contents);
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
     * @return the crystarollerRoomChest
     */
    public Location getCrystarollerRoom() {
        return crystarollerRoom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCrystarollerRoom(Item contents) {
        crystarollerRoom.setContents(contents);
    }
    
    /**
     * @return the eyeBridgeTopRight
     */
    public Location getEyeBridgeTopRight() {
        return eyeBridgeTopRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setEyeBridgeTopRight(Item contents) {
        eyeBridgeTopRight.setContents(contents);
    }

    /**
     * @return the eyeBridgeTopLeftt
     */
    public Location getEyeBridgeTopLeft() {
        return eyeBridgeTopLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setEyeBridgeTopLeft(Item contents) {
        eyeBridgeTopLeft.setContents(contents);
    }

    /**
     * @return the eyeBridgeBottomRight
     */
    public Location getEyeBridgeBottomRight() {
        return eyeBridgeBottomRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setEyeBridgeBottomRight(Item contents) {
        eyeBridgeBottomRight.setContents(contents);
    }

    /**
     * @return the eyeBridgeBottomLeft
     */
    public Location getEyeBridgeBottomLeft() {
        return eyeBridgeBottomLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setEyeBridgeBottomLeft(Item contents) {
        eyeBridgeBottomLeft.setContents(contents);
    }
    
    /**
     * @return trinexx
     */
    public Location getTrinexx() {
        return trinexx;
    }
    
    /**
     * @param contents Trinexx' new item
     */
    public void setTrinexx(Item contents) {
        trinexx.setContents(contents);
    }
    
    /**
     * @return the medallion to open Mire
     */
    public KeyItem getMedallion() {
        return medallion;
    }
    
    /**
     * @param medallion The new medallion to open Mire
     */
    public void setMedallion(KeyItem medallion) {
        this.medallion = medallion;
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