/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

import java.util.ArrayList;
import items.*;
/**
 *
 * @author Andrew
 */
public class HyruleCastle extends Area {
    
    //Hyrule Castle has 10 possible item locations
    private final Location linksUncle;
    private final Location secretPassage;
    
    private final Location mapChest;
    private final Location boomerangChest;  
    private final Location zeldasCell;
    
    private final Location sewersDarkCross;
    
    private final Location sewersSecretRoomLeft;
    private final Location sewersSecretRoomMiddle; 
    private final Location sewersSecretRoomRight; 
    
    private final Location sanctuary;
    
    //Hyrule Castle has a Small Key in a location
    //Note -- The Big Keys is in a guard
    private final String SMALL_KEY = "Sewers Key";
    
    //Prevent Magic Number
    private final int TOTAL_SMALL_KEYS = 1;
    
    public final static String NAME = "Hyrule Castle";
    
    public HyruleCastle() {
        super();
        
        linksUncle = new Location("Link's Uncle");
        secretPassage = new Location("Secret Passage");
        
        mapChest = new Location("Hyrule Castle - Map Chest");
        boomerangChest = new Location("Hyrule Castle - Boomerang Chest");
        zeldasCell = new Location("Hyrule Castle - Zelda's Cell");
        
        sewersDarkCross = new Location("Sewers - Dark Cross");
        
        sewersSecretRoomLeft = new Location("Sewers - Secret Room - Left");
        sewersSecretRoomMiddle = new Location("Sewers - Secret Room - Middle");
        sewersSecretRoomRight = new Location("Sewers - Secret Room - Right");
        
        sanctuary = new Location("Sanctuary");
    }
    
    /**
     * Check to see if there is a way to enter the dungeon
     * Hyrule Castle is always open
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory){
        return false;
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Hyrule Castle requires the lantern and the gloves to full clear
     * (Gloves are required because the sewers key could be in the secret room)
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        return (inventory.getItem(KeyItem.LANTERN).isOwned() && 
                inventory.getItem(Gloves.GLOVES).isOwned());      
    }
    
    //No Big Key in a location
    
    /**
     * Check to see how many small keys have been picked up
     * @return The number of small keys that have been acquired
     */
    private int smallKeysAcquired() {
        int numSmallKeys = 0;
        
        Location[] possibleSmallKeys = {mapChest, sewersDarkCross, 
            sewersSecretRoomLeft, sewersSecretRoomMiddle, 
            sewersSecretRoomRight};
        
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
        
        if(closed(inventory))
            return inLogic;
        
        if (logicLinksUncle(inventory))
            inLogic.add(linksUncle);
        if (logicSecretPassage(inventory))
            inLogic.add(secretPassage);
        
        if (logicMapChest(inventory))
            inLogic.add(mapChest);
        
        if (smallKeysAcquired() == TOTAL_SMALL_KEYS) {
            if (logicBoomerangChest(inventory))
                inLogic.add(boomerangChest);
            if (logicZeldasCell(inventory))
                inLogic.add(zeldasCell);
        }
        
        if (logicSewersDarkCross(inventory))
            inLogic.add(sewersDarkCross);
        
        inLogic.addAll(logicSewersSecretRoom(inventory));
        
        if (logicSanctuary(inventory))
            inLogic.add(sanctuary);
        
        return inLogic;
    }
    
    /**
     * Check to see if Link's Uncle is in logic
     * It's in logic if the item is not obtained
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicLinksUncle(Inventory inventory) {
        return !linksUncle.isAcquired();
    }
    
    /**
     * Check to see if the Secret Passage is in logic
     * It's in logic if the item is not obtained
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicSecretPassage(Inventory inventory) {
        return !secretPassage.isAcquired();
    }
    
    /**
     * Check to see if the map chest is in logic
     * It's in logic if the item is not obtained
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicMapChest(Inventory inventory) {
        return !mapChest.isAcquired();
    }
    
    /**
     * Check to see if the boomerang chest is in logic
     * It's in logic if the item is not obtained 
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicBoomerangChest(Inventory inventory) {
        return !boomerangChest.isAcquired();
    }
    
    /**
     * Check to see if the boomerang chest is in logic
     * It's in logic if the item is not obtained
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicZeldasCell(Inventory inventory) {
        return !zeldasCell.isAcquired();
    }
    
    /**
     * Check to see if the sewers dark cross chest is in logic
     * It's in logic if the item is not obtained 
     * and the lantern has been obtained
     * @param inventory The current inventory
     * @return True or False if the chest is in logic
     */
    private boolean logicSewersDarkCross(Inventory inventory) {
        if (sewersDarkCross.isAcquired())
            return false;
            
        return inventory.getItem(KeyItem.LANTERN).isOwned();
    }
    
    /**
     * Check to see what's in logic in the sewers secret room
     * All of sewers secret room is in logic if they have not been obtained
     * and the secret room can be reached in either of the following ways:
     * 1) The gloves are acquired
     * 2) The lantern and sewers key are acquired
     * @param inventory The current inventory
     * @return A list of items that are available
     */
    private ArrayList<Location> logicSewersSecretRoom(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (inventory.getItem(Gloves.GLOVES).isOwned() || 
                (smallKeysAcquired() == TOTAL_SMALL_KEYS && 
                inventory.getItem(KeyItem.LANTERN).isOwned())) {
            if (!sewersSecretRoomLeft.isAcquired())
                inLogic.add(sewersSecretRoomLeft);
            if (!sewersSecretRoomMiddle.isAcquired())
                inLogic.add(sewersSecretRoomMiddle);
            if (!sewersSecretRoomRight.isAcquired())
                inLogic.add(sewersSecretRoomRight);
        }
        
        return inLogic;
    }
    
    /**
     * Check to see if sanctuary is in logic
     * It's in logic if the item is not obtained
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicSanctuary(Inventory inventory) {
        return !sanctuary.isAcquired();
    }
    
    //Getters and Setters for the locations below
    
    /**
     * Get a list of all the locations
     * @return All the locations in Hyrule Castle
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(linksUncle);
        locations.add(secretPassage);
        
        locations.add(mapChest);
        locations.add(boomerangChest);
        locations.add(zeldasCell);
        
        locations.add(sewersDarkCross);
        
        locations.add(sewersSecretRoomLeft);
        locations.add(sewersSecretRoomMiddle);
        locations.add(sewersSecretRoomRight);
        
        locations.add(sanctuary);
        
        return locations;
    }
    
    /**
     * @return Link's Uncle
     */    
    public Location getLinksUncle() {
        return linksUncle;
    }

    /**
     * @param contents Link's Uncle new item
     */    
    public void setLinksUncle(KeyItem contents) {
        linksUncle.setContents(contents);
    }
    
    /**
     * @return the secret passage
     */    
    public Location getSecretPassage() {
        return secretPassage;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setSecretPassage(KeyItem contents) {
        secretPassage.setContents(contents);
    }
    
    /**
     * @return the map chest
     */    
    public Location getMapChest() {
        return mapChest;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setMapChest(KeyItem contents) {
        mapChest.setContents(contents);
    }
    
    /**
     * @return the boomerang chest
     */    
    public Location getBoomerangChest() {
        return boomerangChest;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setBoomerangChest(KeyItem contents) {
        boomerangChest.setContents(contents);
    }   
    
    /**
     * @return Zelda's Cell chest
     */    
    public Location getZeldasCell() {
        return zeldasCell;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setZeldasCell(KeyItem contents) {
        secretPassage.setContents(contents);
    }
    
    /**
     * @return the sewers dark cross chest
     */    
    public Location getSewersDarkCross() {
        return sewersDarkCross;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setSewersDarkCross(KeyItem contents) {
        sewersDarkCross.setContents(contents);
    }   
    
    /**
     * @return the sewers secret room left chest
     */    
    public Location getSewersSecretRoomLeft() {
        return sewersSecretRoomLeft;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setSewersSecretRoomLeft(KeyItem contents) {
        sewersSecretRoomLeft.setContents(contents);
    }   
    
    /**
     * @return the sewers secret room middle chest
     */    
    public Location getSewersSecretRoomMiddle() {
        return sewersSecretRoomMiddle;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setSewersSecretRoomMiddle(KeyItem contents) {
        sewersSecretRoomMiddle.setContents(contents);
    }   

    /**
     * @return the sewers secret room right chest
     */    
    public Location getSewersSecretRoomRight() {
        return sewersSecretRoomRight;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setSewersSecretRoomRight(KeyItem contents) {
        sewersSecretRoomRight.setContents(contents);
    }  
    
    /**
     * @return the sanctuary chest
     */
    public Location getSanctuary() {
        return sanctuary;
    }

    /**
     * @param contents The new contents of the chest
     */    
    public void setSanctuary(KeyItem contents) {
        sanctuary.setContents(contents);
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