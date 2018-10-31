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
    private final Location aginahsCave;
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
    
    /**
     * Constructor Method
     * Instantiate the 50 locations with their description
     */
    public LightWorld() {
                
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
        aginahsCave = new Location("Aginah's Cave");
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

    /**
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic (Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        //Check the logic of each location (grouped by area)
        //And add them to the list if they are in logic
        inLogic.addAll(logicKakariko(inventory));
        
        if (logicFluteSpot(inventory))
            inLogic.add(fluteSpot);
        if (logicLinksHouse(inventory))
            inLogic.add(linksHouse);
        
        inLogic.addAll(logicDesert(inventory));
        
        inLogic.addAll(logicSouthShore(inventory)); 
        
        inLogic.addAll(logicEastHyrule(inventory));
        
        inLogic.addAll(logicZorasDomain(inventory));
        
        if (logicPegasusRocks(inventory))
            inLogic.add(pegasusRocks);
        
        if (logicLostWoodsHideout(inventory))
            inLogic.add(lostWoodsHideout);
        if (logicMushroom(inventory))
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
    
    /**
     * Get the locations that are currently in logic in the Kakariko area
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicKakariko(Inventory inventory) {
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
    
    /**
     * Check to see if the flute spot is in logic
     * It's in logic if it's not opened and the shovel is acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicFluteSpot(Inventory inventory) {
        if (fluteSpot.isAcquired())
            return false;
        
        return inventory.getItem(Item.SHOVEL).isOwned();
    }
    
    /**
     * Check to see if the chest in Link's House is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicLinksHouse(Inventory inventory) {
        return !linksHouse.isAcquired();
    }
    
    /**
     * Get the locations that are currently in logic in the desert
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicDesert(Inventory inventory) {
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
            if (inventory.getItem(Item.BOOK).isOwned())
                if (!desertLedge.isAcquired())
                    inLogic.add(desertLedge);
        
        if (!aginahsCave.isAcquired())
            inLogic.add(aginahsCave);
            
        return inLogic;
    }
    
    /**
     * Get the locations that are currently in logic in the south shore
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicSouthShore(Inventory inventory) {
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
    
    /**
     * Get the locations that are currently in logic in the east Hyrule area
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicEastHyrule(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
               
        if (!hobo.isAcquired() && inventory.getItem(Item.FLIPPERS).isOwned())
            inLogic.add(sunkenTreasure);
        
        if (!sahasrahlasHutLeft.isAcquired())
            inLogic.add(sahasrahlasHutLeft);
        if (!sahasrahlasHutMiddle.isAcquired())
            inLogic.add(sahasrahlasHutMiddle);
        if (!sahasrahlasHutRight.isAcquired())
            inLogic.add(sahasrahlasHutRight);
           
        if (!potionShop.isAcquired() && 
                inventory.getItem(Item.MUSHROOM).isOwned())
            inLogic.add(potionShop);
        
        return inLogic;
    }
    
    /**
     * Get the locations that are currently in logic in Zora's Domain
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
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
    
    /**
     * Check to see if the pegasus rock's chest is in logic
     * It's in logic if it's not opened and the boots are acquired
     * @param inventory The current inventory 
     * @return True or False if the chest is in logic
     */
    private boolean logicPegasusRocks(Inventory inventory) {
        if (pegasusRocks.isAcquired())
            return false;
        
        return inventory.getItem(Item.BOOTS).isOwned();
    }
    
    /**
     * Check to see if the lost woods item is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
    private boolean logicLostWoodsHideout(Inventory inventory){
        return !lostWoodsHideout.isAcquired();
    }  
    
    /**
     * Check to see if the mushroom spot is in logic
     * It's in logic if it's not opened
     * @param inventory The current inventory (unused)
     * @return True or False if the chest is in logic
     */
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
                    inventory.getItem(Gloves.GLOVES).getDescription()
                    .equals(Gloves.TITANS_MITTS))
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
    
    //Getters and Setters for the locations below
    
    /**
     * @return the kakarikoWellTop
     */
    public Location getKakarikoWellTop() {
        return kakarikoWellTop;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setKakarikoWellTop(Item contents) {
        kakarikoWellTop.setContents(contents);
    }

    /**
     * @return the kakarikoWellLeft
     */
    public Location getKakarikoWellLeft() {
        return kakarikoWellLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setKakarikoWellLeft(Item contents) {
        kakarikoWellLeft.setContents(contents);
    }

    /**
     * @return the kakarikoWellMiddle
     */
    public Location getKakarikoWellMiddle() {
        return kakarikoWellMiddle;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setKakarikoWellMiddle(Item contents) {
        kakarikoWellMiddle.setContents(contents);
    }
    
    /**
     * @return the kakarikoWellRight
     */
    public Location getKakarikoWellRight() {
        return kakarikoWellRight;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setKakarikoWellRight(Item contents) {
        kakarikoWellRight.setContents(contents);
    }
    
    /**
     * @return the kakarikoWellBottom
     */
    public Location getKakarikoWellBottom() {
        return kakarikoWellBottom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setKakarikoWellBottom(Item contents) {
        kakarikoWellBottom.setContents(contents);
    }

    /**
     * @return the blindsHideoutTop
     */
    public Location getBlindsHideoutTop() {
        return blindsHideoutTop;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setBlindsHideoutTop(Item contents) {
        blindsHideoutTop.setContents(contents);
    }
    
    /**
     * @return the blindsHideoutLeft
     */
    public Location getBlindsHideoutLeft() {
        return blindsHideoutLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBlindsHideoutLeft(Item contents) {
        blindsHideoutLeft.setContents(contents);
    }

    /**
     * @return the blindsHideoutRight
     */
    public Location getBlindsHideoutRight() {
        return blindsHideoutRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBlindsHideoutRight(Item contents) {
        blindsHideoutRight.setContents(contents);
    }

    /**
     * @return the blindsHideoutFarLeft
     */
    public Location getBlindsHideoutFarLeft() {
        return blindsHideoutFarLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBlindsHideoutFarLeft(Item contents) {
        blindsHideoutFarLeft.setContents(contents);
    }

    /**
     * @return the blindsHideoutFarRight
     */
    public Location getBlindsHideoutFarRight() {
        return blindsHideoutFarRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setBlindsHideoutFarRight(Item contents) {
        blindsHideoutFarRight.setContents(contents);
    }

    /**
     * @return the bottleMerchant
     */
    public Location getBottleMerchant() {
        return bottleMerchant;
    }
    
    /**
     * @param contents The Bottle Merchant's new item
     */
    public void setBottleMerchant(Item contents) {
        bottleMerchant.setContents(contents);
    }

    /**
     * @return the chickenHouse
     */
    public Location getChickenHouse() {
        return chickenHouse;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setChickenHouse(Item contents) {
        chickenHouse.setContents(contents);
    }

    /**
     * @return the sickKid
     */
    public Location getSickKid() {
        return sickKid;
    }

    /**
     * @param contents Sick Kid's new item
     */
    public void setSickKid(Item contents) {
        sickKid.setContents(contents);
    }
    
    /**
     * @return the kakarikoTavern
     */
    public Location getKakarikoTavern() {
        return kakarikoTavern;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setKakarikoTavern(Item contents) {
        kakarikoTavern.setContents(contents);
    }

    /**
     * @return the magicBat
     */
    public Location getMagicBat() {
        return magicBat;
    }

    /**
     * @param contents The new contents of the magic bat
     */
    public void setMagicBat(Item contents) {
        magicBat.setContents(contents);
    }
    
    /**
     * @return the library
     */
    public Location getLibrary() {
        return library;
    }
    
    /**
     * @param contents The new contents of the library
     */
    public void setLibrary(Item contents) {
        library.setContents(contents);
    }

    /**
     * @return the mazeRace
     */
    public Location getMazeRace() {
        return mazeRace;
    }
    
    /**
     * @param contents The new contents of the maze race
     */
    public void setMazeRace(Item contents) {
        mazeRace.setContents(contents);
    }

    /**
     * @return the fluteSpot
     */
    public Location getFluteSpot() {
        return fluteSpot;
    }
    
    /**
     * @param contents The new contents of the flute spot
     */
    public void setFluteSpot(Item contents) {
        fluteSpot.setContents(contents);
    }

    /**
     * @return the cave45
     */
    public Location getCave45() {
        return cave45;
    }
    
    /**
     * @param contents The new contents of cave45
     */
    public void setCave45(Item contents) {
        cave45.setContents(contents);
    }

    /**
     * @return the linksHouse
     */
    public Location getLinksHouse() {
        return linksHouse;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setLinksHouse(Item contents) {
        linksHouse.setContents(contents);
    }

    /**
     * @return the desertLedge
     */
    public Location getDesertLedge() {
        return desertLedge;
    }
    
    /**
     * @param contents The new contents of the desert ledge
     */
    public void setDesertLedge(Item contents) {
        desertLedge.setContents(contents);
    }

    /**
     * @return the checkerboardCave
     */
    public Location getCheckerboardCave() {
        return checkerboardCave;
    }
    
    /**
     * @param contents The new contents of the checkerboard cave
     */
    public void setCheckerboardCave(Item contents) {
        checkerboardCave.setContents(contents);
    }

    /**
     * @return the aginahscave
     */
    public Location getAginahsCave() {
        return aginahsCave;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setAginahsCave(Item contents) {
        aginahsCave.setContents(contents);
    }

    /**
     * @return the bombosTablet
     */
    public Location getBombosTablet() {
        return bombosTablet;
    }
    
    /**
     * @param contents The new contents of the tablet
     */
    public void setBombosTablet(Item contents) {
        bombosTablet.setContents(contents);
    }

    /**
     * @return the sunkenTreasure
     */
    public Location getSunkenTreasure() {
        return sunkenTreasure;
    }
    
    /**
     * @param contents The new contents of the sunken treasure
     */
    public void setSunkenTreasure(Item contents) {
        sunkenTreasure.setContents(contents);
    }

    /**
     * @return the floodgateChest
     */
    public Location getFloodgateChest() {
        return floodgateChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setFloodgateChest(Item contents) {
        floodgateChest.setContents(contents);
    }

    /**
     * @return the miniMoldormCaveFarLeft
     */
    public Location getMiniMoldormCaveFarLeft() {
        return miniMoldormCaveFarLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setMiniMoldormCaveFarLeft(Item contents) {
        miniMoldormCaveFarLeft.setContents(contents);
    }

    /**
     * @return the miniMoldormCaveLeft
     */
    public Location getMiniMoldormCaveLeft() {
        return miniMoldormCaveLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setMiniMoldormCaveLeft(Item contents) {
        miniMoldormCaveLeft.setContents(contents);
    }

    /**
     * @return the miniMoldormCaveRight
     */
    public Location getMiniMoldormCaveRight() {
        return miniMoldormCaveRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setMiniMoldormCaveRight(Item contents) {
        miniMoldormCaveRight.setContents(contents);
    }

    /**
     * @return the miniMoldormCaveFarRight
     */
    public Location getMiniMoldormCaveFarRight() {
        return miniMoldormCaveFarRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void miniMoldormCaveFarRight(Item contents) {
        miniMoldormCaveFarRight.setContents(contents);
    }

    /**
     * @return the miniMoldormCaveNPC
     */
    public Location getMiniMoldormCaveNPC() {
        return miniMoldormCaveNPC;
    }
    
    /**
     * @param contents The NPC's new item
     */
    public void setMiniMoldormCaveNPC(Item contents) {
        miniMoldormCaveNPC.setContents(contents);
    }

    /**
     * @return the iceRodCave
     */
    public Location getIceRodCave() {
        return iceRodCave;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setIceRodCave(Item contents) {
        iceRodCave.setContents(contents);
    }
    
    /**
     * @return the lakeHyliaIsland
     */
    public Location getLakeHyliaIsland() {
        return lakeHyliaIsland;
    }
    
    /**
     * @param contents The new contents of Lake Hylia Island
     */
    public void setLakeHyliaIsland(Item contents) {
        lakeHyliaIsland.setContents(contents);
    }

    /**
     * @return the hobo
     */
    public Location getHobo() {
        return hobo;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHobo(Item contents) {
        hobo.setContents(contents);
    }

    /**
     * @return the sahasrahlasHutLeft
     */
    public Location getSahasrahlasHutLeft() {
        return sahasrahlasHutLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setSahasrahlasHutLeft(Item contents) {
        sahasrahlasHutLeft.setContents(contents);
    }

    /**
     * @return the sahasrahlasHutMiddle
     */
    public Location getSahasrahlasHutMiddle() {
        return sahasrahlasHutMiddle;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setSahasrahlasHutMiddle(Item contents) {
        sahasrahlasHutMiddle.setContents(contents);
    }

    /**
     * @return the sahasrahlasHutRight
     */
    public Location getSahasrahlasHutRight() {
        return sahasrahlasHutRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setSahasrahlasHutRight(Item contents) {
        sahasrahlasHutRight.setContents(contents);
    }

    /**
     * @return Sahasrahla
     */
    public Location getSahasrahla() {
        return sahasrahla;
    }
    
    /**
     * @param contents Sahasrahla's new item
     */
    public void setSahasrahla(Item contents) {
        sahasrahla.setContents(contents);
    }

    /**
     * @return the potionShop
     */
    public Location getPotionShop() {
        return potionShop;
    }
    
    /**
     * @param contents The new contents of the potion shop
     */
    public void setPotionShop(Item contents) {
        potionShop.setContents(contents);
    }

    /**
     * @return the waterfallFairyLeft
     */
    public Location getWaterfallFairyLeft() {
        return waterfallFairyLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setWaterfallFairyLeft(Item contents) {
        waterfallFairyLeft.setContents(contents);
    }

    /**
     * @return the waterfallFairyRight
     */
    public Location getWaterfallFairyRight() {
        return waterfallFairyRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setWaterfallFairyRight(Item contents) {
        waterfallFairyRight.setContents(contents);
    }

    /**
     * @return the kingZora
     */
    public Location getKingZora() {
        return kingZora;
    }
    
    /**
     * @param contents The new item King Zora is holding
     */
    public void setKingZora(Item contents) {
        kingZora.setContents(contents);
    }

    /**
     * @return the zorasLedge
     */
    public Location getZorasLedge() {
        return zorasLedge;
    }
    
    /**
     * @param contents The new contents of Zora's Ledge
     */
    public void setZorasLedge(Item contents) {
        zorasLedge.setContents(contents);
    }

    /**
     * @return the kingsTomb
     */
    public Location getKingsTomb() {
        return kingsTomb;
    }
    
    /**
     * @param contents The new contents of King's tomb
     */
    public void setKingsTomb(Item contents) {
        kingsTomb.setContents(contents);
    }

    /**
     * @return the graveyardLedge
     */
    public Location getGraveyardLedge() {
        return graveyardLedge;
    }
    
    /**
     * @param contents The new contents of the graveyard ledge
     */
    public void setGraveyardLedge(Item contents) {
        graveyardLedge.setContents(contents);
    }

    /**
     * @return the pegasusRocks
     */
    public Location getPegasusRocks() {
        return pegasusRocks;
    }
    
    /**
     * @param contents The new contents of the Pegasus rocks
     */
    public void setPegasusRocks(Item contents) {
        pegasusRocks.setContents(contents);
    }

    /**
     * @return the lumberjackTree
     */
    public Location getLumberjackTree() {
        return lumberjackTree;
    }
    
    /**
     * @param contents The new contents of the lumberjack tree
     */
    public void setLumberjackTree(Item contents) {
        lumberjackTree.setContents(contents);
    }

    /**
     * @return the lostWoodsHideout
     */
    public Location getLostWoodsHideout() {
        return lostWoodsHideout;
    }
    
    /**
     * @param contents The new contents of the lost woods hideout
     */
    public void setLostWoodsHideout(Item contents) {
        lostWoodsHideout.setContents(contents);
    }

    /**
     * @return the mushroom
     */
    public Location getMushroom() {
        return mushroom;
    }
    
    /**
     * @param contents The new contents of the mushroom spot
     */
    public void setMushroom(Item contents) {
        mushroom.setContents(contents);
    }

    /**
     * @return the masterSwordPedestal
     */
    public Location getMasterSwordPedestal() {
        return masterSwordPedestal;
    }
    
    /**
     * @param contents The new contents of the pedestal
     */
    public void setMasterSwordPedestal(Item contents) {
        masterSwordPedestal.setContents(contents);
    }
}
