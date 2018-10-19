package items;

/**
 *
 * @author Andrew
 */
public class Sword extends ProgressiveItem {
    
    //The names of the different options of sword
    public static final String SWORD = "Sword";
    public static final String FIGHTER_SWORD = "Fighter's Sword";
    public static final String MASTER_SWORD = "Master Sword";
    public static final String TEMPERED_SWORD = "Tempered Sword";
    public static final String GOLDEN_SWORD = "Golden Sword";
    
    //A list of the levels to be used by update
    private final String[] options = {SWORD, FIGHTER_SWORD, MASTER_SWORD,
        TEMPERED_SWORD, GOLDEN_SWORD};
    
    /**
     * Constructor Method
     * Calls the parent constructor (in ProgressiveItem) with the description
     */
    public Sword() {
        super(SWORD);
    }
    
    /**
     * Update the level and description of the sword
     * @param level The new level of sword obtained
     */
    @Override
    public void update(int level) {
        super.update(level, options);
    }
}
