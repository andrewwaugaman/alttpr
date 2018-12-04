package items;

/**
 *
 * @author Andrew
 */
public class Bottles extends KeyItem {
    
    //Name, used for a key value in inventory
    public final static String BOTTLES = "Bottles";
    
    //The number of bottles the player has with a maximum of 4
    private int quantity;
    private final int MAXIMUM = 4;
    
    /**
     * Constructor Method
     * Calls the parent constructor (in Item) with the description
     * Instantiate the bottles with none collected
     */
    public Bottles() {
        super(BOTTLES);
        
        quantity = 0;
    }
    
    /**
     * Updates the number of bottles owned
     * If no bottles are owned, set the value in the Item parent class
     * Otherwise set the number to the maximum or current quantity
     * @param quantity The number of bottles obtained
     */
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
    
    /**
     * Prints the description of the item along with the number obtained
     */
    @Override
    public void print() {
        if (super.isOwned())
            System.out.println(super.getDescription() + ": " + quantity +
                    " Acquired");
        else
            System.out.println(super.getDescription() + ": Not Acquired");
       
    }
    
}
