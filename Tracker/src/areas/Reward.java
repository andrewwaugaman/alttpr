/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

/**
 *
 * @author Andrew
 */
public class Reward {
   
    //The 3 Pendants
    public final static String GREEN_PENDANT = "Green Pendant";
    public final static String BLUE_PENDANT = "Blue Pendant";
    public final static String RED_PENDANT = "Red Pendant";
    
    //The 7 Crystals
    public final static String CRYSTAL_1 = "Crystal 1";
    public final static String CRYSTAL_2 = "Crystal 2";
    public final static String CRYSTAL_3 = "Crystal 3";
    public final static String CRYSTAL_4 = "Crystal 4";
    public final static String CRYSTAL_5 = "Crystal 5";
    public final static String CRYSTAL_6 = "Crystal 6";
    public final static String CRYSTAL_7 = "Crystal 7";
    
    //Agahnim for checks
    public final static String AGAHNIM_1 = "Agahnim 1";
    public final static String AGAHNIM_2 = "Agahnim 2";
    
    //When the Reward is unknown
    public final static String UNKNOWN = "Unknown";
    
    //If I implement a GUI
    public final static String GENERIC_PENDANT = "Pendant";
    public final static String BLUE_CRYSTAL = "Blue Crystal";
    public final static String RED_CRYSTAL = "Red Crystal";
    
    //Description of the reward and the status
    private String description;
    private boolean acquired;
    
    /**
     * Constructor method.  Sets the description to unknown
     * @param description What Pendant or Crystal the reward is
     */
    public Reward(String description) {
        this.description = description;
    }
    
    //Getters and Setters below
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setAcquired(boolean acquired) {
        this.acquired = acquired;
    }
    
    public boolean isAcquired() {
        return acquired;
    }
}
