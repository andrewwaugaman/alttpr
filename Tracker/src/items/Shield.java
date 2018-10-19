package items;

/**
 *
 * @author Andrew
 */
public class Shield extends ProgressiveItem {

    //The names of the different options of shield
    public static final String SHIELD = "Shield";
    public static final String FIGHTER_SHIELD = "Fighter's Shield";
    public static final String RED_SHIELD = "Red Shield";
    public static final String MIRROR_SHIELD = "Mirror Shield";
    
    //A list of the levels to be used by update
    private final String[] options = {SHIELD, FIGHTER_SHIELD, 
        RED_SHIELD, MIRROR_SHIELD};
    
    /**
     * Constructor Method
     * Calls the parent constructor (in ProgressiveItem) with the description
     */
    public Shield() {
        super(SHIELD);
    }
    
    /**
     * Update the level and description of the shield
     * @param level The new level of shield obtained
     */
    @Override
    public void update(int level) {
        super.update(level, options);
    }
    
}
