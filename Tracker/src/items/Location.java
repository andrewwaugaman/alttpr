/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 * Location and Item
 * @author Andrew
 */
public class Location {
    
    private final String description;
    private Item contents;
    private boolean acquired;
    
    public Location (String description) {
        this.description = description;
        acquired = false;
    }
    
    public String getDescription() {
        return description;
    }
    public void setContents(Item contents) {
        this.contents = contents;
        acquired = true;
    }
    
    public void unSetContents() {
        contents = null;
        acquired = false;
    }
 
    public Item getContents() {
        return contents;
    }
    
    public boolean isAcquired() {
        return acquired;
    }
    
}
