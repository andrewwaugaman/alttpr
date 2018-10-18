/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;
import items.*;
import java.util.ArrayList;

/**
 * @author Andrew
 */
public class CastleTower extends Dungeon {

    //Castle Tower has 2 possible item locations
    private final Location room03;
    private final Location darkMaze;
    
    //Castle Tower Palace has 2 Small Keys
    private final String SMALL_KEY = "Agahnims Tower Key";
    
    //Name of the dungeon
    public final static String NAME = "Castle Tower";
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Dungeon) 
     * Instantiate the 6 chests with their description
     * @param reward The reward of the tower (Agahnim 1)
     */
    public CastleTower(Reward reward) {
        super(reward);
        room03 = new Location("Castle Tower - Room 03");
        darkMaze = new Location("Castle Tower - Dark Maze");
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

        if (logicRoom03(inventory))
            inLogic.add(room03);
        if (logicDarkMaze(inventory))
            inLogic.add(darkMaze);
        return inLogic;
    }
       
    /**
     * Check to see if there is a way to enter the tower
     * Castle Tower can be entered if either:
     * 1) The Master Sword (or higher) is obtained
     * 2) The Cape is obtained
     * @param inventory The current inventory 
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory){
        if (((Sword)(inventory.getItem(Sword.SWORD))).getLevel() >= 2)
            return false;
        
        return !inventory.getItem(Item.CAPE).isOwned();
    }
    
    /**
     * Check to see if it's possible to full clear the dungeon
     * Castle Tower requires the following to full clear:
     * 1) Lantern
     * 2) A Sword
     * @param inventory The current inventory
     * @return True or False if it can be full cleared
     */
    public boolean canFullClear(Inventory inventory) {
        //Takes care of checking for the cape for access
        if(closed(inventory))
            return false;
        
        //1) Check the Lantern
        if(!inventory.getItem(Item.LANTERN).isOwned())
            return false;
        
        //2) Check the Sword        
        return inventory.getItem(Sword.SWORD).isOwned();
    }
   
    /**
     * Check to see if the room 03 chest is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicRoom03(Inventory inventory) {
        return !room03.isAcquired();
    }
    
    /**
     * Check to see if the dark maze chest is in logic
     * It's in logic if it's not opened, 1 small key is 
     * acquired, and the lantern is acquired
     * @param inventory The current inventory
     * @return True or False if the chest is in logic
     */
    private boolean logicDarkMaze(Inventory inventory) {
        if (darkMaze.isAcquired())
            return false;
        
        if (smallKeysAcquired() == 1)
            return false;
        
        return inventory.getItem(Item.LANTERN).isOwned();
    }
    
    
    /**
     * Check to see how many small keys have been picked up
     * @return True or False if the big key is acquired
     * @return Number of keys acquired
     */
    private int smallKeysAcquired() {
        
        int acquired = 0;
        
        if (room03.isAcquired() && 
                room03.getContents().getDescription().equals(SMALL_KEY))
            acquired+=1;
        
        if(darkMaze.isAcquired() && 
                darkMaze.getContents().getDescription().equals(SMALL_KEY))
            acquired+=1;
        
        return acquired;
    }
    
    //Getters and Setters for the locations below

    /**
     * @return room03
     */
    public Location getRoom03() {
        return room03;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setRoom03(Item contents) {
        room03.setContents(contents);
    }

    /**
     * @return the darkMaze
     */
    public Location getDarkMaze() {
        return darkMaze;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setDarkMaze(Item contents) {
        darkMaze.setContents(contents);
    }    
}
