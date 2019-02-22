package areas;

import items.*;
import java.util.ArrayList;

/**
 * Implementation of GanonsTower for the Tracker
 * @author Andrew
 */
public class GanonsTower extends Dungeon {
    
    //Ganons Tower has 27 possible item locations
    private final Location bobsTorch;
            
    private final Location dmRoomTopLeft;
    private final Location dmRoomTopRight;
    private final Location dmRoomBottomLeft;
    private final Location dmRoomBottomRight;
    
    private final Location mapChest;
            
    private final Location firesnakeRoom;

    private final Location randomizerRoomTopLeft;
    private final Location randomizerRoomTopRight;
    private final Location randomizerRoomBottomLeft;
    private final Location randomizerRoomBottomRight;
            
    private final Location hopeRoomLeft;
    private final Location hopeRoomRight;
            
    private final Location tileRoom;
            
    private final Location compassRoomTopLeft;
    private final Location compassRoomTopRight;
    private final Location compassRoomBottomLeft;
    private final Location compassRoomBottomRight;
    
    private final Location bobsChest;
    private final Location bigKeyChest;
    private final Location bigKeyChestRoomLeft;
    private final Location bigKeyChestRoomRight;
            
    private final Location bigChest;

    private final Location miniHelmasaurRoomLeft;
    private final Location miniHelmasaurRoomRight;
    
    private final Location preMoldormChest;
    
    private final Location moldormChest;
    
    //Ganons Tower has a Big Key and 4 Small Keys in locations
    private final String SMALL_KEY = "Ganons Tower - Small Key";
    private final String BIG_KEY = "Ganons Tower - Big Key";

    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 4;
    
    public final static String NAME = "Ganons Tower";
    
    //Used to check if Ganons Tower is Open
    private final DarkWorld darkWorld;
    private final RewardSet rewards;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 8 locations with their description
     * @param darkWorld The Dark World the Ganons Tower is in
     * @param rewards The Crystals to check if the tower is open
     */
    public GanonsTower(DarkWorld darkWorld, RewardSet rewards) {
        super(rewards.getRewards().get(Reward.AGAHNIM_2));

        bobsTorch = new Location("Ganons Tower - Bob's Torch"); 

        dmRoomTopLeft = new Location("Ganons Tower - DMs Room - Top Left"); 
        dmRoomTopRight = new Location("Ganons Tower - DMs Room - Top Right"); 
        dmRoomBottomLeft = new Location(
                "Ganons Tower - DMs Room - Bottom Left"); 
        dmRoomBottomRight = new Location(
                "Ganons Tower - DMs Room - Bottom Right");

        mapChest = new Location("Ganons Tower - Map Chest"); 

        firesnakeRoom = new Location("Ganons Tower - Firesnake Room"); 

        randomizerRoomTopLeft = new Location(
                "Ganons Tower - Randomizer Room - Top Left"); 
        randomizerRoomTopRight = new Location(
                "Ganons Tower - Randomizer Room - Top Right"); 
        randomizerRoomBottomLeft = new Location(
                "Ganons Tower - Randomizer Room - Bottom Left"); 
        randomizerRoomBottomRight = new Location(
                "Ganons Tower - Randomizer Room - Bottom Right"); 

        hopeRoomLeft = new Location("Ganons Tower - Hope Room - Left"); 
        hopeRoomRight = new Location("Ganons Tower - Hope Room - Right"); 

        tileRoom = new Location("Ganons Tower - Tile Room"); 

        compassRoomTopLeft = new Location(
                "Ganons Tower - Compass Room - Top Left"); 
        compassRoomTopRight = new Location(
                "Ganons Tower - Compass Room - Top Right"); 
        compassRoomBottomLeft = new Location(
                "Ganons Tower - Compass Room - Bottom Left"); 
        compassRoomBottomRight = new Location(
                "Ganons Tower - Compass Room - Bottom Right"); 

        bobsChest = new Location("Ganons Tower - Bob's Chest"); 
        bigKeyChest = new Location("Ganons Tower - Big Key Chest"); 
        bigKeyChestRoomLeft = new Location(
                "Ganons Tower - Big Key Room - Left"); 
        bigKeyChestRoomRight = new Location(
                "Ganons Tower - Big Key Room - Right"); 

        bigChest = new Location("Ganons Tower - Chest"); 

        miniHelmasaurRoomLeft = new Location(
                "Ganons Tower - Mini Helmasaur Room - Left"); 
        miniHelmasaurRoomRight = new Location(
                "Ganons Tower - Mini Helmasaur Room - Right"); 

        preMoldormChest = new Location("Ganons Tower - Pre-Moldorm Chest"); 

        moldormChest = new Location("Ganons Tower - Moldorm Chest"); 
        
        this.darkWorld = darkWorld;
        this.rewards = rewards;
    }
    
    /**
     * Check to see if there is a way to enter the dungeon
     * Ganons Tower can be entered if:
     * 1) East Dark Death Mountain is accessible 
     * 2) The 7 Crystals are acquired
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        if (!darkWorld.eastDarkDeathMountainAccess(inventory))
            return true;
        
        for (int i = 0; i < Reward.CRYSTALS.length; i++)
            if (!rewards.getRewards().get(Reward.CRYSTALS[i]).isAcquired())
                return true;
        
        return false;
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Ganons Tower requires:
     * 1) The Boots
     * 2) The Hookshot
     * 3) The Cane of Somaria
     * 4) The Bow
     * 5) A Sword or the Bug Net
     * Note -- The following items are required in every seed to enter
     * Lantern, Titan's Mitts, Hammer
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (!inventory.getItem(KeyItem.BOOTS).isOwned())
            return false;
              
        if (!inventory.getItem(KeyItem.HOOKSHOT).isOwned())
            return false;
        
        if (!inventory.getItem(KeyItem.SOMARIA).isOwned())
            return false;
        
        if (!inventory.getItem(KeyItem.BOW).isOwned())
            return false;
        
        return inventory.getItem(Sword.SWORD).isOwned() || 
                inventory.getItem(KeyItem.BUG_NET).isOwned();
    }
    
    /**
     * Check to see if the big key has been picked up
     * This checks all the locations possible where the big key can be
     * @return True or False if the big key is acquired
     */
    private boolean bigKeyAcquired() {
        
        Location[] possibleBigKey = {bobsTorch, dmRoomTopLeft, dmRoomTopRight,
            dmRoomBottomLeft, dmRoomBottomRight, mapChest, firesnakeRoom, 
            randomizerRoomTopLeft, randomizerRoomTopRight, 
            randomizerRoomBottomLeft, randomizerRoomBottomRight, hopeRoomLeft,
            hopeRoomRight, tileRoom, compassRoomTopLeft, compassRoomTopRight,
            compassRoomBottomLeft, compassRoomBottomRight, bobsChest, 
            bigKeyChest, bigKeyChestRoomLeft, bigKeyChestRoomRight};
        
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
        
        Location[] possibleSmallKeys = {bobsTorch, dmRoomTopLeft, 
            dmRoomTopRight, dmRoomBottomLeft, dmRoomBottomRight, mapChest, 
            firesnakeRoom, randomizerRoomTopLeft, randomizerRoomTopRight, 
            randomizerRoomBottomLeft, randomizerRoomBottomRight, hopeRoomLeft,
            hopeRoomRight, tileRoom, compassRoomTopLeft, compassRoomTopRight,
            compassRoomBottomLeft, compassRoomBottomRight, bobsChest, 
            bigKeyChest, bigKeyChestRoomLeft, bigKeyChestRoomRight, bigChest,
            miniHelmasaurRoomLeft, miniHelmasaurRoomRight, preMoldormChest};
        
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
     * small key to access.  In Ganons Tower a small key could be 
     * locked in the map chest
     * @return The number of small keys that could be locked
     */
    private int smallKeysLocked() {
        int numObtainedKeys = 0;
        
        Location[] possibleSmallKeys = {bobsTorch, dmRoomTopLeft, 
            dmRoomTopRight, dmRoomBottomLeft, dmRoomBottomRight, firesnakeRoom, 
            randomizerRoomTopLeft, randomizerRoomTopRight, 
            randomizerRoomBottomLeft, randomizerRoomBottomRight, hopeRoomLeft,
            hopeRoomRight, tileRoom, compassRoomTopLeft, compassRoomTopRight,
            compassRoomBottomLeft, compassRoomBottomRight, bobsChest, 
            bigKeyChest, bigKeyChestRoomLeft, bigKeyChestRoomRight, bigChest,
            miniHelmasaurRoomLeft, miniHelmasaurRoomRight, preMoldormChest};
        
        //If there's a location outside of the map chest 
        //that hasn't been checked exit the check
        for (Location location : possibleSmallKeys) {
            if (!location.isAcquired())
                return 0;
            else
                if (location.getContents().getDescription().equals(SMALL_KEY))
                    numObtainedKeys++;
        }
        
        //Since this is reached, the map chest has to have the small key 
        //First check to see if it's opened, if not then the key could be
        //locked with poor key usage
        if (mapChest.isAcquired() && mapChest.getContents()
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
        
        //These items are reachable with no items
        if (logicBobsTorch(inventory))
            inLogic.add(bobsTorch);
        if (logicHopeRoomLeft(inventory))
            inLogic.add(hopeRoomLeft);
        if (logicHopeRoomRight(inventory))
            inLogic.add(hopeRoomRight);
        
        //The left side of the basement is locked by the hookshot.  The
        //right side of the basement is locked by the cane of Somaria.  Each
        //item (obtained or not obtained) causes the key logic to change
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned())
            if (inventory.getItem(KeyItem.SOMARIA).isOwned())
                inLogic.addAll(keyLogicHookshotAndSomaria(inventory));
            else
                inLogic.addAll(keyLogicHookshot(inventory));
        else
            if (inventory.getItem(KeyItem.SOMARIA).isOwned())
                inLogic.addAll(keyLogicSomaria(inventory));
            else
                inLogic.addAll(keyLogicUpstairsOnly(inventory));
        
        //These checks are in place in case an area is reached and an item is
        //found in one of the chests that could change the logic, then the rest
        //of the chests in that area need to be added to list of ones in logic
        //
        //Example: If the randomizer room is reached with two keys because only
        //the hookshot is obtained, and then the cane of somaria is in a chest,
        //then the rest of the room would be "Out of Logic" by the hookshot and
        //cane of somaria key logic.  Since the room is open, the other chests
        //are still in logic
        
        //Used by the checks to see if a location is obtained
        boolean locationAcquired = false;
        
        //Check the Randomzer Room
        Location[] randoRoom = {randomizerRoomTopLeft, randomizerRoomTopRight,
            randomizerRoomBottomLeft, randomizerRoomBottomRight};
        for (int i = 0; i < randoRoom.length; i++) {
            //Loop through the locations and see if any have been obtained
            if (randoRoom[i].isAcquired()) {
                //A location has been acquired, break the loop and check
                //if all the other locations are in the list
                locationAcquired = true;
                break;
            }
        }
        //If a location has been acquired, check if all the other locations 
        //are the in list of available locations
        if (locationAcquired) {
            ArrayList<Location> rando = logicRandomizerRoom(inventory);
            for (int i = 0; i < rando.size(); i++) {
                //If the location isn't in the list, add it
                if (!inLogic.contains(rando.get(i)))
                    inLogic.add(rando.get(i));
            }
        }
        
        //Reset the variable
        locationAcquired = false;
        
        //Check the Compass Room
        Location[] compRoom = {compassRoomTopLeft, compassRoomTopRight,
            compassRoomBottomLeft, compassRoomBottomRight};
        for (int i = 0; i < compRoom.length; i++) {
            //Loop through the locations and see if any have been obtained
            if (compRoom[i].isAcquired()) {
                //A location has been acquired, break the loop and check
                //if all the other locations are in the list
                locationAcquired = true;
                break;
            }
        }
        //If a location has been acquired, check if all the other locations 
        //are the in list of available locations
        if (locationAcquired) {
            ArrayList<Location> comp = logicCompassRoom(inventory);
            for (int i = 0; i < comp.size(); i++) {
                //If the location isn't in the list, add it
                if (!inLogic.contains(comp.get(i)))
                    inLogic.add(comp.get(i));
            }
        }
        
        //Reset the variable
        locationAcquired = false;   
        
        //Check the end area of the basement
        Location[] basementEnd = {bobsChest, bigKeyChest, bigKeyChestRoomLeft,
            bigKeyChestRoomRight, bigChest};
        for (int i = 0; i < basementEnd.length; i++) {
            //Loop through the locations and see if any have been obtained
            if (basementEnd[i].isAcquired()) {
                //A location has been acquired, break the loop and check
                //if all the other locations are in the list
                locationAcquired = true;
                break;
            }
        }
        //If a location has been acquired, check if all the other locations 
        //are the in list of available locations
        if (locationAcquired) {
            ArrayList<Location> basement = new ArrayList();
            if (logicBobsChest(inventory))
                basement.add(bobsChest);
            basement.addAll(logicBigKeyChestRoom(inventory));
            if (logicBigChest(inventory))
                basement.add(bigChest);
            for (int i = 0; i < basement.size(); i++) {
                //If the location isn't in the list, add it
                if (!inLogic.contains(basement.get(i)))
                    inLogic.add(basement.get(i));
            }
        }        
        
        return inLogic;
    }
    
    /**
     * Check to see what's in logic if the hookshot 
     * and the cane of Somaria are acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired (small keys and the big key)
     * Note -- 4 keys are found on enemies or under pots, affecting logic
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogicHookshotAndSomaria
        (Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //These only require the hookshot or the cane of somaria
        inLogic.addAll(logicDMRoom(inventory));
        if (logicTileRoom(inventory))
            inLogic.add(tileRoom);
        
        //Used if upstairs is accessible
        int upstairsDoor = 0;
        
        //Upstairs requires the big key and the bow to advance
        if (bigKeyAcquired() && inventory.getItem(KeyItem.BOW).isOwned()) {
            //There's one door upstairs that a key could be used on before
            //the basement doors, so set that for the basement logic check
            //Note -- There's two doors upstairs, but there is one key 
            //upstairs as well so only one small key has to be used
            upstairsDoor = 1;
            
            //These chests don't require any keys except the big key to reach
            if (logicMiniHelmasaurRoomLeft(inventory))
                inLogic.add(miniHelmasaurRoomLeft);
            if (logicMiniHelmasaurRoomRight(inventory))
                inLogic.add(miniHelmasaurRoomRight);
            
            //There is one locked door past this chest, so all but one key 
            //are needed to reach this chest
            if (smallKeysAcquired() >= TOTAL_SMALL_KEYS - 1)
                if (logicPreMoldormChest(inventory))
                    inLogic.add(preMoldormChest);
            //There are no locked doors past this chest, so all keys are 
            //needed to reach this chest
            //Note -- Hookshot is acquired, so the gap can be crossed
            //Note -- Add small keys locked just to see if everything but the
            //map chest was opened (and a key wasn't used on the door to it)
            if (smallKeysAcquired() + smallKeysLocked() == TOTAL_SMALL_KEYS)
                if (logicMoldormChest(inventory))
                    inLogic.add(moldormChest);
        }
        
        //Firesnake room has a locked door at the end of the room.  Therefore
        //the chest requires all but one key to be reached.  2 doors can
        //be unlocked if the upstairs isn't accessible, if it is 3 doors
        //can be unlocked before the room is reached
        if (smallKeysAcquired() >= 2 + upstairsDoor)
            if (logicFiresnakeRoom(inventory))
                inLogic.add(firesnakeRoom);
        
        //Check to see if the Fire Rod is acquired, this prevents part of the 
        //Right side of the basement from being accessible
        if (inventory.getItem(KeyItem.FIRE_ROD).isOwned()) {
            //If two keys or three keys are used (depending on upstairs access), 
            //either the door past the firesnake room or the door past the 
            //compass room warp is opened and these chests are then in logic
            if (smallKeysAcquired() >= 2 + upstairsDoor) {
                if (logicBobsChest(inventory))
                    inLogic.add(bobsChest);
                inLogic.addAll(logicBigKeyChestRoom(inventory));
                if (logicBigChest(inventory))
                    inLogic.add(bigChest);
            }
            //If three or four keys are used (depending on upstairs access),
            //all the remaining locations are accessible
            //Note -- Add small keys locked just to see if everything but the 
            //map chest was opened (and a key wasn't used on the door to it)
            if (smallKeysAcquired() >= 3 + upstairsDoor + smallKeysLocked()) {
                if (logicMapChest(inventory))
                    inLogic.add(mapChest);
                inLogic.addAll(logicRandomizerRoom(inventory));
                inLogic.addAll(logicCompassRoom(inventory));
            }
        }
        //No fire rod, so the compass room and the right path to the Ice Armos
        //aren't accessible.  In addition, a small key can be "wasted" on the 
        //locked door in the tile room, since it doesn't lead to locations
        //or another key.  Therefore the rest of the locations require all the 
        //small keys (3 or 4 depending on upstairs access) to be accessible.
        else {
            if (smallKeysAcquired() >= 3 + upstairsDoor) {
                if (logicBobsChest(inventory))
                    inLogic.add(bobsChest);
                inLogic.addAll(logicBigKeyChestRoom(inventory));
                if (logicBigChest(inventory))
                    inLogic.add(bigChest);
                
                if (logicMapChest(inventory))
                    inLogic.add(mapChest);
                inLogic.addAll(logicRandomizerRoom(inventory));  
            }
        }
        return inLogic;
    }
        
    /**
     * Check to see what's in logic if the hookshot is acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired (small keys and the big key)
     * Note -- 4 keys are found on enemies or under pots, affecting logic
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogicHookshot(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //These only require the hookshot
        inLogic.addAll(logicDMRoom(inventory));
        
        //Used if upstairs is accessible
        int upstairsDoor = 0;
        
        //Upstairs requires the big key and the bow to advance
        if (bigKeyAcquired() && inventory.getItem(KeyItem.BOW).isOwned()) {
            //There's one door upstairs that a key could be used on before
            //the basement doors, so set that for the basement logic check
            //Note -- There's two doors upstairs, but there is one key 
            //upstairs as well so only one small key has to be used
            upstairsDoor = 1;
            
            //These chests don't require any keys except the big key to reach
            if (logicMiniHelmasaurRoomLeft(inventory))
                inLogic.add(miniHelmasaurRoomLeft);
            if (logicMiniHelmasaurRoomRight(inventory))
                inLogic.add(miniHelmasaurRoomRight);
            
            //There is one locked door past this chest and there is one locked
            //door that requires the cane of Somaria to reach, so two keys
            //are required to reach the chest
            if (smallKeysAcquired() >= 2)
                if (logicPreMoldormChest(inventory))
                    inLogic.add(preMoldormChest);
            //There are no locked doors past this chest but there is one locked
            //door that requires the cane of Somaria to reach, so three keys
            //are required to reach the chest
            //Note -- Hookshot is acquired, so the gap can be crossed
            if (smallKeysAcquired() == 3)
                if (logicMoldormChest(inventory))
                    inLogic.add(moldormChest);
        }
        
        //Firesnake room has a locked door at the end of the room.  Therefore
        //the chest requires all but one key to be reached.  1 door can
        //be unlocked if the upstairs isn't accessible, if it is 2 doors
        //can be unlocked before the room is reached
        //Note -- One locked door requires the cane of Somaria
        if (smallKeysAcquired() >= 1 + upstairsDoor)
            if (logicFiresnakeRoom(inventory))
                inLogic.add(firesnakeRoom);
        
        //If two or three keys are used (depending on upstairs access),
        //all the remaining locations are accessible
        if (smallKeysAcquired() >= 2 + upstairsDoor) {
            if (logicMapChest(inventory))
                inLogic.add(mapChest);
            
            inLogic.addAll(logicRandomizerRoom(inventory)); 
            
            if (logicBobsChest(inventory))
                inLogic.add(bobsChest);
            inLogic.addAll(logicBigKeyChestRoom(inventory));
            if (logicBigChest(inventory))
                inLogic.add(bigChest); 
        }

        return inLogic;
    }
    
    /**
     * Check to see what's in logic if the cane of Somaria is acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired (small keys and the big key)
     * Note -- 4 keys are found on enemies or under pots, affecting logic
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogicSomaria(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //This only requires the cane of Somaria
        if (logicTileRoom(inventory))
            inLogic.add(tileRoom);
        
        //Used if upstairs is accessible
        int upstairsDoor = 0;
        
        //Upstairs requires the big key and the bow to advance
        if (bigKeyAcquired() && inventory.getItem(KeyItem.BOW).isOwned()) {
            //There's one door upstairs that a key could be used on before
            //the basement doors, so set that for the basement logic check
            //Note -- There's two doors upstairs, but there is one key 
            //upstairs as well so only one small key has to be used
            upstairsDoor = 1;
            
            //These chests don't require any keys except the big key to reach
            if (logicMiniHelmasaurRoomLeft(inventory))
                inLogic.add(miniHelmasaurRoomLeft);
            if (logicMiniHelmasaurRoomRight(inventory))
                inLogic.add(miniHelmasaurRoomRight);
            
            //There is one locked door past this chest and there are two locked
            //doors that requires the hookshot to reach, so one key is required
            // to reach the chest
            if (smallKeysAcquired() >= 1)
                if (logicPreMoldormChest(inventory))
                    inLogic.add(preMoldormChest);
            //Since the hookshot isn't acquired, the gap past Moldorm can't be
            //crossed so there's no need to check if the chest is in logic
        }
        
        //Check to see if the Fire Rod is acquired, this opens up locations in 
        //the right side of the basement
        if (inventory.getItem(KeyItem.FIRE_ROD).isOwned()) {
            //If one or two keys are used (depending on upstairs access),
            //all the remaining locations are accessible
            if (smallKeysAcquired() >= 1 + upstairsDoor) {
                inLogic.addAll(logicCompassRoom(inventory));
                
                if (logicBobsChest(inventory))
                    inLogic.add(bobsChest);
                inLogic.addAll(logicBigKeyChestRoom(inventory));
                if (logicBigChest(inventory))
                    inLogic.add(bigChest);
            }
        }

        return inLogic;
    }
    
    /**
     * Check to see what's in logic if the hookshot and 
     * the cane of Somaria are not acquired acquired
     * The item locations that are in logic depend on how many 
     * keys have been acquired (small keys and the big key)
     * Note -- 4 keys are found on enemies or under pots, affecting logic
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> keyLogicUpstairsOnly(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();

        //The big key and the bow locks upstairs
        //Upstairs requires the big key and the bow to advance
        if (bigKeyAcquired() && inventory.getItem(KeyItem.BOW).isOwned()) {
            
            //These chests don't require any keys except the big key to reach
            if (logicMiniHelmasaurRoomLeft(inventory))
                inLogic.add(miniHelmasaurRoomLeft);
            if (logicMiniHelmasaurRoomRight(inventory))
                inLogic.add(miniHelmasaurRoomRight);
            
            //There is one locked door past this chest, two locked doors that
            //require the hookshot to reach, and one locked door that requires 
            //the cane of Somaria to reach. No keys from locations are needed
            //Note -- 2 locked doors can be reached at this point.  The one
            //connecting the left and right basement (Under the lobby) and the
            //door to get to this room.  There's a key that's always accessible 
            //in the room to the left of Bob's Torch in the basement and a key 
            //on the mini helmasaur upstairs, so these doors are taken care of
            if (logicPreMoldormChest(inventory))
                inLogic.add(preMoldormChest);
            //Since the hookshot isn't acquired, the gap past Moldorm can't be
            //crossed so there's no need to check if the chest is in logic
        }

        return inLogic;
    }
    
    //Logic checks for the various locations
    //Key Logic isn't checked for any of these since it's checked above
    
    /**
     * Check to see if Bob's Torch is in logic
     * It's in logic if it's not opened and the boots are acquired
     * @param inventory The current inventory
     * @return True or False if the torch is in logic
     */
    private boolean logicBobsTorch(Inventory inventory) {
        if (bobsTorch.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.BOOTS).isOwned();
    }
    
    /**
     * Get the locations that are currently in logic in the DM Room
     * They're in logic if they're not open and the hookshot is acquired
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicDMRoom(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned()) {
            if (!dmRoomTopLeft.isAcquired())
                inLogic.add(dmRoomTopLeft);
            if (!dmRoomTopRight.isAcquired())
                inLogic.add(dmRoomTopRight);
            if (!dmRoomBottomLeft.isAcquired())
                inLogic.add(dmRoomBottomLeft);
            if (!dmRoomBottomRight.isAcquired())
                inLogic.add(dmRoomBottomRight);
        }            

        return inLogic;
    }  
    
    /**
     * Check to see if the map chest is in logic
     * It's in logic if it's not opened and the hookshot is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicMapChest(Inventory inventory) {
        if (mapChest.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }
    
    /**
     * Check to see if the firesnake room is in logic
     * It's in logic if it's not opened and the hookshot is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicFiresnakeRoom(Inventory inventory) {
        if (firesnakeRoom.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }
    
    /**
     * Get the locations that are currently in logic in the randomzier Room
     * They're in logic if they're not open and the hookshot is acquired
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicRandomizerRoom(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned()) {
            if (!randomizerRoomTopLeft.isAcquired())
                inLogic.add(randomizerRoomTopLeft);
            if (!randomizerRoomTopRight.isAcquired())
                inLogic.add(randomizerRoomTopRight);
            if (!randomizerRoomBottomLeft.isAcquired())
                inLogic.add(randomizerRoomBottomLeft);
            if (!randomizerRoomBottomRight.isAcquired())
                inLogic.add(randomizerRoomBottomRight);
        }            

        return inLogic;
    }  
    
    /**
     * Check to see if the hope room left chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicHopeRoomLeft(Inventory inventory) {
        return !hopeRoomLeft.isAcquired();
    }
    
    /**
     * Check to see if the hope room right chest is in logic
     * It's in logic if it's not opened 
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicHopeRoomRight(Inventory inventory) {
        return !hopeRoomRight.isAcquired();
    }
    
    /**
     * Check to see if the tile room is in logic
     * It's in logic if it's not opened and the cane of Somaria is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicTileRoom(Inventory inventory) {
        if (tileRoom.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.SOMARIA).isOwned();
    }
    
    /**
     * Get the locations that are currently in logic in the Compass Room
     * They're in logic if they're not open, the hookshot and the cane of 
     * Somaria are acquired
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicCompassRoom(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (inventory.getItem(KeyItem.SOMARIA).isOwned() && 
                inventory.getItem(KeyItem.FIRE_ROD).isOwned()) {
            if (!compassRoomTopLeft.isAcquired())
                inLogic.add(compassRoomTopLeft);
            if (!compassRoomTopRight.isAcquired())
                inLogic.add(compassRoomTopRight);
            if (!compassRoomBottomLeft.isAcquired())
                inLogic.add(compassRoomBottomLeft);
            if (!compassRoomBottomRight.isAcquired())
                inLogic.add(compassRoomBottomRight);
        }            

        return inLogic;
    }  
    
    /**
     * Check to see if Bob's chest is in logic
     * It's in logic if it's not opened and one of the following are acquired
     * 1) The hookshot 
     * 2) The cane of Somaria and the Fire Rod
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicBobsChest(Inventory inventory) {
        if (bobsChest.isAcquired())
            return false;
        
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned())
            return true;
        
        return inventory.getItem(KeyItem.SOMARIA).isOwned() &&
                inventory.getItem(KeyItem.FIRE_ROD).isOwned();
    }
    
    /**
     * Get the locations that are currently in logic in the big key chest room
     * They're in logic if they are not opened and 
     * one of the following are acquired
     * 1) The hookshot 
     * 2) The cane of Somaria and the Fire Rod
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicBigKeyChestRoom(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned() || 
                (inventory.getItem(KeyItem.SOMARIA).isOwned() && 
                inventory.getItem(KeyItem.FIRE_ROD).isOwned())) {
            if (!bigKeyChest.isAcquired())
                inLogic.add(bigKeyChest);
            if (!bigKeyChestRoomLeft.isAcquired())
                inLogic.add(bigKeyChestRoomLeft);
            if (!bigKeyChestRoomRight.isAcquired())
                inLogic.add(bigKeyChestRoomRight);
        }            

        return inLogic;
    } 
    
    /**
     * Check to see if the big chest is in logic
     * It's in logic if it's not opened, the big key is acquired
     * and one of the following are acquired
     * 1) The hookshot 
     * 2) The cane of Somaria and the Fire Rod
     * @param inventory The current inventory 
     * @return True or False if the big key chest is in logic
     */
    private boolean logicBigChest(Inventory inventory) {
        if (bigChest.isAcquired())
            return false;
        
        if (!bigKeyAcquired())
            return false;
        
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned())
            return true;
        
        return inventory.getItem(KeyItem.SOMARIA).isOwned() &&
                inventory.getItem(KeyItem.FIRE_ROD).isOwned();
    }
 
    /**
     * Check to see if the mini Helmasaur Room Left chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicMiniHelmasaurRoomLeft(Inventory inventory) {
        return !miniHelmasaurRoomLeft.isAcquired();
    }
    
    /**
     * Check to see if the mini Helmasaur Room Right chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicMiniHelmasaurRoomRight(Inventory inventory) {
        return !miniHelmasaurRoomRight.isAcquired();
    }
    
    /**
     * Check to see if the pre Moldorm chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the big key chest is in logic
     */
    private boolean logicPreMoldormChest(Inventory inventory) {
        return !preMoldormChest.isAcquired();
    }
    
    /**
     * Check to see if the Moldorm chest is in logic
     * It's in logic if it's not opened and the hookshot is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicMoldormChest(Inventory inventory) {
        if (moldormChest.isAcquired())
            return false;
        
        return inventory.getItem(KeyItem.HOOKSHOT).isOwned();
    }
    
    //Getters and Setters for the locations below
    
    /**
     * Get a list of all the locations
     * @return All the locations in Ganon's Tower
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(bobsTorch);
        
        locations.add(dmRoomTopLeft);
        locations.add(dmRoomTopRight);
        locations.add(dmRoomBottomLeft);
        locations.add(dmRoomBottomRight);
        
        locations.add(mapChest);
        
        locations.add(firesnakeRoom);
        
        locations.add(randomizerRoomTopLeft);
        locations.add(randomizerRoomTopRight);
        locations.add(randomizerRoomBottomLeft);
        locations.add(randomizerRoomBottomRight);
        
        locations.add(hopeRoomLeft);
        locations.add(hopeRoomRight);
        
        locations.add(tileRoom);
        
        locations.add(compassRoomTopLeft);
        locations.add(compassRoomTopRight);
        locations.add(compassRoomBottomLeft);
        locations.add(compassRoomBottomRight);
        
        locations.add(bobsChest);
        locations.add(bigKeyChest);
        locations.add(bigKeyChestRoomLeft);
        locations.add(bigKeyChestRoomRight);
        
        locations.add(bigChest);
        
        locations.add(miniHelmasaurRoomLeft);
        locations.add(miniHelmasaurRoomRight);
        
        locations.add(preMoldormChest);
        
        locations.add(moldormChest);  
        
        return locations; 
    }
   
    /**
     * @return the bobsTorch
     */
    public Location getBobsTorch() {
        return bobsTorch;
    }
    
    /**
     * @param contents The new contents of the torch
     */
    public void setBobsTorch(Item contents) {
        bobsTorch.setContents(contents);
    }
    
    /**
     * @return the dmRoomTopLeft
     */
    public Location getDMRoomTopLeft() {
        return dmRoomTopLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setDMRoomTopLeft(Item contents) {
        dmRoomTopLeft.setContents(contents);
    }
    
    /**
     * @return the dmRoomTopRight
     */
    public Location getDMRoomTopRight() {
        return dmRoomTopRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setDMRoomTopRight(Item contents) {
        dmRoomTopRight.setContents(contents);
    }

    /**
     * @return the dmRoomBottomLeft
     */
    public Location getDMRoomBottomLeft() {
        return dmRoomBottomLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setDMRoomBottomLeft(Item contents) {
        dmRoomBottomLeft.setContents(contents);
    }
    
    /**
     * @return the dmRoomBottomRight
     */
    public Location getDMRoomBottomRight() {
        return dmRoomBottomRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setDMRoomBottomRight(Item contents) {
        dmRoomBottomRight.setContents(contents);
    }
    
    /**
     * @return the randomizerRoomTopLeft
     */
    public Location getRandomizerRoomLeft() {
        return randomizerRoomTopLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setRandomizerRoomTopLeft(Item contents) {
        randomizerRoomTopLeft.setContents(contents);
    }
    
    /**
     * @return the randomizerRoomTopRight
     */
    public Location getRandomizerRoomTopRight() {
        return randomizerRoomTopRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setRandomizerRoomTopRight(Item contents) {
        randomizerRoomTopRight.setContents(contents);
    }

    /**
     * @return the randomizerRoomBottomLeft
     */
    public Location getRandomizerRoomBottomLeft() {
        return randomizerRoomBottomLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setRandomizerRoomBottomLeft(Item contents) {
        randomizerRoomBottomLeft.setContents(contents);
    }
    
    /**
     * @return the randomizerRoomBottomRight
     */
    public Location getRandomizerRoomBottomRight() {
        return randomizerRoomBottomRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setRandomizerRoomBottomRight(Item contents) {
        randomizerRoomBottomRight.setContents(contents);
    }
    
    /**
     * @return the firesnakeRoom
     */
    public Location getFiresnakeRoom() {
        return firesnakeRoom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setFiresnakeRoom(Item contents) {
        firesnakeRoom.setContents(contents);
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
     * @return the hopeRoomLeft
     */
    public Location getHopeRoomLeft() {
        return hopeRoomLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHopeRoomLeft(Item contents) {
        hopeRoomLeft.setContents(contents);
    }
    
    /**
     * @return the hopeRoomRight
     */
    public Location getHopeRoomRight() {
        return hopeRoomRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHopeRoomRight(Item contents) {
        hopeRoomRight.setContents(contents);
    }
    
    /**
     * @return the bobsChest
     */
    public Location getBobsChest() {
        return bobsChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBobsChest(Item contents) {
        bobsChest.setContents(contents);
    }
    
    /**
     * @return the tileRoom
     */
    public Location getTileRoom() {
        return tileRoom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setTileRoom(Item contents) {
        tileRoom.setContents(contents);
    }
    
    /**
     * @return the compassRoomTopLeft
     */
    public Location getCompassRoomTopLeft() {
        return compassRoomTopLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCompassRoomTopLeft(Item contents) {
        compassRoomTopLeft.setContents(contents);
    }
    
    /**
     * @return the compassRoomTopRight
     */
    public Location getCompassRoomTopRight() {
        return compassRoomTopRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCompassRoomTopRight(Item contents) {
        compassRoomTopRight.setContents(contents);
    }

    /**
     * @return the compassRoomBottomLeft
     */
    public Location getCompassRoomBottomLeft() {
        return compassRoomBottomLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCompassRoomBottomLeft(Item contents) {
        compassRoomBottomLeft.setContents(contents);
    }
    
    /**
     * @return the compassRoomBottomRight
     */
    public Location getCompassRoomBottomRight() {
        return compassRoomBottomRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCompassRoomBottomRight(Item contents) {
        compassRoomBottomRight.setContents(contents);
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
     * @return the bigKeyChestRoomLeft
     */
    public Location getBigKeyChestRoomLeft() {
        return bigKeyChestRoomLeft;
    }
    
    /**
     * @param contents The new contents of the big key chest room left
     */
    public void setBigKeyChestRoomLeft(Item contents) {
        bigKeyChestRoomLeft.setContents(contents);
    }
    
    /**
     * @return the bigKeyChestRoomRight
     */
    public Location getBigKeyChestRoomRight() {
        return bigKeyChestRoomRight;
    }
    
    /**
     * @param contents The new contents of the big key chest room right
     */
    public void setBigKeyChestRoomRight(Item contents) {
        bigKeyChestRoomRight.setContents(contents);
    }
    
    /**
     * @return the miniHelmasaurRoomLeft
     */
    public Location getMiniHelmasaurRoomLeft() {
        return miniHelmasaurRoomLeft;
    }
    
    /**
     * @param contents The new contents of the miniHelmasaurRoomLeft
     */
    public void setMiniHelmasaurRoomLeft(Item contents) {
        miniHelmasaurRoomLeft.setContents(contents);
    }
    
    /**
     * @return the miniHelmasaurRoomRight
     */
    public Location getMiniHelmasaurRoomRight() {
        return miniHelmasaurRoomRight;
    }
    
    /**
     * @param contents The new contents of the miniHelmasaurRoomRight
     */
    public void setMiniHelmasaurRoomRight(Item contents) {
        miniHelmasaurRoomRight.setContents(contents);
    }
    
    /**
     * @return the preMoldormChest
     */
    public Location getPreMoldormChest() {
        return preMoldormChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setPreMoldormChest(Item contents) {
        preMoldormChest.setContents(contents);
    }
    
    /**
     * @return the MoldormChest
     */
    public Location getMoldormChest() {
        return moldormChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setMoldormChest(Item contents) {
        moldormChest.setContents(contents);
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