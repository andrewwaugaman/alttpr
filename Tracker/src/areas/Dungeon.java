package areas;

/**
 * Parent class for the various dungeons in the game.  Each 
 * dungeon has a reward (Pendant, Crystal, or Agahnim) and a 
 * number of locations.  This class takes care of the reward, 
 * the child classes will handle the locations inside each of them
 * 
 * Dungeons to be created:
 * Eastern Palace, Desert Palace, Tower of Hera, Castle Tower,
 * Dark Palace, Swamp Palace, Skull Woods, Thieves Town, 
 * Ice Palace, Misery Mire, Turtle Rock, and Ganon's Tower
 * @author Andrew
 */
public abstract class Dungeon extends Area {
    
    //The reward of the dungeon, a Pendant or Crytstal
    private Reward reward;
    
    /**
     * Constructor method.  Creates an unknown reward that can be 
     * updated later when it's known
     */
    public Dungeon() {
        this.reward = new Reward(Reward.UNKNOWN);
    }
    
    /**
     * Constructor method.  Sets the reward of the dungeon
     * @param reward The reward of the dungeon
     */
    public Dungeon(Reward reward) {
        this.reward = reward;
    }
    
    //Getter and Setter methods below
    
    /**
     * @return the reward
     */
    public Reward getReward() {
        return reward;
    }
    
    /**
     * @param reward the new reward for the dungeon
     */
    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
