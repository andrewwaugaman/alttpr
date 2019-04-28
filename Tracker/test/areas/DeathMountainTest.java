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
import items.Sword;
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
        System.out.println("locationsInLogic");
        Inventory inventory = new Inventory();
        DeathMountain instance = new DeathMountain();
        String[] locationsNoItems = {};
        ArrayList<Location> result = instance.locationsInLogic(inventory);
        assertEquals(locationsNoItems.length, result.size());
        for (int i = 0; i < locationsNoItems.length; i++) {
            assertEquals(locationsNoItems[i], result.get(i).getDescription());
        }
        
        inventory.updateItem(KeyItem.FLUTE, true);
        String[] locationsFlute = {"Spectacle Rock Cave"};
        result = instance.locationsInLogic(inventory);
        assertEquals(locationsFlute.length, result.size());
        for (int i = 0; i < locationsFlute.length; i++) {
            assertEquals(locationsFlute[i], result.get(i).getDescription());
        }
        
        inventory.updateItem(KeyItem.HOOKSHOT, true);
        String[] locationsFluteHook = {"Spectacle Rock Cave", "Spiral Cave", 
            "Paradox Cave Lower - Far Left", "Paradox Cave Lower - Left",
            "Paradox Cave Lower - Right", "Paradox Cave Lower - Far Right",
            "Paradox Cave Lower - Middle", "Paradox Cave Upper - Left",
            "Paradox Cave Upper - Right"};
        result = instance.locationsInLogic(inventory);
        assertEquals(locationsFluteHook.length, result.size());
        for (int i = 0; i < locationsFluteHook.length; i++) {
            assertEquals(locationsFluteHook[i], result.get(i).getDescription());
        }
        
        Inventory inventory2 = new Inventory();
        inventory2.updateProgressive(Gloves.GLOVES, 1);
        inventory2.updateItem(KeyItem.LANTERN, true);
        String[] locationsGlovesLantern = {"Old Man", "Spectacle Rock Cave"};
        result = instance.locationsInLogic(inventory2);
        assertEquals(locationsGlovesLantern.length, result.size());
        for (int i = 0; i < locationsGlovesLantern.length; i++) {
            assertEquals(locationsGlovesLantern[i], 
                    result.get(i).getDescription());
        }
        
        inventory2.updateItem(KeyItem.MIRROR, true);
        inventory2.updateProgressive(Sword.SWORD, 2);
        inventory2.updateItem(KeyItem.BOOK, true);
        String[] locationsGlovesLanternMirrorTablet = {"Old Man", 
            "Spectacle Rock Cave", "Spectacle Rock", "Ether Tablet"};
        result = instance.locationsInLogic(inventory2);
        assertEquals(locationsGlovesLanternMirrorTablet.length, result.size());
        for (int i = 0; i < locationsGlovesLanternMirrorTablet.length; i++) {
            assertEquals(locationsGlovesLanternMirrorTablet[i], 
                    result.get(i).getDescription());
        }        
        
        inventory2.updateItem(KeyItem.HAMMER, true);
        String[] locationsHammer = {"Old Man", "Spectacle Rock Cave", 
            "Spectacle Rock", "Ether Tablet", "Spiral Cave", 
            "Paradox Cave Lower - Far Left", "Paradox Cave Lower - Left", 
            "Paradox Cave Lower - Right", "Paradox Cave Lower - Far Right", 
            "Paradox Cave Lower - Middle", "Paradox Cave Upper - Left", 
            "Paradox Cave Upper - Right"};
        result = instance.locationsInLogic(inventory2);
        assertEquals(locationsHammer.length, result.size());
        for (int i = 0; i < locationsHammer.length; i++) {
            assertEquals(locationsHammer[i], result.get(i).getDescription());  
        }
        
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
