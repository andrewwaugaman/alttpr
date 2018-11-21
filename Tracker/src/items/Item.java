package items;

/**
 * To be used for an item in the inventory
 * @author Andrew
 */
public class Item {
    
    //The name of all the (non-progressive/bottles) items
    public final static String BOW = "Bow";
    public final static String SILVER_ARROWS = "Silver Arrows";
    public final static String BLUE_BOOMERANG = "Blue Boomerang";      
    public final static String RED_BOOMERANG = "Red Boomerang";
    public final static String HOOKSHOT = "Hookshot";
    public final static String MUSHROOM = "Mushroom";
    public final static String POWDER = "Magic Powder";
    public final static String FIRE_ROD = "Fire Rod";
    public final static String ICE_ROD = "Ice Rod";
    public final static String BOMBOS = "Bombos Medallion";
    public final static String ETHER = "Ether Medallion";
    public final static String QUAKE = "Quake Medallion";
    public final static String LANTERN = "Lantern";
    public final static String HAMMER = "Hammer";
    public final static String FLUTE = "Flute";
    public final static String SHOVEL = "Shovel";
    public final static String BUG_NET = "Bug Net";
    public final static String BOOK = "Book of Mudora";
    public final static String SOMARIA = "Cane of Somaria";
    public final static String BYRNA = "Cane of Byrna";
    public final static String CAPE = "Magic Cape";
    public final static String MIRROR = "Magic Mirror";
    public final static String BOOTS = "Pegasus Boots";
    public final static String FLIPPERS = "Zora's Flippers";
    public final static String MOON_PEARL = "Moon Pearl";

    //Used for entry into MiseryMire and TurtleRock
    public final static String UNKNOWN_MEDALLION = "Unknown Medallion";
    
    //The name of the item and if it's obtained
    private String description;
    private boolean owned;
    
    /**
     * Constructor Method
     * Creates the item with a description of it
     * @param description The name of the item
     */
    public Item (String description) {
        this.description = description;
        owned = false;
    }
   
    /**
     * Prints the description of the item along with if it's acquired
     */
    public void print () {
        if (owned)
            System.out.println(description + ": Acquired");
        else
            System.out.println(description + ": Not Acquired");
    } 
    
    /**
     * @return the item's description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @param description the item's new description (Used by Progressive Items)
     */
    public void updateDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return if the item is acquired or not
     */
    public boolean isOwned() {
        return owned;
    }
    
    /**
     * @param status whether or not the item is owned
     */
    public void setOwned (boolean status) {
        owned = status;
    }   
}
