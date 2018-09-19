/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

import items.Gloves;
import items.Inventory;
import items.Item;
import items.Location;
import items.Sword;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class DeathMountain extends Area {
    
    //Death Mountain has 14 possible item locations
    private final Location oldMan;
    private final Location spectacleRockCave;
    private final Location spectacleRock; 
    private final Location etherTablet; 

    private final Location spiralCave;

    private final Location paradoxCaveLowerFarLeft;
    private final Location paradoxCaveLowerLeft;
    private final Location paradoxCaveLowerRight;
    private final Location paradoxCaveLowerFarRight;
    private final Location paradoxCaveLowerMiddle;
    
    private final Location paradoxCaveUpperLeft;
    private final Location paradoxCaveUpperRight;
    
    private final Location floatingIsland;
    
    private final Location mimicCave;

   
    //Name of the area
    public final static String NAME = "Death Mountain";

    
    /**
     * Constructor Method
     * Calls the parent constructor
     * Instantiate the 14 Locations with their description
     */
    public DeathMountain() {
        super();
        
        oldMan = new Location("Old Man");
        spectacleRockCave = new Location("Spectacle Rock Cave");
        spectacleRock = new Location("Spectacle Rock"); 
        etherTablet = new Location("Ether Tablet");

        spiralCave = new Location("Spiral Cave");
        
        paradoxCaveLowerFarLeft = new Location(
                "Paradox Cave Lower - Far Left");
        paradoxCaveLowerLeft = new Location(
                "Paradox Cave Lower - Left");
        paradoxCaveLowerRight = new Location(
                "Paradox Cave Lower - Right");
        paradoxCaveLowerFarRight = new Location(
                "Paradox Cave Lower - Far Right");
        paradoxCaveLowerMiddle = new Location(
                "Paradox Cave Lower - Middle");
        
        paradoxCaveUpperLeft = new Location(
                "Paradox Cave Upper - Left");
        paradoxCaveUpperRight = new Location(
                "Paradox Cave Upper - Right");
    
        floatingIsland = new Location("Floating Island");
        
        mimicCave = new Location("Mimic Cave");

    }
    
    /**
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        //Check to see if there is a way to enter the mountain
        if(closed(inventory)){
            return inLogic;
        }

        //Check the logic of each of the locations and add them to the list
        if (logicOldMan(inventory))
            inLogic.add(oldMan);
        if (logicSpectacleRockCave(inventory))
            inLogic.add(spectacleRockCave);
        if (logicSpectacleRock(inventory))
            inLogic.add(spectacleRock);
        if (logicEtherTablet(inventory))
            inLogic.add(etherTablet);
        
        if (eastDeathMountainAccess(inventory)) 
            inLogic.addAll(logicEastDeathMountain(inventory));
        
        /*
        if (logicFloatingIsland(inventory, darkWorld))
            inLogic.add(floatingIsland);
        if (logicMimicCave(inventory, darkWorld, turtleRock))
            inLogic.add(mimicCave);
        */
        
        return inLogic;
    }
    
    /**
     * Check to see if there is a way to enter the Mountain
     * Public Method so that it can be used by TowerOfHera and DarkWorld 
     * Death Mountain is open if either:
     * 1) The lantern and at least the Power Gloves are obtained
     * 2) The flute is obtained
     * @param inventory The current inventory 
     * @return True or False if it's accessible
     */
    public boolean closed(Inventory inventory){
        if(inventory.getItem(Item.LANTERN).isOwned() && 
                inventory.getItem(Gloves.GLOVES).isOwned())
            return true;
        
        return inventory.getItem(Item.FLUTE).isOwned();
    }
    
    /**
     * Check to see if East Death Mountain is reachable
     * Public Method so that it can be used by DarkWorld and TurtleRock
     * East Death Mountain is open if either:
     * 1) The Hookshot is acquired
     * 2) The Mirror and the Hammer are acquired
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean eastDeathMountainAccess(Inventory inventory)
    {
        if(inventory.getItem(Item.HOOKSHOT).isOwned())
            return true;
        
        return inventory.getItem(Item.MIRROR).isOwned() &&
                inventory.getItem(Item.HAMMER).isOwned();
    }
    
    /**
     * Check to see if the old man's item is in logic
     * It's in logic if it's not obtained and the lantern is acquired
     * @param inventory The current inventory
     * @return True or False if the location is in logic
     */
    private boolean logicOldMan(Inventory inventory) {
        if (oldMan.isAcquired())
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    /**
     * Check to see if the spectacle rock cave item is in logic
     * It's in logic if it's not obtained
     * @param inventory The current inventory (unused)
     * @return True or False if the location is in logic
     */
    private boolean logicSpectacleRockCave(Inventory inventory) {
        return !spectacleRockCave.isAcquired();
    }
    
    /**
     * Check to see if the spectacle rock item is in logic
     * It's in logic if it's not obtained and the mirror is acquired
     * @param inventory The current inventory
     * @return True or False if the location is in logic
     */
    private boolean logicSpectacleRock(Inventory inventory) {
        if (spectacleRock.isAcquired())
            return false;
        
        return inventory.getItem(Item.MIRROR).isOwned();
    }
    
    /**
     * Check to see if the ether tablet item is in logic
     * It's in logic if it's not obtained and
     * 1) The book is acquired
     * 2) At least the master sword is acquired
     * 3) One of these is obtained:
     *    - Hookshot and Hammer
     *    - Mirror
     * @param inventory The current inventory (unused)
     * @return True or False if the location is in logic
     */
    private boolean logicEtherTablet(Inventory inventory) {
        if (etherTablet.isAcquired())
            return false;
        
        if (!inventory.getItem(Item.BOOK).isOwned())
            return false;
        
        if (((Sword)(inventory.getItem(Sword.SWORD))).getLevel() < 2)
            return false;
        
        if (inventory.getItem(Item.HOOKSHOT).isOwned() &&
                inventory.getItem(Item.HAMMER).isOwned())
            return true;
        
        return inventory.getItem(Item.MIRROR).isOwned();
    }
    
    /**
     * Check to see what's in logic in East Death Mountain
     * All of Paradox Cave and Spiral Cave are in logic if 
     * they can be reached and they have not been obtained
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> logicEastDeathMountain(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (!eastDeathMountainAccess(inventory))
            return inLogic;
        
        if (!spiralCave.isAcquired())
            inLogic.add(spiralCave);
        
        if (!paradoxCaveLowerFarLeft.isAcquired())
            inLogic.add(paradoxCaveLowerFarLeft);
        if (!paradoxCaveLowerLeft.isAcquired())
            inLogic.add(paradoxCaveLowerLeft);
        if (!paradoxCaveLowerRight.isAcquired())
            inLogic.add(paradoxCaveLowerRight);
        if (!paradoxCaveLowerFarRight.isAcquired())
            inLogic.add(paradoxCaveLowerFarRight);
        if (!paradoxCaveLowerMiddle.isAcquired())
            inLogic.add(paradoxCaveLowerMiddle);
        
        if (!paradoxCaveUpperLeft.isAcquired())
            inLogic.add(paradoxCaveUpperLeft);      
        if (!paradoxCaveUpperRight.isAcquired())
            inLogic.add(paradoxCaveUpperRight); 
        
        return inLogic;
    }
    
    //Adding in when DarkWorld and TurtleRock are done
    /**
     * Check to see if Floating Island is in logic
     * It's in logic if it has not been obtained, if east dark
     * death mountain can be reached, and the mirror is acquired
     * @param inventory
     * @param darkWorld
     * @return 
     
    private boolean logicFloatingIsland(Inventory inventory, 
            DarkWorld darkWorld){
        
        if (floatingIsland.isAcquired())
            return false;
        
        return darkWorld.eastDeathMountainAccess(inventory) &&
                inventory.getItem(Item.MIRROR).isOwned();
    } */
    
        /**
     * Check to see if Mimic Cave is in logic
     * It's in logic if it has not been obtained, if the area
     * inside Turtle Rock can be reached, and the mirror is acquired
     * @param inventory The current Inventory
     * @param turtleRock The Turtle Rock Dungeon (To check for access)
     * @return True or False if the location is in logic
     
    public boolean logicMimicCave(Inventory inventory,
            TurtleRock turtleRock) {
        
        if (spectacleRock.isAcquired())
            return false;
        
        return turtleRock.mimicCaveAccess(inventory) && 
                inventory.getItem(Item.MIRROR).isOwned();
    
    } */
    
    //Getters and Setters for the locations below

    /**
     * @return the oldMan
     */
    public Location getOldMan() {
        return oldMan;
    }

    /**
     * @param contents The old man's item
     */
    public void setOldMan(Item contents) {
        oldMan.setContents(contents);
    }

    /**
     * @return the spectacleRockCave
     */
    public Location getSpectacleRockCave() {
        return spectacleRockCave;
    }

    /**
     * @param contents The new contents of spectacle rock cave
     */
    public void setSpectacleRockCave(Item contents) {
        spectacleRockCave.setContents(contents);
    }
    
    /**
     * @return the spectacleRock
     */
    public Location getSpectacleRock() {
        return spectacleRock;
    }

    /**
     * @param contents The new item on spectacle rock
     */
    public void setSpectacleRock(Item contents) {
        spectacleRock.setContents(contents);
    }

    /**
     * @return the etherTablet
     */
    public Location getEtherTablet() {
        return etherTablet;
    }

    /**
     * @param contents The new contents of the tablet
     */
    public void setEtherTablet(Item contents) {
        etherTablet.setContents(contents);
    }

    /**
     * @return the spiralCave
     */
    public Location getSpiralCave() {
        return spiralCave;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setSpiralCave(Item contents) {
        spiralCave.setContents(contents);
    }

    /**
     * @return the paradoxCaveLowerFarLeft
     */
    public Location getParadoxCaveLowerFarLeft() {
        return paradoxCaveLowerFarLeft;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setParadoxCaveLowerFarLeft(Item contents) {
        paradoxCaveLowerFarLeft.setContents(contents);
    }

    /**
     * @return the paradoxCaveLowerLeft
     */
    public Location getParadoxCaveLowerLeft() {
        return paradoxCaveLowerLeft;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setParadoxCaveLowerLeft(Item contents) {
        paradoxCaveLowerLeft.setContents(contents);
    }

    /**
     * @return the paradoxCaveLowerRight
     */
    public Location getParadoxCaveLowerRight() {
        return paradoxCaveLowerRight;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setParadoxCaveLowerRight(Item contents) {
        paradoxCaveLowerRight.setContents(contents);
    }

    /**
     * @return the paradoxCaveLowerFarRight
     */
    public Location getParadoxCaveLowerFarRight() {
        return paradoxCaveLowerFarRight;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setParadoxCaveLowerFarRight(Item contents) {
        paradoxCaveLowerFarRight.setContents(contents);
    }

    /**
     * @return the paradoxCaveLowerMiddle
     */
    public Location getParadoxCaveLowerMiddle() {
        return paradoxCaveLowerMiddle;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setParadoxCaveLowerMiddle(Item contents) {
        paradoxCaveLowerMiddle.setContents(contents);
    }

    /**
     * @return the paradoxCaveUpperLeft
     */
    public Location getParadoxCaveUpperLeft() {
        return paradoxCaveUpperLeft;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setParadoxCaveUpperLeft(Item contents) {
        paradoxCaveUpperLeft.setContents(contents);
    }

    /**
     * @return the paradoxCaveUpperRight
     */
    public Location getParadoxCaveUpperRight() {
        return paradoxCaveUpperRight;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setParadoxCaveUpperRight(Item contents) {
        paradoxCaveUpperRight.setContents(contents);
    }

    /**
     * @return the floatingIsland
     */
    public Location getFloatingIsland() {
        return floatingIsland;
    }

    /**
     * @param contents The new item on the floating island
     */
    public void setFloatingIsland(Item contents) {
        floatingIsland.setContents(contents);
    }
    
    /**
     * @return the mimicCave
     */
    public Location getMimicCave() {
        return mimicCave;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setMimicCave(Item contents) {
        mimicCave.setContents(contents);
    }
}
