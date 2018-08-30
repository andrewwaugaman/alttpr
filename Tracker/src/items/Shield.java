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
public class Shield extends ProgressiveItem {

    public static final String SHIELD = "Shield";
    public static final String FIGHTER_SHIELD = "Fighter's Shield";
    public static final String RED_SHIELD = "Red Shield";
    public static final String MIRROR_SHIELD = "Mirror Shield";
    
    private final String[] options = {SHIELD, FIGHTER_SHIELD, 
        RED_SHIELD, MIRROR_SHIELD};
    
    public Shield(){
        super(SHIELD);
    }
    
    public void update(int level) {
        super.update(level, options);
    }
    
}
