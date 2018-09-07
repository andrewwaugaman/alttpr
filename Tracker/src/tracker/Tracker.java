/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tracker;

import areas.*;
import items.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class Tracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
       // inventory.printInventory();
        Scanner scan = new Scanner(System.in);
        
        RewardSet rewards = new RewardSet();
        AreaSet areas = new AreaSet();
        
        int menu_option = 1;
        
        while (menu_option != 0) {
            System.out.println();
            System.out.println();
            
            System.out.println("**********Menu**********");
            System.out.println("1 - Display Inventory");
            System.out.println("2 - Add Item to Inventory");
            System.out.println("3 - Remove Item from Inventory");
            System.out.println("4 - Add Item (Sword) To Inventory");
            System.out.println("5 - Show available items");
            System.out.println("0 - Quit");
            System.out.println("************************");
            System.out.println();
            System.out.print("Enter your choice: ");
            menu_option = scan.nextInt();
            scan.nextLine(); //Gets rid of newline character
            
            System.out.println();
            String item;
            switch (menu_option) {
                case 1:
                    inventory.printInventory();
                    break;
                case 2:
                    System.out.print("What item did you get? ");
                    item = scan.nextLine();
                    System.out.println(item);
                    inventory.updateItem(item, true);
                    break;
                case 3: 
                    System.out.print("What item did you lose? ");
                    item = scan.next();
                    inventory.updateItem(item, false);
                    break;
                case 4:
                    System.out.print("What level sword? ");
                    int level = scan.nextInt();
                    inventory.updateProgressive("Sword",level);
                    break;
                case 5:
                    ArrayList<Location> inLogic = 
                            areas.locationsInLogic(inventory, rewards);
                    for (int i = 0; i < inLogic.size(); i++)
                        System.out.println(inLogic.get(i).getDescription());
                    break;
                case 6:
                    System.out.print("How many bottles? ");
                    int quantity = scan.nextInt();
                    inventory.updateBottle(quantity);
            }
            
            System.out.println();
            System.out.println();
        }
    }
    
}
