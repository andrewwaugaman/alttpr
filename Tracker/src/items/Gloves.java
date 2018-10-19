package items;

/**
 *
 * @author Andrew
 */
public class Gloves extends ProgressiveItem {
    
    //The names of the different options of Gloves
    public static final String GLOVES = "Gloves";
    public static final String POWER_GLOVES = "Power Gloves";
    public static final String TITANS_MITTS = "Titan's Mitts";
    
    //A list of the levels to be used by update
    private final String[] options = {GLOVES, POWER_GLOVES, TITANS_MITTS};
    
    /**
     * Constructor Method
     * Calls the parent constructor (in ProgressiveItem) with the description
     */
    public Gloves() {
        super(GLOVES);
    }
    
    /**
     * Update the level and description of the gloves
     * @param level The new level of gloves obtained
     */
    @Override
    public void update(int level) {
        super.update(level, options);
    }
    
}
