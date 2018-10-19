package items;

/**
 *
 * @author Andrew
 */
public abstract class ProgressiveItem extends Item {
    
    //The level of the item
    private int level;
    
    /**
     * Constructor Method
     * Creates the progressive item with a description starting at level 0
     * @param description The name of the item
     */
    public ProgressiveItem(String description) {
        super(description);
        level = 0;
    }
    
    /**
     * Used for a generic progressive item which will then 
     * pass the possible descriptions to the update below
     * @param level 
     */
    abstract void update(int level);
    
    /**
     * Updates the level and description of the item
     * @param level The new level of the item
     * @param options The possible descriptions that can be set
     */
    public void update(int level, String[] options) {
        if (level == 0)
            this.setOwned(false);
        else
            this.setOwned(true);
        if (level > options.length)
            this.level = options.length;
        else
            this.level = level;
        super.updateDescription(options[this.level]);
    }

    /**
     * @return the level of the item
     */
    public int getLevel() {
        return level;
    }
}
