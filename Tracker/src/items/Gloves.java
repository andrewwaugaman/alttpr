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
public class Gloves extends ProgressiveItem {
    
    public static final String GLOVES = "Gloves";
    public static final String POWER_GLOVES = "Power Gloves";
    public static final String TITANS_MITTS = "Titan's Mitts";
    
    private final String[] options = {GLOVES, POWER_GLOVES, TITANS_MITTS};
    
    public Gloves(){
        super(GLOVES);
    }
    
    public void update(int level) {
        super.update(level,options);
    }
    
}
