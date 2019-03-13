/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

import items.Gloves;
import items.Inventory;
import items.KeyItem;
import items.Location;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class DeathMountainTest {
    
    public DeathMountainTest() {
    }

    /**
     * Test of locationsInLogic method, of class DeathMountain.
     */
    @Test
    public void testLocationsInLogic() {
        //To-do
        System.out.println("locationsInLogic");
        Inventory inventory = null;
        DeathMountain instance = new DeathMountain();
        ArrayList<Location> expResult = null;
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closed method, of class DeathMountain.
     */
    @Test
    public void testClosed() {
        System.out.println("closed");
        Inventory inventory = new Inventory();
        DeathMountain instance = new DeathMountain();
        
        boolean expResult = true;
        boolean result = instance.closed(inventory);
        assertEquals(expResult, result);
        
        inventory.updateProgressive(Gloves.GLOVES, 1);
        expResult = true;
        result = instance.closed(inventory);
        assertEquals(expResult, result);
        
        inventory.updateItem(KeyItem.LANTERN, true);
        expResult = false;
        result = instance.closed(inventory);
        assertEquals(expResult, result);
        
        Inventory inventory2 = new Inventory();
        inventory2.updateItem(KeyItem.FLUTE, true);
        expResult = false;
        result = instance.closed(inventory2);
        assertEquals(expResult, result);
    }

    /**
     * Test of eastDeathMountainAccess method, of class DeathMountain.
     */
    @Test
    public void testEastDeathMountainAccess() {
        System.out.println("eastDeathMountainAccess");
        Inventory inventory = new Inventory();
        DeathMountain instance = new DeathMountain();
        boolean expResult = false;
        boolean result = instance.eastDeathMountainAccess(inventory);
        assertEquals(expResult, result);
        
        inventory.updateItem(KeyItem.FLUTE, true);
        expResult = false;
        result = instance.eastDeathMountainAccess(inventory);
        assertEquals(expResult, result);
        
        inventory.updateItem(KeyItem.HOOKSHOT, true);
        expResult = true;
        result = instance.eastDeathMountainAccess(inventory);
        assertEquals(expResult, result);
        
        Inventory inventory2 = new Inventory();
        inventory2.updateProgressive(Gloves.GLOVES, 2);
        inventory2.updateItem(KeyItem.LANTERN, true);
        expResult = false;
        result = instance.eastDeathMountainAccess(inventory2);
        assertEquals(expResult, result);
        
        inventory2.updateItem(KeyItem.MIRROR, true);
        inventory2.updateItem(KeyItem.HAMMER, true);
        expResult = true;
        result = instance.eastDeathMountainAccess(inventory2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class DeathMountain.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DeathMountain instance = new DeathMountain();
        String expResult = "Death Mountain";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
