package items;

/**
 * To be used for an item in the inventory
 * @author Andrew
 */
public class Item {
    
    //The name of the item and if it's obtained
    private String description;
    
    /**
     * Constructor Method
     * Creates the item with a description of it
     * @param description The name of the item
     */
    public Item(String description) {
        this.description = description;
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
}
