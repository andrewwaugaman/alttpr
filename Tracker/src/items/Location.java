package items;

/**
 * 
 * @author Andrew
 */
public class Location {
    
    //Name of the location along with if the item inside has been picked up
    private final String description;
    private Item contents;
    private boolean acquired;
    
    /**
     * Constructor Method
     * Creates the location with a description of it
     * @param description The name of the location
     */
    public Location (String description) {
        this.description = description;
        acquired = false;
    }
    
    /**
     * @return The name of the location
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * A new item has been picked up inside a location
     * @param contents The item inside the location
     */
    public void setContents(Item contents) {
        this.contents = contents;
        acquired = true;
        if (contents instanceof KeyItem)
            ((KeyItem)(contents)).setOwned(true);
    }
    
    /**
     * An item has been seen at a location (such as seeing Spectacle Rock)
     * @param contents The item inside the location
     * @param acquired Whether or not the item has been picked up
     */
    public void setContents(Item contents, boolean acquired) {
        this.contents = contents;
        this.acquired = acquired;
        if (contents instanceof KeyItem)
            ((KeyItem)(contents)).setOwned(acquired);
    }
    
    /**
     * Set the location as unknown and not obtained
     */
    public void unSetContents() {
        contents = null;
        acquired = false;
    }
 
    /**
     * @return The item inside the location
     */
    public Item getContents() {
        return contents;
    }
    
    /**
     * @return Whether or not the item in the location has been picked up
     */
    public boolean isAcquired() {
        return acquired;
    }
    
}
