/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

/**
 * Parent class for the various dungeons in the game.  Each dungeon
 * has a reward (Pendant or Crystal) and a number of locations.  This 
 * class takes care of the reward, the child classes will handle the
 * locations inside each of them
 * 
 * Dungeons to be created:
 * Eastern Palace, Desert Palace, Tower of Hera, Dark Palace, Swamp Palace, 
 * Skull Woods, Thieves Town, Ice Palace, Misery Mire, and Turtle Rock
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
    
    //Getter and Setter methods below
    public Reward getReward() {
        return reward;
    }
    
    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
