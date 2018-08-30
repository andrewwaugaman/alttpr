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
public abstract class ProgressiveItem extends Item {
    
    private int level;
    
    public ProgressiveItem(String description) {
        super(description);
        level = 0;
    }
    
    abstract void update(int level);
    
    public void update(int level, String[] options) {
        if (level == 0)
            this.setOwned(false);
        else
            this.setOwned(true);
        if (level > options.length)
            this.level = options.length;
        else
            this.level = level;
        super.updateDescription(options[this.level]);
    }

    public int getLevel() {
        return level;
    }
}
