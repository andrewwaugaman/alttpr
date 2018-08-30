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
    
    public static final String MAIL = "Mail";
    public static final String GREEN_MAIL = "Green Mail";
    public static final String BLUE_MAIL = "Blue Mail";
    public static final String RED_MAIL = "Red Mail";
    
    private final String[] options = {GREEN_MAIL, BLUE_MAIL, RED_MAIL};
    
    public Mail(){
        super(GREEN_MAIL);
        super.setOwned(true);
    }
    
    public void update(int level) {
        super.update(level, options);
    }
}
