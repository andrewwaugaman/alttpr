package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of Dark Palace for the Tracker
 * @author Andrew
 */
public class DarkPalace extends Dungeon {
    
    //Desert Palace has 14 possible item locations
    private final Location shooterRoom;
    
    private final Location mapChest;
    private final Location arenaLedge;
    
    private final Location arenaBridge;
    private final Location stalfosBasement;
    
    private final Location compassChest;
    
    private final Location darkBasementLeft;
    private final Location darkBasementRight;
    
    private final Location darkMazeTop;
    private final Location darkMazeBottom;
    private final Location bigChest;
    
    private final Location bigKeyChest;
    private final Location harmlessHellway;
    
    private final Location helmasaur;  
    
    //Dark Palace has a Big Key and 6 Small Keys
    private final String SMALL_KEY = "Palace of Darkness - Small Key";
    private final String BIG_KEY = "Palace of Darkness - Big Key";
    
    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 6;
    
    public final static String NAME = "Palace of Darkness";
    
    //Used to check if Dark Palace is Open
    private final DarkWorld darkWorld;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 14 locations with their description
     * @param darkWorld The Dark World the Dark Palace is in
     */
    public DarkPalace(DarkWorld darkWorld) {
        super();
        
        shooterRoom = new Location("Palace of Darkness - Shooter Room");
        
        mapChest = new Location("Palace of Darkness - Map Chest");
        arenaLedge = new Location("Palace of Darkness - The Arena - Ledge");
        
        arenaBridge = new Location("Palace of Darkness - The Arena - Bridge");
        stalfosBasement = new Location("Palace of Darkness - Stalfos Basement");
        
        compassChest = new Location("Palace of Darkness - Compass Chest");
        
        darkBasementLeft = 
                new Location("Palace of Darkness - Dark Basement - Left");
        darkBasementRight =
                new Location("Palace of Darkness - Dark Basement - Right");
        
        darkMazeTop = new Location("Palace of Darkness - Dark Maze - Top");
        darkMazeBottom = 
                new Location("Palace of Darkness - Dark Maze - Bottom");
        bigChest = new Location("Palace of Darkness - Big Chest");
        
        bigKeyChest = new Location("Palace of Darkness - Big Key Chest");
        harmlessHellway = new Location("Palace of Darkness - Harmless Hellway");
        
        helmasaur = new Location("Palace of Darkness - Helmasaur");
        
        this.darkWorld = darkWorld;
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
        
        if (logicShooterRoom(inventory))
            inLogic.add(shooterRoom); 
        
        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        if (logicArenaLedge(inventory))
            inLogic.add(arenaLedge);
        
        if (inventory.getItem(Item.BOW).isOwned() &&
            inventory.getItem(Item.HAMMER).isOwned())
                inLogic.addAll(keyLogicBowAndHammer(inventory));
        else
            inLogic.addAll(keyLogicFront(inventory));
 
        return inLogic;
    }
       
    /**
     * Check to see if there is a way to enter the dungeon
     * Dark Palace can be entered if the east side of the
     * Dark World is accessible (no additional requirements)
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        return !darkWorld.eastDarkAccess(inventory);
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Dark Palace requires the following to full clear:
     * 1) Bow
     * 2) Hammer
     * 3) Lantern
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (!inventory.getItem(Item.BOW).isOwned())
            return false;
        
        if (!inventory.getItem(Item.HAMMER).isOwned())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the shooter room is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the location is in logic
     */
    private boolean logicShooterRoom(Inventory inventory) {
        return !shooterRoom.isAcquired();
    }
    
    /**
     * Check to see if the map chest room is in logic
     * It's in logic if it's not opened and the bow is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicMapChest(Inventory inventory) {
        if (mapChest.isAcquired())
            return false;
        
        return inventory.getItem(Item.BOW).isOwned();
    }
    
    /**
     * Check to see if the arena ledge chest is in logic
     * It's in logic if it's not opened and the bow is acquired
     * @param inventory The current inventory 
     * @return True or False if the location is in logic
     */
    private boolean logicArenaLedge(Inventory inventory) {
        if (mapChest.isAcquired())
            return false;
        
        return inventory.getItem(Item.BOW).isOwned();
    }
    
    /**
     * Check to see how many small keys have been picked up
     * Note -- Not all chests in Dark Palace can be keys
     * @return The number of small keys that have been acquired
     */
    private int smallKeysAcquired() {
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {shooterRoom, mapChest, arenaLedge, 
            arenaBridge, stalfosBasement, compassChest, darkBasementLeft,
            darkBasementRight, bigKeyChest, harmlessHellway};
        
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
     * small key to access.  In Dark Palace a small key could be 
     * locked in either the big key chest or the harmless hellway
     * @return The number of small keys that could be locked
     */
    private int smallKeysLocked() {
        int numObtainedKeys = 0;
        
        Location[] possibleSmallKeys = {shooterRoom, mapChest, arenaLedge, 
            arenaBridge, stalfosBasement, compassChest, darkBasementLeft,
            darkBasementRight};
        
        //If there's a location outside of the big key chest and the harmless
        //hellway that hasn't been checked exit the check
        for (Location location : possibleSmallKeys) {
            if (!location.isAcquired())
                return 0;
            else
                if (location.getContents().getDescription().equals(SMALL_KEY))
                    numObtainedKeys++;
        }
        
        //Since this is reached, the only chests that could have keys that 
        //haven't been obtained are the big key chest and the harmless 
        //hellway.  First check to see if either of the chests have keys,
        //and then subtract the number of keys found from the total.  That 
        //will result in the number of possibly locked small keys
        if (bigKeyChest.isAcquired() && bigKeyChest.getContents()
                .getDescription().equals(SMALL_KEY))
            numObtainedKeys++;
        
        if (harmlessHellway.isAcquired() && harmlessHellway.getContents()
                .getDescription().equals(SMALL_KEY))
            numObtainedKeys++;
        
        return TOTAL_SMALL_KEYS - numObtainedKeys;
    }
    
    /**
     * Check to see what's in logic if the bow and hammer are acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired (or are locked in a chest)
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogicBowAndHammer(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if(logicArenaBridge(inventory))
            inLogic.add(arenaBridge);
        if(logicStalfosBasement(inventory))
            inLogic.add(stalfosBasement);
        
        //4 keys means the back of Dark Palace is now open
        if (smallKeysAcquired() >= 4) {
            if(logicCompassChest(inventory))
                inLogic.add(compassChest);
            if(logicDarkBasementLeft(inventory))
                inLogic.add(darkBasementLeft);
            if(logicDarkBasementRight(inventory))
                inLogic.add(darkBasementRight);
        }
        
        //6 keys means all of Dark Palace is now open
        //This check includes possible key locked locations, which 
        //is why they are added to the number of obtained keys
        if (smallKeysAcquired() + smallKeysLocked() >= 6) {
            if(logicDarkMazeTop(inventory))
                inLogic.add(darkMazeTop);
            if(logicDarkMazeBottom(inventory))
                inLogic.add(darkMazeBottom);
            if(logicBigChest(inventory))
                inLogic.add(bigChest);
            
            if(logicBigKeyChest(inventory))
                inLogic.add(bigKeyChest);
            if(logicHarmlessHellway(inventory))
                inLogic.add(harmlessHellway);
            
            if(logicHelmasaur(inventory))
                inLogic.add(helmasaur);
        }
            
        return inLogic;
    }
    
    /**
     * Check to see what's in logic if the bow and/or hammer aren't acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired (or are locked in a chest)
     * Note -- Without the bow and the hammer, it is impossible to open
     * the small key door to get to Helmasaur so there's one less key 
     * required in order to reach the other locations in Dark Palace
     * @param inventory
     * @return 
     */
    private ArrayList<Location> keyLogicFront(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //1 key means the front door is open
        if (smallKeysAcquired() >= 1) {
            if(logicArenaBridge(inventory))
                inLogic.add(arenaBridge);
            if(logicStalfosBasement(inventory))
                inLogic.add(stalfosBasement);
        }
                
        //3 keys means the back of Dark Palace is now open
        if (smallKeysAcquired() >= 3) {
            if(logicCompassChest(inventory))
                inLogic.add(compassChest);
            if(logicDarkBasementLeft(inventory))
                inLogic.add(darkBasementLeft);
            if(logicDarkBasementRight(inventory))
                inLogic.add(darkBasementRight);
        }
        
        //5 keys means all of Dark Palace is now open (Except Helmasaur)
        //This check includes possible key locked locations, which 
        //is why they are added to the number of obtained keys
        if (smallKeysAcquired() + smallKeysLocked() >= 5) {
            if(logicDarkMazeTop(inventory))
                inLogic.add(darkMazeTop);
            if(logicDarkMazeBottom(inventory))
                inLogic.add(darkMazeBottom);
            if(logicBigChest(inventory))
                inLogic.add(bigChest);
            
            if(logicBigKeyChest(inventory))
                inLogic.add(bigKeyChest);
            if(logicHarmlessHellway(inventory))
                inLogic.add(harmlessHellway);
        }
            
        return inLogic;
    }
    
    /**
     * Check to see if the arena bridge is in logic
     * It's in logic if it's not opened 
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicArenaBridge(Inventory inventory) {
        return !arenaBridge.isAcquired();
    }
    
    /**
     * Check to see if the stalfos basement is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicStalfosBasement(Inventory inventory) {
        return !stalfosBasement.isAcquired();
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
     * Check to see if the dark basement left chest is in logic
     * It's in logic if it's not opened and the lantern is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicDarkBasementLeft(Inventory inventory) {
        if(darkBasementLeft.isAcquired())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the dark basement right chest is in logic
     * It's in logic if it's not opened and the lantern is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicDarkBasementRight(Inventory inventory) {
        if(darkBasementRight.isAcquired())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the dark maze top chest is in logic
     * It's in logic if it's not opened and the lantern is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicDarkMazeTop(Inventory inventory) {
        if(darkMazeTop.isAcquired())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the dark maze bottom chest is in logic
     * It's in logic if it's not opened and the lantern is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicDarkMazeBottom(Inventory inventory) {
        if(darkMazeBottom.isAcquired())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened and the lantern is acquired
     * @param inventory The current inventory
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        if(bigChest.isAcquired())
            return false;
       
        if(!bigKeyAcquired())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {shooterRoom, mapChest, arenaLedge, 
            arenaBridge, stalfosBasement, compassChest, darkBasementLeft,
            darkBasementRight, darkMazeTop, darkMazeBottom, bigKeyChest,
            harmlessHellway};
        
        for (Location location : possibleBigKey) {
            if (location.isAcquired() && location.getContents()
                    .getDescription().equals(BIG_KEY))
                return true;
        }
        
        return false;
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
     * Check to see if the harmless hellway is in logic
     * It's in logic if it's not opened 
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicHarmlessHellway(Inventory inventory) {
        return !harmlessHellway.isAcquired();
    }
    
    /**
     * Check to see if Helmasaur is in logic
     * It's in logic if the item isn't acquired and 
     * the big key is acquired along with the lantern
     * Note - Key Logic checks the Hammer and Bow
     * @param inventory The current inventory
     * @return True or False if the Helmasaur is in logic
     */
    private boolean logicHelmasaur(Inventory inventory) {
        //If it's acquired it doesn't need to be listed as available
        if(helmasaur.isAcquired())
            return false;
        
        if(!bigKeyAcquired())
            return false;
               
        return inventory.getItem(Item.LANTERN).isOwned();
    }          
    
    //Getters and Setters for the locations below

    /**
     * @return the shooterRoom
     */
    public Location getShooterRoom() {
        return shooterRoom;
    }
    
    /**
     * @param contents The new contents of the shooter room chest
     */
    public void setShooterRoom(Item contents) {
        shooterRoom.setContents(contents);
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
     * @return the arenaLedge
     */
    public Location getArenaLedge() {
        return arenaLedge;
    }
    
    /**
     * @param contents The new contents of the ledge chest
     */
    public void setArenaLedge(Item contents) {
        arenaLedge.setContents(contents);
    }
    
    /**
     * @return the arenaBridge
     */
    public Location getArenaBridge() {
        return arenaBridge;
    }
    
    /**
     * @param contents The new contents of the bridge chest
     */
    public void setArenaBridge(Item contents) {
        arenaBridge.setContents(contents);
    }
    
    /**
     * @return the stalfosBasement
     */
    public Location getStalfosBasement() {
        return stalfosBasement;
    }
    
    /**
     * @param contents The new contents of the basement chest
     */
    public void setStalfosBasement(Item contents) {
        stalfosBasement.setContents(contents);
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
     * @return the darkBasementLeft
     */
    public Location getDarkBasementLeft() {
        return darkBasementLeft;
    }
    
    /**
     * @param contents The new contents of the dark basement left chest
     */
    public void setDarkBasementLeft(Item contents) {
        darkBasementLeft.setContents(contents);
    }
    
    /**
     * @return the darkBasementRight
     */
    public Location getDarkBasementRight() {
        return darkBasementRight;
    }
    
    /**
     * @param contents The new contents of the dark basement right chest
     */
    public void setDarkBasementRight(Item contents) {
        darkBasementRight.setContents(contents);
    }
    
    /**
     * @return the darkMazeTop
     */
    public Location getDarkMazeTop() {
        return darkMazeTop;
    }
    
    /**
     * @param contents The new contents of the dark maze top chest
     */
    public void setDarkMazeTop(Item contents) {
        darkMazeTop.setContents(contents);
    }
    
    /**
     * @return the darkMazeBottom
     */
    public Location getDarkMazeBottom() {
        return darkMazeBottom;
    }
    
    /**
     * @param contents The new contents of the dark basement left chest
     */
    public void setDarkMazeBottom(Item contents) {
        darkMazeBottom.setContents(contents);
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
     * @return the harmlessHellway
     */
    public Location getHarmlessHellway() {
        return harmlessHellway;
    }
    
    /**
     * @param contents The new contents of the harmless hellway chest
     */
    public void setHarmlessHellway(Item contents) {
        harmlessHellway.setContents(contents);
    }
    
    /**
     * @return the helmasaur
     */
    public Location getHelmasaur() {
        return helmasaur;
    }
    
    /**
     * @param contents The Helmasaur new item
     */
    public void setArmosKnights(Item contents) {
        helmasaur.setContents(contents);
    }
}
