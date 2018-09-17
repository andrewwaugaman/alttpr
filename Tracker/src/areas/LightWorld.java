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
public class LightWorld extends Area {
    
    //Light world has 50 possible item locations
    private final Location kakarikoWellTop;
    private final Location kakarikoWellLeft;
    private final Location kakarikoWellMiddle;
    private final Location kakarikoWellRight;
    private final Location kakarikoWellBottom;  
    
    private final Location blindsHideoutTop;
    private final Location blindsHideoutLeft;
    private final Location blindsHideoutRight;
    private final Location blindsHideoutFarLeft;
    private final Location blindsHideoutFarRight;    
    
    private final Location bottleMerchant;
    private final Location chickenHouse;
    private final Location sickKid;
    private final Location kakarikoTavern;

    private final Location magicBat;
    
    private final Location library;
    private final Location mazeRace;

    private final Location fluteSpot;  
    private final Location cave45;
    private final Location linksHouse;
    
    private final Location desertLedge;
    private final Location checkerboardCave;
    private final Location aginahscave;
    private final Location bombosTablet;
    
    private final Location sunkenTreasure;
    private final Location floodgateChest;   
    
    private final Location miniMoldormCaveFarLeft;
    private final Location miniMoldormCaveLeft;
    private final Location miniMoldormCaveRight;
    private final Location miniMoldormCaveFarRight;
    private final Location miniMoldormCaveNPC;
    
    private final Location iceRodCave;

    private final Location lakeHyliaIsland;
    
    private final Location hobo; 
    
    private final Location sahasrahlasHutLeft;
    private final Location sahasrahlasHutMiddle;
    private final Location sahasrahlasHutRight; 
    private final Location sahasrahla; 
    
    private final Location potionShop; 
    
    private final Location waterfallFairyLeft;
    private final Location waterfallFairyRight;
    private final Location kingZora;
    private final Location zorasLedge;
        
    private final Location kingsTomb;
    private final Location graveyardLedge;   
    private final Location pegasusRocks;
  
    private final Location lumberjackTree;
    
    private final Location lostWoodsHideout;
    private final Location mushroom;
    
    private final Location masterSwordPedestal;

    //Name of the area
    public final static String NAME = "Light World";
    
    public LightWorld(){
                
        kakarikoWellTop = new Location("Kakariko Well - Top");
        kakarikoWellLeft = new Location("Kakariko Well - Left");
        kakarikoWellMiddle = new Location("Kakariko Well - Middle");
        kakarikoWellRight = new Location("Kakariko Well - Right");
        kakarikoWellBottom = new Location("Kakariko Well - Bottom");
        
        blindsHideoutTop = new Location("Blind's Hideout - Top");
        blindsHideoutLeft = new Location("Blind's Hideout - Left");
        blindsHideoutRight = new Location("Blind's Hideout - Right");
        blindsHideoutFarLeft = new Location("Blind's Hideout - Far Left");
        blindsHideoutFarRight = new Location("Blind's Hideout - Far Right");
        
        bottleMerchant = new Location("Bottle Merchant");
        chickenHouse = new Location("Chicken House");
        sickKid = new Location("Sick Kid");
        kakarikoTavern = new Location("Kakariko Tavern");

        magicBat = new Location("Magic Bat");
    
        library = new Location("Library");
        mazeRace = new Location("Maze Race");

        fluteSpot = new Location("Flute Spot");
        cave45 = new Location("Cave 45");
        linksHouse = new Location("Link's House");
    
        desertLedge = new Location("Desert Ledge");
        checkerboardCave = new Location("Checkerboard Cave");
        aginahscave = new Location("Aginah's Cave");
        bombosTablet = new Location("Bombos Tablet");
    
        sunkenTreasure = new Location("Sunken Treasure");
        floodgateChest = new Location("Floodgate Chest");  
        
        miniMoldormCaveFarLeft = new Location("Mini Moldorm Cave - Far Left");
        miniMoldormCaveLeft = new Location("Mini Moldorm Cave - Left");
        miniMoldormCaveRight = new Location("Mini Moldorm Cave - Right");
        miniMoldormCaveFarRight = new Location("Mini Moldorm Cave - Far Right");
        miniMoldormCaveNPC = new Location("Mini Moldorm Cave - NPC"); 
        
        iceRodCave = new Location("Ice Rod Cave");

        lakeHyliaIsland = new Location("Lake Hylia Island");
        
        hobo = new Location("Hobo");
        
        sahasrahlasHutLeft = new Location("Sahasrahla's Hut - Left");
        sahasrahlasHutMiddle = new Location("Sahasrahla's Hut - Middle");
        sahasrahlasHutRight = new Location("Sahasrahla's Hut - Right");
        sahasrahla = new Location("Sahasrahla");
        
        potionShop = new Location("Potion Shop");
        
        waterfallFairyLeft = new Location("Waterfall Fairy - Left");
        waterfallFairyRight = new Location("Waterfall Fairy - Right");
        kingZora = new Location("King Zora");
        zorasLedge = new Location("Zora's Ledge");
    
        kingsTomb = new Location("King's Tomb");
        graveyardLedge = new Location("Graveyard Ledge");
        pegasusRocks = new Location("Pegasus Rocks");
  
        lumberjackTree = new Location("Lumberjack Tree");
    
        lostWoodsHideout = new Location("Lost Woods Hideout");
        mushroom = new Location("Mushroom");
    
        masterSwordPedestal = new Location("Master Sword Pedestal");        
    }

    public ArrayList<Location> locationsInLogic (Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
        
        //Check the logic of each location (grouped by area)
        //And add them to the list if they are in logic
        inLogic.addAll(logicKakariko(inventory));
        
        if(logicFluteSpot(inventory))
            inLogic.add(fluteSpot);
        if(logicLinksHouse(inventory))
            inLogic.add(linksHouse);
        
        inLogic.addAll(logicDesert(inventory));
        
        inLogic.addAll(logicSouthShore(inventory)); 
        
        inLogic.addAll(logicEastHyrule(inventory));
        
        inLogic.addAll(logicZorasDomain(inventory));
        
        if(logicPegasusRocks(inventory))
            inLogic.add(pegasusRocks);
        
        if(logicLostWoodsHideout(inventory))
            inLogic.add(lostWoodsHideout);
        if(logicMushroom(inventory))
            inLogic.add(mushroom);
        
        return inLogic;
        
        /**
         * The Following Locations will be checked by AreaSet 
         * and their own method due to special requirements
         * (The requirements aren't just an inventory item)
         * 1) Magic Bat
         * 2) Cave 45
         * 3) Bombos Tablet
         * 4) Lake Hylia Island
         * 5) Sahasrahla
         * 6) King's Tomb
         * 7) Graveyard Ledge
         * 8) Lumberjack Tree
         * 9) Master Sword Pedestal
         */
    }
    
    private ArrayList<Location> logicKakariko(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
               
        if (!kakarikoWellTop.isAcquired())
            inLogic.add(kakarikoWellTop);
        if (!kakarikoWellLeft.isAcquired())
            inLogic.add(kakarikoWellLeft);
        if (!kakarikoWellMiddle.isAcquired())
            inLogic.add(kakarikoWellMiddle);
        if (!kakarikoWellRight.isAcquired())
            inLogic.add(kakarikoWellRight);
        if (!kakarikoWellBottom.isAcquired())
            inLogic.add(kakarikoWellBottom);
                
        if (!blindsHideoutTop.isAcquired())
            inLogic.add(blindsHideoutTop);
        if (!blindsHideoutLeft.isAcquired())
            inLogic.add(blindsHideoutLeft);
        if (!blindsHideoutRight.isAcquired())
            inLogic.add(blindsHideoutRight);
        if (!blindsHideoutFarLeft.isAcquired())
            inLogic.add(blindsHideoutFarLeft);
        if (!blindsHideoutFarRight.isAcquired())
            inLogic.add(blindsHideoutFarRight);
        
        if (!bottleMerchant.isAcquired())
            inLogic.add(bottleMerchant);
        if (!chickenHouse.isAcquired())
            inLogic.add(chickenHouse);
        if (!sickKid.isAcquired() && 
                inventory.getItem(Bottles.BOTTLES).isOwned())
            inLogic.add(sickKid);
        if (!kakarikoTavern.isAcquired())
            inLogic.add(kakarikoTavern);
        
        if (!library.isAcquired() && inventory.getItem(Item.BOOTS).isOwned())
            inLogic.add(library);
        if (!mazeRace.isAcquired())
            inLogic.add(mazeRace);
        
        return inLogic;
    }
    
    private boolean logicFluteSpot(Inventory inventory){
        if (fluteSpot.isAcquired())
            return false;
        
        return inventory.getItem(Item.SHOVEL).isOwned();
    }
    
    private boolean logicLinksHouse(Inventory inventory){
        return !linksHouse.isAcquired();
    }
    
    private ArrayList<Location> logicDesert(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
               
        //Access through dark world
        if (inventory.getItem(Item.FLUTE).isOwned() &&
                ((Gloves)(inventory.getItem(Gloves.GLOVES))).getLevel() == 2) {
            if(!desertLedge.isAcquired())
                inLogic.add(desertLedge);
            if(!checkerboardCave.isAcquired())
                inLogic.add(checkerboardCave);
        }
        else
            if(inventory.getItem(Item.BOOK).isOwned())
                if(!desertLedge.isAcquired())
                    inLogic.add(desertLedge);
        
        if(!aginahscave.isAcquired())
            inLogic.add(aginahscave);
            
        return inLogic;
    }
    
    private ArrayList<Location> logicSouthShore(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
               
        if (!sunkenTreasure.isAcquired())
            inLogic.add(sunkenTreasure);
        if (!floodgateChest.isAcquired())
            inLogic.add(floodgateChest);
        
        if (!miniMoldormCaveFarLeft.isAcquired())
            inLogic.add(miniMoldormCaveFarLeft);
        if (!miniMoldormCaveLeft.isAcquired())
            inLogic.add(miniMoldormCaveLeft);
        if (!miniMoldormCaveRight.isAcquired())
            inLogic.add(miniMoldormCaveRight);
        if (!miniMoldormCaveFarRight.isAcquired())
            inLogic.add(miniMoldormCaveFarRight);                
        if (!miniMoldormCaveNPC.isAcquired())
            inLogic.add(miniMoldormCaveNPC);
        
        if (!iceRodCave.isAcquired())
            inLogic.add(iceRodCave);
        
        return inLogic;
    }
    
    private ArrayList<Location> logicEastHyrule(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
               
        if (!hobo.isAcquired() && inventory.getItem(Item.FLIPPERS).isOwned())
            inLogic.add(sunkenTreasure);
        
        if (!sahasrahlasHutLeft.isAcquired())
            inLogic.add(sahasrahlasHutLeft);
        if (!sahasrahlasHutMiddle.isAcquired())
            inLogic.add(sahasrahlasHutMiddle);
        if (!sahasrahlasHutRight.isAcquired())
            inLogic.add(sahasrahlasHutRight);
           
        if (!potionShop.isAcquired())
            inLogic.add(potionShop);
        
        return inLogic;
    }
    
    private ArrayList<Location> logicZorasDomain(Inventory inventory){
        ArrayList<Location> inLogic = new ArrayList();
        
        if (!waterfallFairyLeft.isAcquired() &&
                inventory.getItem(Item.FLIPPERS).isOwned())
            inLogic.add(waterfallFairyLeft);
        if (!waterfallFairyRight.isAcquired() &&
                inventory.getItem(Item.FLIPPERS).isOwned())
            inLogic.add(waterfallFairyRight);
        
        if (!kingZora.isAcquired() && 
                (inventory.getItem(Item.FLIPPERS).isOwned() || 
                inventory.getItem(Gloves.GLOVES).isOwned()))
            inLogic.add(kingZora);

        if (!zorasLedge.isAcquired() &&
                inventory.getItem(Item.FLIPPERS).isOwned())
            inLogic.add(zorasLedge);
        
        return inLogic;
    }
    
    private boolean logicPegasusRocks(Inventory inventory){
        if (pegasusRocks.isAcquired())
            return false;
        
        return inventory.getItem(Item.BOOTS).isOwned();
    }
    
    private boolean logicLostWoodsHideout(Inventory inventory){
        return !lostWoodsHideout.isAcquired();
    }  
    
    private boolean logicMushroom(Inventory inventory){
        return !mushroom.isAcquired();
    }    
    
    /**
    public Location logicMagicBat(Inventory inventory, DarkWorld darkWorld){
        if (!magicBat.isAcquired()) {
            if (inventory.getItem(Item.HAMMER).isOwned() && 
                    inventory.getItem(Item.POWDER).isOwned())
                return magicBat;
            if (darkWorld.northEastAccess(inventory) && 
                    inventory.getItem(Item.MIRROR).isOwned() &&
                    inventory.getItem(Item.POWDER).isOwned())
                return magicBat;
        }
        
        return null;
    }
        
    public Location logicCave45(Inventory inventory, DarkWorld darkWorld){
        if (!cave45.isAcquired())
            if (darkWorld.southAccess(inventory) && 
                    inventory.getItem(Item.MIRROR).isOwned())
                return cave45;
            
        return null;
    }
    
    public Location logicBombosTable(Inventory inventory, DarkWorld darkWorld){
        if (!bombosTablet.isAcquired())
            if (darkWorld.southAccess(inventory) &&
                    inventory.getItem(Item.MIRROR).isOwned() &&
                    inventory.getItem(Item.BOOK).isOwned() &&
                    ((Sword)(inventory.getItem(Sword.SWORD))).getLevel() >= 2)
                return bombosTablet;
        
        return null;
    }
    
    public Location logicLakeHyliaIsland(Inventory inventory, 
            DarkWorld darkWorld){
        if (!lakeHyliaIsland.isAcquired())
            if (!darkWorld.isClosed(inventory) && 
                    inventory.getItem(Item.MIRROR).isOwned() &&
                    inventory.getItem(Item.FLIPPERS).isOwned())
            return lakeHyliaIsland;
        
        return null;
    }
    
    public Location logicSahasrahla(Reward greenPendant){
        if (!sahasrahla.isAcquired())
            if (greenPendant.isAcquired())
                return sahasrahla;
        
        return null;     
    }
    
    public Location logicKingsTomb(Inventory inventory, DarkWorld darkWorld){
        if (!kingsTomb.isAcquired()) {
            if (inventory.getItem(Item.BOOTS).isOwned() && 
                    ((Gloves)(inventory.getItem(Gloves.GLOVES))).getLevel()
                    == 2)
                return kingsTomb;
            if (darkWorld.northEastAccess(inventory) && 
                    inventory.getItem(Item.MIRROR).isOwned() &&
                    inventory.getItem(Item.BOOTS).isOwned())
                return kingsTomb;
        }
        
        return null;
    }
    
    public Location logicGraveyardLedge(Inventory inventory,
            DarkWorld darkWorld){
        if (!graveyardLedge.isAcquired())
            if (darkWorld.northEastAccess(inventory) && 
                    inventory.getItem(Item.MIRROR).isOwned())
                return graveyardLedge;
            
        return null;
    }
    
    public Location logicLumberjackTree(Inventory inventory, 
            HyruleCastle hyruleCastle){
        if (!lumberjackTree.isAcquired())
            if (hyruleCastle.isAgahnimDefeated() &&
                    inventory.getItem(Item.BOOTS).isOwned())
                return lumberjackTree;
        
        return null;
    }
    
    public Location logicMasterSwordPedestal(Reward greenPendant, 
            Reward bluePendant, Reward redPendant){
        if (!masterSwordPedestal.isAcquired())
            if (greenPendant.isAcquired() && bluePendant.isAcquired() &&
                    redPendant.isAcquired())
                return masterSwordPedestal;
        
        return null;
    }
    */
}
