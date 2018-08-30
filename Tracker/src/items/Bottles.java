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
public class Bottles extends Item {
    
    public final static String BOTTLES = "Bottles";
    
    private int quantity;
    private final int MAXIMUM = 4;
    
    public Bottles(){
        super(BOTTLES);
        quantity = 0;
    }
    
    public void update(int quantity) {
        if (quantity <= 0) {
            super.setOwned(false);
            this.quantity = 0;
        }
        else {
            if (quantity > MAXIMUM)
                this.quantity = MAXIMUM;
            else 
                this.quantity = quantity;
            super.setOwned(true);
        }
    }
    
    public void print() {
        if (super.isOwned())
            System.out.println(super.getDescription() + ": " + quantity +
                    " Acquired");
        else
            System.out.println(super.getDescription() + ": Not Acquired");
       
    }
    
}
