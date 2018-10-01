/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package areas;

import java.util.ArrayList;
import items.*;

/**
 *
 * @author Andrew
 */
public class DarkWorld extends Area {
    
    //Dark world has 27 possible item locations
    private final Location bumperCave;
    
    private final Location cShapedHouse;
    private final Location chestGame;
    private final Location brewery;


    private final Location hammerPegs;
    private final Location blacksmith;
    private final Location purpleChest;   
    
    private final Location diggingGame;
    private final Location stumpy;
    
    private final Location hypeCaveTop;
    private final Location hypeCaveMiddleRight;
    private final Location hypeCaveMiddleLeft;
    private final Location hypeCaveBottom;
    private final Location hypeCaveNPC;
    
    private final Location mireShedLeft;
    private final Location mireShedRight;
    
    //Special -- Can be obtained without Moon Perl
    private final Location pyramid;
    
    private final Location pyramidFairyLeft;
    private final Location pyramidFairyRight;
    
    private final Location catfish;
    
    private final Location spikeCave;
    
    private final Location superbunnyCaveTop;
    private final Location superbunnyCaveBottom;
    
    private final Location hookshotCaveTopRight;
    private final Location hookshotCaveTopLeft;
    private final Location hookshotCaveBottomLeft;
    private final Location hookshotCaveBottomRight;
    
    //Used to check if Dark World is Open
    private final LightWorld lightWorld;
    private final DeathMountain deathMountain;
    private final Reward agahnim;
    
    public DarkWorld(LightWorld lightWorld, DeathMountain deathMountain,
            Reward agahnim) {
        super();
        
        bumperCave = new Location("Bumper Cave");
        
        cShapedHouse = new Location("C-Shaped House");
        chestGame = new Location("Chest Game");
        brewery = new Location("Brewery");
        
        hammerPegs = new Location("Hammer Pegs");
        blacksmith = new Location("Blacksmith");
        purpleChest = new Location("Purple Chest");

        diggingGame = new Location("Digging Game");
        stumpy = new Location("Stumpy");
        
        hypeCaveTop = new Location("Hype Cave - Top");
        hypeCaveMiddleRight = new Location("Hype Cave - Middle Right");
        hypeCaveMiddleLeft = new Location("Hype Cave - Middle Left");
        hypeCaveBottom = new Location("Hype Cave - Bottom");
        hypeCaveNPC = new Location("Hype Cave - NPC");
        
        mireShedLeft = new Location("Mire Shed - Left");
        mireShedRight = new Location("Mire Shed - Right");
        
        pyramid = new Location("Pyramid");
        
        pyramidFairyLeft = new Location("Pyramid Fairy - Left");
        pyramidFairyRight = new Location("Pyramid Fairy - Right");
        
        catfish = new Location("Catfish");
        
        spikeCave = new Location("Spike Cave");
        
        superbunnyCaveTop = new Location("Superbunny Cave - Top");
        superbunnyCaveBottom = new Location("Superbunny Cave - Bottom");
        
        hookshotCaveTopRight = new Location("Hookshot Cave - Top Right");
        hookshotCaveTopLeft = new Location("Hookshot Cave - Top Left");
        hookshotCaveBottomLeft = new Location("Hookshot Cave - Bottom Left");
        hookshotCaveBottomRight = new Location("Hookshot Cave - Bottom Right");
        
        this.lightWorld = lightWorld;
        this.deathMountain = deathMountain;
        this.agahnim = agahnim;
    }
    
    /**
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if(closed(inventory)) {
            if(logicPyramidNoPearl(inventory))
                inLogic.add(pyramid);
            return inLogic;
        }
        
        //Check the logic of each location (grouped by area)
        //And add them to the list if they are in logic
        if(northWestDarkAccess(inventory))
            inLogic.addAll(logicNorthWestDark(inventory));
        
        if(southDarkAccess(inventory))
            inLogic.addAll(logicSouthDark(inventory));
        
        if(mireAreaAccess(inventory))
            inLogic.addAll(logicMireArea(inventory));
        
        if(eastDarkAccess(inventory))
            inLogic.addAll(logicEastDark(inventory));
        
        if(logicSpikeCave(inventory))
            inLogic.add(spikeCave);
        
        inLogic.addAll(logicDarkDeathMountain(inventory));
        
        return inLogic;
        
        /**
         * The Pyramid Fairy Locations will be checked by AreaSet 
         * and their own method due to special requirements
         * (The requirements aren't just an inventory item)
         */
    }
    
    /**
     * Check to see if there is a way to enter the dark world as Link
     * The Dark World can be entered as Link if the Moon Peal is obtained
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory){
        return !inventory.getItem(Item.MOON_PEARL).isOwned();
    }
    
    /**
     * Check to see if the pyramid is in logic without the pearl
     * It's in logic if it's not obtained and Agahnim is defeated
     * @param inventory The current inventory (unused)
     * @return True or False if the pyramid item is in logic
     */
    private boolean logicPyramidNoPearl(Inventory inventory) {
        if (!pyramid.isAcquired())
            if (agahnim.isAcquired())
                return true;
        
        return false;   
    }
    
    /**
     * Check to see if North West Dark World is reachable
     * Public Method so that it can be used by SkullWoods and ThievesTown
     * North West Dark World is open if either:
     * 1) Agahnim is defeated and the hookshot is acquired
     * 2) The Hammer and Power Gloves are acquired
     * 3) The Titan's Mitts are acquired
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean northWestDarkAccess(Inventory inventory) {
        
        if (closed(inventory))
            return false;
        
        if (agahnim.isAcquired() && inventory.getItem(Item.HOOKSHOT).isOwned())
            return true;
        
        if (inventory.getItem(Item.HAMMER).isOwned() &&
                inventory.getItem(Gloves.GLOVES).isOwned())
            return true;
        
        return inventory.getItem(Gloves.GLOVES).getDescription()
                .equals(Gloves.TITANS_MITTS);
    }

    private ArrayList<Location> logicNorthWestDark(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!bumperCave.isAcquired() && 
                inventory.getItem(Item.MIRROR).isOwned())
            inLogic.add(bumperCave);
  
        if (!cShapedHouse.isAcquired())
            inLogic.add(cShapedHouse);
   
        if (!chestGame.isAcquired())
            inLogic.add(chestGame);
      
        if (!brewery.isAcquired())
            inLogic.add(brewery);
        
        if (inventory.getItem(Gloves.GLOVES).getDescription()
                .equals(Gloves.TITANS_MITTS)) {
            
            if (inventory.getItem(Item.HAMMER).isOwned())
                if (!hammerPegs.isAcquired())
                    inLogic.add(hammerPegs);
            
            if (!blacksmith.isAcquired())
                inLogic.add(blacksmith);
            
            if (!purpleChest.isAcquired() && blacksmith.isAcquired())
                inLogic.add(purpleChest);
        }
        
        return inLogic;
    }
    
    /**
     * Check to see if South Dark World is reachable
     * Public Method so that it can be used by SwampPalace and IcePalace
     * South Dark World is open if either:
     * 1) Agahnim is defeated and the Hammer or Hookshot is acquired
     * 2) The Hammer and Power Gloves are acquired
     * 3) The Titan's Mitts are acquired
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean southDarkAccess(Inventory inventory) {
        
        if (closed(inventory))
            return false;
        
        if (agahnim.isAcquired()) {
            if (inventory.getItem(Item.HAMMER).isOwned())
                return true;
            if (inventory.getItem(Item.HOOKSHOT).isOwned())
                return true;
        }
        
        if (inventory.getItem(Item.HAMMER).isOwned() &&
                inventory.getItem(Gloves.GLOVES).isOwned())
            return true;
        
        return inventory.getItem(Gloves.GLOVES).getDescription()
                .equals(Gloves.TITANS_MITTS);
    }

    private ArrayList<Location> logicSouthDark(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!diggingGame.isAcquired())
            inLogic.add(diggingGame);
   
        if (!stumpy.isAcquired())
            inLogic.add(stumpy);
      
        if (!hypeCaveTop.isAcquired())
            inLogic.add(hypeCaveTop);

        if (!hypeCaveMiddleRight.isAcquired())
            inLogic.add(hypeCaveMiddleRight);
   
        if (!hypeCaveMiddleLeft.isAcquired())
            inLogic.add(hypeCaveMiddleLeft);
 
        if (!hypeCaveBottom.isAcquired())
            inLogic.add(hypeCaveBottom);
        
        if (!hypeCaveNPC.isAcquired())
            inLogic.add(hypeCaveNPC);

        return inLogic;
    }    
    
    /**
     * Check to see if Mire Area is reachable
     * Public Method so that it can be used by MiseryMire
     * Mire Area is open if:
     * 1) The Flute is acquired
     * 2) The Titan's Mitts are acquired
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean mireAreaAccess(Inventory inventory) {  
        
        if (closed(inventory))
            return false;
        
        if (inventory.getItem(Item.FLUTE).isOwned())
            return true;
        
        return inventory.getItem(Gloves.GLOVES).getDescription()
                .equals(Gloves.TITANS_MITTS);
    }

    private ArrayList<Location> logicMireArea(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!mireShedLeft.isAcquired())
            inLogic.add(mireShedLeft);
   
        if (!mireShedRight.isAcquired())
            inLogic.add(mireShedRight);

        return inLogic;
    }   
    
    /**
     * Check to see if East Dark World is reachable
     * Public Method so that it can be used by PalaceOfDarkness
     * East Dark World is open if:
     * 1) Agahnim is defeated
     * 2) The Hammer and Power Gloves are acquired
     * 2) The Titan's Mitts and Flippers
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean eastDarkAccess(Inventory inventory) {      
        
        if (closed(inventory))
            return false;
        
        if (agahnim.isAcquired())
            return true;
        
        if (inventory.getItem(Item.HAMMER).isOwned() &&
                inventory.getItem(Gloves.GLOVES).isOwned())
            return true;
        
        return (inventory.getItem(Gloves.GLOVES).getDescription()
                .equals(Gloves.TITANS_MITTS) &&
                inventory.getItem(Item.FLIPPERS).isOwned());
    }

    private ArrayList<Location> logicEastDark(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!pyramid.isAcquired())
            inLogic.add(pyramid);
   
        if (!catfish.isAcquired())
            inLogic.add(catfish);

        return inLogic;
    }    
      
    /**
     * Check to see if the spike cave's item is in logic
     * It's in logic if it's not obtained and:
     * 1) The Hammer is acquired
     * 2) Power Gloves are acquired
     * 3) An invincibility item is acquired with a bottle for Magic Refill
     *    - Cane of Byrna
     *    - Magic Cape    
     * @param inventory The current inventory
     * @return True or False if the location is in logic
     */
    private boolean logicSpikeCave(Inventory inventory) {
        if (!inventory.getItem(Item.HAMMER).isOwned())
            return false;
        
        if (!inventory.getItem(Gloves.GLOVES).isOwned())
            return false;
        
        if (!inventory.getItem(Bottles.BOTTLES).isOwned())
            return false;
        
        return inventory.getItem(Item.BYRNA).isOwned() || 
                inventory.getItem(Item.CAPE).isOwned();   
    }
    
    /**
    public ArrayList<Location> logicPyramidFairy(Inventory inventory, 
            Reward crystal5, Reward crystal6) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (crystal5.isAcquired() && crystal6.isAcquired() && 
                southDarkAccess(inventory)) { 
            if (inventory.getItem(Item.HAMMER).isOwned() ||
                    (inventory.getItem(Item.MIRROR).isOwned() &&
                    agahnim.isAcquired())) {
                inLogic.add(pyramidFairyLeft);
                inLogic.add(pyramidFairyRight);
            }
        }
        
        return inLogic;
    }
    */
}