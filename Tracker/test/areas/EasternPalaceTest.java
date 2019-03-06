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

    /**
     * Test of locationsInLogic method, of class EasternPalace.
     */
    @Test
    public void testLocationsInLogic() {
        System.out.println("locationsInLogic");
        Inventory inventory = new Inventory();
        EasternPalace instance = new EasternPalace();
        String[] locationsNoItems = {"Eastern Palace - Cannonball Chest", 
            "Eastern Palace - Map Chest", "Eastern Palace - Compass Chest"};
        
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        for (int i = 0; i < locationsNoItems.length; i++) {
            assertEquals(locationsNoItems[i], result.get(i).getDescription());
        }
        
        String[] locationsAllItems = {"Eastern Palace - Cannonball Chest", 
            "Eastern Palace - Map Chest", "Eastern Palace - Compass Chest",
            "Eastern Palace - Big Key Chest", "Eastern Palace - Big Chest",
            "Eastern Palace - Armos Knights"};
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
