package areas;

import java.util.HashMap;
/**
 *
 * @author Andrew
 */
public class RewardSet {
                  
    //A way to track all the different rewards
    private final HashMap<String,Reward> rewards;

    /**
     * Constructor Method
     * Create all the different rewards and put them into the hash
     */
    public RewardSet() {
        rewards = new HashMap<>();
        
        rewards.put(Reward.GREEN_PENDANT, new Reward(Reward.GREEN_PENDANT));    
        rewards.put(Reward.BLUE_PENDANT, new Reward(Reward.BLUE_PENDANT));        
        rewards.put(Reward.RED_PENDANT, new Reward(Reward.RED_PENDANT));    
        
        rewards.put(Reward.AGAHNIM_1, new Reward(Reward.AGAHNIM_1));
        
        rewards.put(Reward.CRYSTAL_1, new Reward(Reward.CRYSTAL_1));
        rewards.put(Reward.CRYSTAL_2, new Reward(Reward.CRYSTAL_2));
        rewards.put(Reward.CRYSTAL_3, new Reward(Reward.CRYSTAL_3));
        rewards.put(Reward.CRYSTAL_4, new Reward(Reward.CRYSTAL_4));
        rewards.put(Reward.CRYSTAL_5, new Reward(Reward.CRYSTAL_5));
        rewards.put(Reward.CRYSTAL_6, new Reward(Reward.CRYSTAL_6));
        rewards.put(Reward.CRYSTAL_7, new Reward(Reward.CRYSTAL_7));
        
        rewards.put(Reward.AGAHNIM_2, new Reward(Reward.AGAHNIM_2));
    }
    
    /**
     * @return The set of rewards
     */
    public HashMap<String,Reward> getRewards() {
        return rewards;
    }
    
}
