/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author Andrew
 */
public class Mail extends ProgressiveItem {
    
    //The names of the different options of Mail
    public static final String MAIL = "Mail";
    public static final String GREEN_MAIL = "Green Mail";
    public static final String BLUE_MAIL = "Blue Mail";
    public static final String RED_MAIL = "Red Mail";
    
    //A list of the levels to be used by update
    private final String[] options = {GREEN_MAIL, BLUE_MAIL, RED_MAIL};
    
    /**
     * Constructor Method
     * Calls the parent constructor (in ProgressiveItem) with the description
     */
    public Mail() {
        super(GREEN_MAIL);
        
        //Note -- The player starts with Green Mail
        super.setOwned(true);
    }
    
    /**
     * Update the level and description of the mail
     * @param level The new level of mail obtained
     */
    @Override
    public void update(int level) {
        super.update(level, options);
        
        //Note -- Progressive Item sets an item's status to unowned if 
        //the level is 0, so adding this in case Green Mail needs to
        //be set as the level (Mainly due to a mislick when implemented)
        super.setOwned(true);
    }
}
