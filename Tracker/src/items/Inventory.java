package items;

import java.util.HashMap;

/**
 * To be used to manage the equipment
 * @author Andrew
 */
public class Inventory {
            
    //The current inventory of items
    private final HashMap<String,Item> inventory;
    
    /**
     * Constructor Method
     * Creates all the items in the hash with their key as a description
     */
    public Inventory() {
        inventory = new HashMap<>();
        inventory.put(Item.BOW, new Item(Item.BOW));
        inventory.put(Item.SILVER_ARROWS, new Item(Item.SILVER_ARROWS));
        inventory.put(Item.BLUE_BOOMERANG, new Item(Item.BLUE_BOOMERANG));
        inventory.put(Item.RED_BOOMERANG, new Item(Item.RED_BOOMERANG));
        inventory.put(Item.HOOKSHOT, new Item(Item.HOOKSHOT));
        inventory.put(Item.MUSHROOM, new Item(Item.MUSHROOM));
        inventory.put(Item.POWDER, new Item(Item.POWDER));
        inventory.put(Item.FIRE_ROD, new Item(Item.FIRE_ROD));
        inventory.put(Item.ICE_ROD, new Item(Item.ICE_ROD));
        inventory.put(Item.BOMBOS, new Item(Item.BOMBOS));
        inventory.put(Item.ETHER, new Item(Item.ETHER));
        inventory.put(Item.QUAKE, new Item(Item.QUAKE));
        inventory.put(Item.LANTERN, new Item(Item.LANTERN));
        inventory.put(Item.HAMMER, new Item(Item.HAMMER));
        inventory.put(Item.FLUTE, new Item(Item.FLUTE));
        inventory.put(Item.SHOVEL, new Item(Item.SHOVEL));
        inventory.put(Item.BOOK, new Item(Item.BOOK));
        inventory.put(Bottles.BOTTLES, new Bottles());
        inventory.put(Item.SOMARIA, new Item(Item.SOMARIA));
        inventory.put(Item.BYRNA, new Item(Item.BYRNA));
        inventory.put(Item.CAPE, new Item(Item.CAPE));
        inventory.put(Item.MIRROR, new Item(Item.MIRROR));
        inventory.put(Item.BOOTS, new Item(Item.BOOTS));
        inventory.put(Item.FLIPPERS, new Item(Item.FLIPPERS));
        inventory.put(Item.MOON_PEARL, new Item(Item.MOON_PEARL));
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
        for (Item value : inventory.values())
            value.print();
    }
    
    /**
     * Returns a particular item from the inventory
     * @param key The name of the item to obtain
     * @return The item matching the key from the inventory
     */
    public Item getItem(String key) {
        return inventory.get(key);
    }

    /**
     * @return The current inventory
     */
    public HashMap<String,Item> getInventory() {
        return inventory;
    }
}
