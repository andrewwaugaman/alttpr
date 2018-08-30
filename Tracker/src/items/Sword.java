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
public class Sword extends ProgressiveItem {
    
    public static final String SWORD = "Sword";
    public static final String FIGHTER_SWORD = "Fighter's Sword";
    public static final String MASTER_SWORD = "Master Sword";
    public static final String TEMPERED_SWORD = "Tempered Sword";
    public static final String GOLDEN_SWORD = "Golden Sword";
    
    private final String[] options = {SWORD, FIGHTER_SWORD, MASTER_SWORD,
        TEMPERED_SWORD, GOLDEN_SWORD};
    
    public Sword(){
        super(SWORD);
    }
    
    public void update(int level) {
        super.update(level, options);
    }
}
