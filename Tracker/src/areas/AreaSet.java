/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

import java.util.HashMap;

/**
 *
 * @author Andrew
 */
public class AreaSet {
    
    //A way to track all the different areas
    private final HashMap<String,Area> areas;

    public AreaSet() {
        areas = new HashMap<>();
        
        rewards.put(Reward.GREEN_PENDANT, new Reward(Reward.GREEN_PENDANT));    
        rewards.put(Reward.BLUE_PENDANT, new Reward(Reward.BLUE_PENDANT));        
        rewards.put(Reward.RED_PENDANT, new Reward(Reward.RED_PENDANT));    
        
        rewards.put(Reward.CRYSTAL_1, new Reward(Reward.CRYSTAL_1));
        rewards.put(Reward.CRYSTAL_2, new Reward(Reward.CRYSTAL_2));
        rewards.put(Reward.CRYSTAL_3, new Reward(Reward.CRYSTAL_3));
        rewards.put(Reward.CRYSTAL_4, new Reward(Reward.CRYSTAL_4));
        rewards.put(Reward.CRYSTAL_5, new Reward(Reward.CRYSTAL_5));
        rewards.put(Reward.CRYSTAL_6, new Reward(Reward.CRYSTAL_6));
        rewards.put(Reward.CRYSTAL_7, new Reward(Reward.CRYSTAL_7));
    }
    
    public HashMap<String,Reward> getRewards() {
        return rewards;
    }
}
