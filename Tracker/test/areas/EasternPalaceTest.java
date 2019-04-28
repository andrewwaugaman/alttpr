/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

import items.Inventory;
import items.Item;
import items.KeyItem;
import items.Location;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class EasternPalaceTest {

    /**
     * Test of canFullClear method, of class EasternPalace.
     */
    @Test
    public void testCanFullClear() {
        System.out.println("canFullClear");
        Inventory inventory = new Inventory();
        EasternPalace instance = new EasternPalace();
        boolean expResult = false;
        boolean result = instance.canFullClear(inventory);
        assertEquals(expResult, result);
        
        inventory.updateItem(KeyItem.LANTERN, true);
        result = instance.canFullClear(inventory);
        assertEquals(expResult, result);
        
        inventory.updateItem(KeyItem.BOW, true);
        result = instance.canFullClear(inventory);
        expResult = true;
        assertEquals(expResult, result);
    }

    // The following tests all test locationsInLogic
    
    @Test
    public void testLocationsNoItems() {
        Inventory inventory = new Inventory();
        EasternPalace instance = new EasternPalace();
        String[] locations = {"Eastern Palace - Cannonball Chest", 
            "Eastern Palace - Map Chest", "Eastern Palace - Compass Chest"};
        
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(locations.length, result.size());       
        for (int i = 0; i < locations.length; i++) {
            assertEquals(locations[i], result.get(i).getDescription());
        }
    }
    
    @Test
    public void testLocationsLantern() { 
        Inventory inventory = new Inventory();
        inventory.updateItem(KeyItem.LANTERN, true);
        EasternPalace instance = new EasternPalace();
        
        String[] locations = {"Eastern Palace - Cannonball Chest", 
            "Eastern Palace - Map Chest", "Eastern Palace - Compass Chest",
            "Eastern Palace - Big Key Chest"};
        
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(locations.length, result.size());        
        for (int i = 0; i < locations.length; i++) {
            assertEquals(locations[i], result.get(i).getDescription());
        }
    }
    
    @Test
    public void testBigChest() {
        Inventory inventory = new Inventory();
        EasternPalace instance = new EasternPalace();
        
        instance.setCannonballChest(new Item(EasternPalace.BIG_KEY));
        String[] locations = {"Eastern Palace - Map Chest", 
            "Eastern Palace - Compass Chest", "Eastern Palace - Big Chest"};
        
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(locations.length, result.size());
        for (int i = 0; i < locations.length; i++) {
            assertEquals(locations[i], result.get(i).getDescription());
        }
    }
    
    @Test
    public void testBowNoLantern() {
        Inventory inventory = new Inventory();
        EasternPalace instance = new EasternPalace();
        
        instance.setMapChest(new Item(EasternPalace.BIG_KEY));
        String[] locations = {"Eastern Palace - Cannonball Chest", 
            "Eastern Palace - Compass Chest", "Eastern Palace - Big Chest"};
        
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(locations.length, result.size());        
        for (int i = 0; i < locations.length; i++) {
            assertEquals(locations[i], result.get(i).getDescription());
        }
    }
    
    @Test
    public void testLanternAndBow() {
        Inventory inventory = new Inventory();
        inventory.updateItem(KeyItem.LANTERN, true);
        inventory.updateItem(KeyItem.BOW, true);
        EasternPalace instance = new EasternPalace();
        
        instance.setBigKeyChest(new Item(EasternPalace.BIG_KEY));
        String[] locations = {"Eastern Palace - Cannonball Chest", 
            "Eastern Palace - Map Chest", "Eastern Palace - Compass Chest",
            "Eastern Palace - Big Chest", "Eastern Palace - Armos Knights"};
        
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(locations.length, result.size());
        for (int i = 0; i < locations.length; i++) {
            assertEquals(locations[i], result.get(i).getDescription());
        }
    }

    /**
     * Test of toString method, of class EasternPalace.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        EasternPalace instance = new EasternPalace();
        String expResult = "Eastern Palace";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
