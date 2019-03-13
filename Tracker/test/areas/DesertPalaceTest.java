/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

import items.Gloves;
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
public class DesertPalaceTest {
    
    /**
     * Test of canFullClear method, of class DesertPalace.
     */
    @Test
    public void testCanFullClear() {
        System.out.println("canFullClear");
        Inventory inventory = new Inventory();
        DesertPalace instance = new DesertPalace();
        boolean expResult = false;
        boolean result = instance.canFullClear(inventory);
        assertEquals(expResult, result);
        
        inventory.updateItem(KeyItem.BOOK, true);
        result = instance.canFullClear(inventory);
        assertEquals(expResult, result);
        
        inventory.updateItem(KeyItem.BOOTS, true);
        inventory.updateProgressive(Gloves.GLOVES, 1);
        inventory.updateItem(KeyItem.FIRE_ROD, true);
        
        result = instance.canFullClear(inventory);
        expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of locationsInLogic method, of class DesertPalace.
     */
    @Test
    public void testLocationsInLogic() {
        System.out.println("locationsInLogic");
        Inventory inventory = new Inventory();
        DesertPalace instance = new DesertPalace();
        String[] locationsNoItems = {};
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(locationsNoItems.length, result.size());
        for (int i = 0; i < locationsNoItems.length; i++) {
            assertEquals(locationsNoItems[i], result.get(i).getDescription());
        }
        
        inventory.updateItem(KeyItem.BOOK, true);
        String[] locationsBook = {"Desert Palace - Map Chest"};
        result = instance.locationsInLogic(inventory);
        assertEquals(locationsBook.length, result.size());
        for (int i = 0; i < locationsBook.length; i++) {
            assertEquals(locationsBook[i], result.get(i).getDescription());
        }
        
        /**
        String[] locationsAllItems = {"Desert Palace - Map Chest", 
            "Desert Palace - Torch", "Desert Palace - Compass Chest",
            "Desert Palace - Big Key Chest", "Desert Palace - Big Chest",
            "Desert Palace - Lanmolas"};
            */        
    }

    /**
     * Test of toString method, of class DesertPalace.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DesertPalace instance = new DesertPalace();
        String expResult = "Desert Palace";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
