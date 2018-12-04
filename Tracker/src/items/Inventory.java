package items;

import java.util.HashMap;

/**
 * To be used to manage the equipment
 * @author Andrew
 */
public class Inventory {
            
    //The current inventory of items
    private final HashMap<String,KeyItem> inventory;
    
    /**
     * Constructor Method
     * Creates all the items in the hash with their key as a description
     */
    public Inventory() {
        inventory = new HashMap<>();
        inventory.put(KeyItem.BOW, new KeyItem(KeyItem.BOW));
        inventory.put(KeyItem.SILVER_ARROWS, 
                new KeyItem(KeyItem.SILVER_ARROWS));
        inventory.put(KeyItem.BLUE_BOOMERANG, 
                new KeyItem(KeyItem.BLUE_BOOMERANG));
        inventory.put(KeyItem.RED_BOOMERANG, 
                new KeyItem(KeyItem.RED_BOOMERANG));
        inventory.put(KeyItem.HOOKSHOT, new KeyItem(KeyItem.HOOKSHOT));
        inventory.put(KeyItem.MUSHROOM, new KeyItem(KeyItem.MUSHROOM));
        inventory.put(KeyItem.POWDER, new KeyItem(KeyItem.POWDER));
        inventory.put(KeyItem.FIRE_ROD, new KeyItem(KeyItem.FIRE_ROD));
        inventory.put(KeyItem.ICE_ROD, new KeyItem(KeyItem.ICE_ROD));
        inventory.put(KeyItem.BOMBOS, new KeyItem(KeyItem.BOMBOS));
        inventory.put(KeyItem.ETHER, new KeyItem(KeyItem.ETHER));
        inventory.put(KeyItem.QUAKE, new KeyItem(KeyItem.QUAKE));
        inventory.put(KeyItem.LANTERN, new KeyItem(KeyItem.LANTERN));
        inventory.put(KeyItem.HAMMER, new KeyItem(KeyItem.HAMMER));
        inventory.put(KeyItem.FLUTE, new KeyItem(KeyItem.FLUTE));
        inventory.put(KeyItem.SHOVEL, new KeyItem(KeyItem.SHOVEL));
        inventory.put(KeyItem.BUG_NET, new KeyItem(KeyItem.BUG_NET));
        inventory.put(KeyItem.BOOK, new KeyItem(KeyItem.BOOK));
        inventory.put(Bottles.BOTTLES, new Bottles());
        inventory.put(KeyItem.SOMARIA, new KeyItem(KeyItem.SOMARIA));
        inventory.put(KeyItem.BYRNA, new KeyItem(KeyItem.BYRNA));
        inventory.put(KeyItem.CAPE, new KeyItem(KeyItem.CAPE));
        inventory.put(KeyItem.MIRROR, new KeyItem(KeyItem.MIRROR));
        inventory.put(KeyItem.BOOTS, new KeyItem(KeyItem.BOOTS));
        inventory.put(KeyItem.FLIPPERS, new KeyItem(KeyItem.FLIPPERS));
        inventory.put(KeyItem.MOON_PEARL, new KeyItem(KeyItem.MOON_PEARL));
        inventory.put(Gloves.GLOVES, new Gloves());
        inventory.put(Sword.SWORD, new Sword());
        inventory.put(Shield.SHIELD, new Shield());
        inventory.put(Mail.MAIL, new Mail());
    }
    
    /**
     * Update the item if it's obtained or not
     * @param item The item to be updated
     * @param status Whether or not the item is obtained
     */
    public void updateItem(String item, boolean status) {
        inventory.get(item).setOwned(status);
    }
    
    /**
     * Update a progressive item with the new level
     * @param item The item to be updated
     * @param level The new level of the item
     */
    public void updateProgressive(String item, int level) {
        ((ProgressiveItem)(inventory.get(item))).update(level);
    }
    
    /**
     * Updates the bottle with the new number
     * Note -- Bottles are the only item that multiple can be obtained
     * @param quantity 
     */
    public void updateBottle(int quantity) {
        ((Bottles)(inventory.get(Bottles.BOTTLES))).update(quantity);
    }
    
    /**
     * Print the current inventory for each item and if it's obtained
     */
    public void printInventory() {
        for (KeyItem value : inventory.values())
            value.print();
    }
    
    /**
     * Returns a particular item from the inventory
     * @param key The name of the item to obtain
     * @return The item matching the key from the inventory
     */
    public KeyItem getItem(String key) {
        return inventory.get(key);
    }

    /**
     * @return The current inventory
     */
    public HashMap<String,KeyItem> getInventory() {
        return inventory;
    }
}
