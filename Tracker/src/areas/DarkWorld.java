package areas;

import java.util.ArrayList;
import items.*;

/**
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
    
    private final Location catfish;
    
    private final Location spikeCave;
    
    private final Location superbunnyCaveTop;
    private final Location superbunnyCaveBottom;
    
    private final Location hookshotCaveTopRight;
    private final Location hookshotCaveTopLeft;
    private final Location hookshotCaveBottomLeft;
    private final Location hookshotCaveBottomRight;
    
    private final Location pyramidFairyLeft;
    private final Location pyramidFairyRight;
    
    //Used to check if Dark World is Open
    private final Portals portals;
    
    //Name of the area
    public final static String NAME = "Dark World";

    /**
     * Constructor Method
     * Instantiate the 27 locations with their description
     * @param deathMountain the Moutain in the light world
     * @param agahnim the reward from agahnim to see if the portal is open
     */
    public DarkWorld(DeathMountain deathMountain, Reward agahnim) {
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
        
        catfish = new Location("Catfish");
        
        spikeCave = new Location("Spike Cave");
        
        superbunnyCaveTop = new Location("Superbunny Cave - Top");
        superbunnyCaveBottom = new Location("Superbunny Cave - Bottom");
        
        hookshotCaveTopRight = new Location("Hookshot Cave - Top Right");
        hookshotCaveTopLeft = new Location("Hookshot Cave - Top Left");
        hookshotCaveBottomLeft = new Location("Hookshot Cave - Bottom Left");
        hookshotCaveBottomRight = new Location("Hookshot Cave - Bottom Right");
        
        pyramidFairyLeft = new Location("Pyramid Fairy - Left");
        pyramidFairyRight = new Location("Pyramid Fairy - Right");
        
        portals = new Portals(deathMountain, agahnim);
    }
    
    /**
     * Check to see if there is a way to enter the dark world as Link
     * The Dark World can be entered as Link if the Moon Peal is obtained
     * @return True or False if it's closed
     */
    private boolean closed(Inventory inventory) {
        return !inventory.getItem(KeyItem.MOON_PEARL).isOwned();
    }
    
    /**
     * Get the locations that are currently in logic
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    @Override
    public ArrayList<Location> locationsInLogic(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (closed(inventory)) {
            if(logicPyramidNoPearl(inventory))
                inLogic.add(pyramid);
            return inLogic;
        }
        
        //Check the logic of each location (grouped by area)
        //And add them to the list if they are in logic
        if (northWestDarkAccess(inventory))
            inLogic.addAll(logicNorthWestDark(inventory));
        
        if (southDarkAccess(inventory))
            inLogic.addAll(logicSouthDark(inventory));
        
        if (mireAreaAccess(inventory))
            inLogic.addAll(logicMireArea(inventory));
        
        if (eastDarkAccess(inventory))
            inLogic.addAll(logicEastDark(inventory));
        
        if (logicSpikeCave(inventory))
            inLogic.add(spikeCave);
        
        if (eastDarkDeathMountainAccess(inventory))
            inLogic.addAll(logicEastDarkDeathMountain(inventory));
        
        return inLogic;
        
        /**
         * The Pyramid Fairy Locations will be checked by AreaSet 
         * and their own method due to special requirements
         * (The requirements aren't just an inventory item)
         */
    }
    
    /**
     * Check to see if the pyramid is in logic without the pearl
     * It's in logic if it's not obtained and Agahnim is defeated
     * @param inventory The current inventory (unused)
     * @return True or False if the pyramid item is in logic
     */
    private boolean logicPyramidNoPearl(Inventory inventory) {
        if (!pyramid.isAcquired())
            if (portals.agahnimPortalOpen())
                return true;
        
        return false;   
    }
    
    /**
     * Check to see if North West Dark World is reachable
     * Public Method so that it can be used by SkullWoods and ThievesTown
     * North West Dark World is open if either:
     * 1) Agahnim is defeated and the hookshot is acquired
     * 2) The Kakariko Portal is open
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean northWestDarkAccess(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (portals.agahnimPortalOpen() && 
                inventory.getItem(KeyItem.HOOKSHOT).isOwned())
            return true;
        
        return portals.kakarikoPortalOpen(inventory);
    }

    /**
     * Get the locations that are currently in 
     * logic in the Northwest Dark world area
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicNorthWestDark(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!bumperCave.isAcquired() && 
                inventory.getItem(KeyItem.MIRROR).isOwned())
            inLogic.add(bumperCave);
  
        if (!cShapedHouse.isAcquired())
            inLogic.add(cShapedHouse);
   
        if (!chestGame.isAcquired())
            inLogic.add(chestGame);
      
        if (!brewery.isAcquired())
            inLogic.add(brewery);
        
        if (inventory.getItem(Gloves.GLOVES).getDescription()
                .equals(Gloves.TITANS_MITTS)) {   
            if (inventory.getItem(KeyItem.HAMMER).isOwned())
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
     * Public Method so that it can be used by SwampPalace
     * South Dark World is open if either:
     * 1) Agahnim is defeated and the Hammer or Hookshot is acquired
     * 2) The South Shore portal is open
     * 3) The Kakariko portal is open (Titan's Mitts)
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean southDarkAccess(Inventory inventory) {
        if (closed(inventory))
            return false;
        
        if (portals.agahnimPortalOpen()) {
            if (inventory.getItem(KeyItem.HAMMER).isOwned())
                return true;
            if (inventory.getItem(KeyItem.HOOKSHOT).isOwned())
                return true;
        }
        
        if (portals.southShorePortalOpen(inventory))
            return true;
        
        return portals.kakarikoPortalOpen(inventory);
    }

    /**
     * Get the locations that are currently in 
     * logic in the South Dark world area
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
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
     * Mire Area is open if the Mire Area portal is open
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean mireAreaAccess(Inventory inventory) {  
        return portals.mirePortalOpen(inventory);
    }

    /**
     * Get the locations that are currently in logic in the Mire area
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicMireArea(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!mireShedLeft.isAcquired())
            inLogic.add(mireShedLeft);
   
        if (!mireShedRight.isAcquired())
            inLogic.add(mireShedRight);

        return inLogic;
    }   
    
    /**
     * Check to see if Lake Hylia Dark World is reachable
     * Public Method so that it can be used by IcePalace
     * Lake Hylia Dark World is open if the Lake Hylia portal is open
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean lakeHyliaAccess(Inventory inventory) {
        return portals.lakeHyliaPortalOpen(inventory);
    }
    
    /**
     * Check to see if East Dark World is reachable
     * Public Method so that it can be used by PalaceOfDarkness
     * East Dark World is open if:
     * 1) Agahnim is defeated
     * 2) The East Hyrule portal is open
     * 3) The Kakariko portal is open and the flippers are acquired
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean eastDarkAccess(Inventory inventory) {      
        if (closed(inventory))
            return false;
        
        if (portals.eastHyrulePortalOpen(inventory))
            return true;
        
        return portals.kakarikoPortalOpen(inventory) &&
                inventory.getItem(KeyItem.FLIPPERS).isOwned();
    }

    /**
     * Get the locations that are currently in 
     * logic in the East Dark world area
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicEastDark(Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!pyramid.isAcquired())
            inLogic.add(pyramid);
   
        if (inventory.getItem(Gloves.GLOVES).isOwned())
            if (!catfish.isAcquired())
                inLogic.add(catfish);

        return inLogic;
    }    
      
    /**
     * Check to see if the spike cave's item is in logic
     * It's in logic if it's not obtained and Death Mountain is accessible:
     * 1) The Hammer is acquired
     * 2) Power Gloves are acquired
     * 3) An invincibility item is acquired
     *    - Cane of Byrna
     *    - Magic Cape    
     * Note - Check about Half Magic
     * @param inventory The current inventory
     * @return True or False if the location is in logic
     */
    private boolean logicSpikeCave(Inventory inventory) {
        if (!spikeCave.isAcquired())
            return false;
        
        if (portals.deathMountainWestPortalOpen(inventory))
            return false;
        
        if (!inventory.getItem(KeyItem.HAMMER).isOwned())
            return false;
        
        if (!inventory.getItem(Gloves.GLOVES).isOwned())
            return false;
        
        return inventory.getItem(KeyItem.BYRNA).isOwned() || 
                inventory.getItem(KeyItem.CAPE).isOwned();   
    }
    
    /**
     * Check to see if East Dark Death Mountain is reachable
     * Public Method so that it can be used by GanonsTower
     * East Dark Death Mountain is open if either death 
     * moutain portal is open
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean eastDarkDeathMountainAccess(Inventory inventory) {      
        if (closed(inventory))
            return false;
        
        return portals.deathMountainNorthEastPortalOpen(inventory) || 
                portals.deathMountainSouthEastPortalOpen(inventory);
    }
    
    /**
     * Check to see if the Turtle Rock Platform is reachable
     * Public Method so that it can be used by TurtleRock
     * Turtle Rock Platform is open if the portal is open
     * @param inventory the current inventory
     * @return True of False if it's accessible
     */
    public boolean turtleRockPlatformAccess(Inventory inventory) {      
        if (closed(inventory))
            return false;
        
        return portals.deathMountainNorthEastPortalOpen(inventory);
    }
    
    /**
     * Get the locations that are currently in 
     * logic in the East Dark Death Mountain area
     * @param inventory The current inventory
     * @return The locations that are in logic
     */
    private ArrayList<Location> logicEastDarkDeathMountain
        (Inventory inventory) {
        ArrayList<Location> inLogic = new ArrayList();
         
        if (!superbunnyCaveTop.isAcquired())
            inLogic.add(superbunnyCaveTop);
   
        if (!superbunnyCaveBottom.isAcquired())
            inLogic.add(superbunnyCaveBottom);
        
        if (inventory.getItem(KeyItem.HOOKSHOT).isOwned()) {
            if (!hookshotCaveTopRight.isAcquired())
                inLogic.add(hookshotCaveTopRight);
            if (!hookshotCaveTopLeft.isAcquired())
                inLogic.add(hookshotCaveTopLeft);
            if (!hookshotCaveBottomLeft.isAcquired())
                inLogic.add(hookshotCaveBottomLeft);
            if (!hookshotCaveBottomRight.isAcquired())
                inLogic.add(hookshotCaveBottomRight);
        }
        else
            if (inventory.getItem(KeyItem.BOOTS).isOwned())
                if (!hookshotCaveBottomRight.isAcquired())
                    inLogic.add(hookshotCaveBottomRight);                

        return inLogic;
    }  
    
    /**
     * Check to see if the chests in the Pyramid Fairy are in logic
     * It's in logic if it's not opened and the 5/6 Crystals are obtained
     * Either are required for access:
     * 1) Hammer
     * 2) Mirror and Agahnim defeated
     * @param crystal5 The 5th Crystal to check if it is obtained
     * @param crystal6 The 6th Crystal to check if it is obtained
     * @param inventory The current inventory
     * @return True or False if the chest is in logic
     */
    public ArrayList<Location> logicPyramidFairy(Inventory inventory, 
            Reward crystal5, Reward crystal6) {
        ArrayList<Location> inLogic = new ArrayList();
        
        if (crystal5.isAcquired() && crystal6.isAcquired() && 
                southDarkAccess(inventory)) { 
            if (inventory.getItem(KeyItem.HAMMER).isOwned() ||
                    (inventory.getItem(KeyItem.MIRROR).isOwned() &&
                    portals.agahnimPortalOpen())) {
                inLogic.add(pyramidFairyLeft);
                inLogic.add(pyramidFairyRight);
            }
        }
        
        return inLogic;
    }
    
    //Getters and Setters for the locations below
    //Private class for the portals is after
    
    /**
     * Get a list of all the locations
     * @return All the locations in the Dark World
     */
    @Override
    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        
        locations.add(bumperCave);
        
        locations.add(cShapedHouse);
        locations.add(chestGame);
        locations.add(brewery);
                
        locations.add(hammerPegs);
        locations.add(blacksmith);
        locations.add(purpleChest);
        
        locations.add(diggingGame);
        
        locations.add(stumpy);
        
        locations.add(hypeCaveTop);
        locations.add(hypeCaveMiddleRight);
        locations.add(hypeCaveMiddleLeft);
        locations.add(hypeCaveBottom);
        locations.add(hypeCaveNPC);
        
        locations.add(mireShedLeft);
        locations.add(mireShedRight);
        
        locations.add(pyramid);
        
        locations.add(catfish);
        
        locations.add(spikeCave);
        
        locations.add(superbunnyCaveTop);
        locations.add(superbunnyCaveBottom);
        
        locations.add(hookshotCaveTopRight);
        locations.add(hookshotCaveTopLeft);
        locations.add(hookshotCaveBottomLeft);
        locations.add(hookshotCaveBottomRight);
        
        locations.add(pyramidFairyLeft);
        locations.add(pyramidFairyRight); 
        
        return locations; 
    }
    
    /**
     * @return the bumperCave
     */
    public Location getBumperCave() {
        return bumperCave;
    }
    
    /**
     * @param contents The new contents of the cave
     */
    public void setBumperCave(Item contents) {
        bumperCave.setContents(contents);
    }

    /**
     * @return the cShapedHouse
     */
    public Location getCShapedHouse() {
        return cShapedHouse;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setCShapedHouse(Item contents) {
        cShapedHouse.setContents(contents);
    }

    /**
     * @return the chestGame
     */
    public Location getChestGame() {
        return chestGame;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setChestGame(Item contents) {
        chestGame.setContents(contents);
    }
    
    /**
     * @return the brewery
     */
    public Location getBrewery() {
        return brewery;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setBrewery(Item contents) {
        brewery.setContents(contents);
    }
    
    /**
     * @return the hammerPegs
     */
    public Location getHammerPegs() {
        return hammerPegs;
    }
    
    /**
     * @param contents The new contents of the cave
     */
    public void setHammerPegs(Item contents) {
        hammerPegs.setContents(contents);
    }

    /**
     * @return the blacksmith
     */
    public Location getBlacksmith() {
        return blacksmith;
    }

    /**
     * @param contents The new contents of the blacksmiths
     */
    public void setBlacksmith(Item contents) {
        blacksmith.setContents(contents);
    }
    
    /**
     * @return the purpleChest
     */
    public Location getPurpleChest() {
        return purpleChest;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setPurpleChest(Item contents) {
        purpleChest.setContents(contents);
    }

    /**
     * @return the diggingGame
     */
    public Location getDiggingGame() {
        return diggingGame;
    }
    
    /**
     * @param contents The new contents of the game
     */
    public void setDiggingGame(Item contents) {
        diggingGame.setContents(contents);
    }

    /**
     * @return stumpy
     */
    public Location getStumpy() {
        return stumpy;
    }
    
    /**
     * @param contents The new contents of stumpy
     */
    public void setStumpy(Item contents) {
        stumpy.setContents(contents);
    }

    /**
     * @return the hypeCaveTop
     */
    public Location getHypeCaveTop() {
        return hypeCaveTop;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHypeCaveTop(Item contents) {
        hypeCaveTop.setContents(contents);
    }

    /**
     * @return the hypeCaveMiddleRight
     */
    public Location getHypeCaveMiddleRight() {
        return hypeCaveMiddleRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHypeCaveMiddleRight(Item contents) {
        hypeCaveMiddleRight.setContents(contents);
    }
    
    /**
     * @return the hypeCaveMiddleLeft
     */
    public Location getHypeCaveMiddleLeft() {
        return hypeCaveMiddleLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHypeCaveMiddleLeft(Item contents) {
        hypeCaveMiddleLeft.setContents(contents);
    }
    
    /**
     * @return the hypeCaveBottom
     */
    public Location getHypeCaveBottom() {
        return hypeCaveBottom;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHypeCaveBottom(Item contents) {
        hypeCaveBottom.setContents(contents);
    }
    
    /**
     * @return the hypeCaveNPC
     */
    public Location getHypeCaveNPC() {
        return hypeCaveNPC;
    }
    
    /**
     * @param contents The new contents of the NPC
     */
    public void setHypeCaveNPC(Item contents) {
        hypeCaveNPC.setContents(contents);
    }
    
    /**
     * @return the mireShedLeft
     */
    public Location getMireShedLeft() {
        return mireShedLeft;
    }
    
    /**
     * @param contents The new contents of the mireShedLeft
     */
    public void setMireShedLeft(Item contents) {
        mireShedLeft.setContents(contents);
    }
    
    /**
     * @return the mireShedRight
     */
    public Location getMireShedRight() {
        return mireShedRight;
    }
    
    /**
     * @param contents The new contents of the mireShedRight
     */
    public void setMireShedRight(Item contents) {
        mireShedRight.setContents(contents);
    }

    /**
     * @return the pyramid
     */
    public Location getPyramid() {
        return pyramid;
    }
    
    /**
     * @param contents The new contents of the pyramid
     */
    public void setPyramid(Item contents) {
        pyramid.setContents(contents);
    }

    /**
     * @return the catfish
     */
    public Location getCatfish() {
        return catfish;
    }

    /**
     * @param contents Catfish's new item
     */
    public void setCatfish(Item contents) {
        catfish.setContents(contents);
    }
    
    /**
     * @return the spikeCave
     */
    public Location getSpikeCave() {
        return spikeCave;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setSpikeCave(Item contents) {
        spikeCave.setContents(contents);
    }

    /**
     * @return the superbunnyCaveTop
     */
    public Location getSuperbunnyCaveTop() {
        return superbunnyCaveTop;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setSuperbunnyCaveTop(Item contents) {
        superbunnyCaveTop.setContents(contents);
    }
    
    /**
     * @return the superbunnyCaveBottom
     */
    public Location getSuperbunnyCaveBottom() {
        return superbunnyCaveBottom;
    }

    /**
     * @param contents The new contents of the chest
     */
    public void setSuperbunnyCaveBottom(Item contents) {
        superbunnyCaveBottom.setContents(contents);
    }
    
    /**
     * @return the hookshotCaveTopRight
     */
    public Location getHookshotCaveTopRight() {
        return hookshotCaveTopRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHookshotCaveTopRight(Item contents) {
        hookshotCaveTopRight.setContents(contents);
    }
    
    /**
     * @return the hookshotCaveTopLeft
     */
    public Location getHookshotCaveTopLeft() {
        return hookshotCaveTopLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHookshotCaveTopLeft(Item contents) {
        hookshotCaveTopLeft.setContents(contents);
    }
    
    /**
     * @return the hookshotCaveBottomLeft
     */
    public Location getHookshotCaveBottomLeft() {
        return hookshotCaveBottomLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHookshotCaveBottomLeft(Item contents) {
        hookshotCaveBottomLeft.setContents(contents);
    }
    
    /**
     * @return the hookshotCaveBottomRight
     */
    public Location getHookshotCaveBottomRight() {
        return hookshotCaveBottomRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setHookshotCaveBottomRight(Item contents) {
        hookshotCaveBottomRight.setContents(contents);
    }
    
    /**
     * @return the pyramidFairyLeft
     */
    public Location getPyramidFairyLeft() {
        return pyramidFairyLeft;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setPyramidFairyLeft(Item contents) {
        pyramidFairyLeft.setContents(contents);
    }
    
    /**
     * @return the pyramidFairyRight
     */
    public Location getPyramidFairyRight() {
        return pyramidFairyRight;
    }
    
    /**
     * @param contents The new contents of the chest
     */
    public void setPyramidFairyRight(Item contents) {
        pyramidFairyRight.setContents(contents);
    }
    
    /**
     * Used to print the name of the area
     * @return The name of the area
     */
    @Override
    public String toString() {
        return NAME;
    }
    
    /**
     * Private class to check which portals are on logic that will 
     * be used to see what areas of the Dark World are accessible
     */
    private class Portals {
        
        //Used to check if portals are accessible
        private final DeathMountain deathMountain;
        private final Reward agahnim;
        
        /**
         * Constructor Method
         * Instantiate the two private fields
         * @param deathMountain the Moutain in the light world
         * @param agahnim the reward from agahnim to see if the portal is open
         */
        public Portals(DeathMountain deathMountain, Reward agahnim) {
            this.deathMountain = deathMountain;
            this.agahnim = agahnim;
        }
        
        /**
         * Check to see if the Agahnim portal is open
         * It's open if Agahnim is defeated
         * @return True or False if the portal is open
         */
        public boolean agahnimPortalOpen() {
            return agahnim.isAcquired();
        }
        
        /**
         * Check to see if the Kakariko portal is open
         * It's open if either:
         * 1) The Titan's Mitts are acquired
         * 2) The Power Gloves and the Hammer are acquired
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean kakarikoPortalOpen(Inventory inventory) {
            return inventory.getItem(Gloves.GLOVES).getDescription()
                    .equals(Gloves.TITANS_MITTS) ||
                    (inventory.getItem(Gloves.GLOVES).isOwned() &&
                    inventory.getItem(KeyItem.HAMMER).isOwned());
        }
       
        /**
         * Check to see if the mire portal is open
         * It's open if both:
         * 1) The Titan's Mitts are acquired
         * 2) The Flute is acquired
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean mirePortalOpen(Inventory inventory) {
            return inventory.getItem(Gloves.GLOVES).getDescription()
                    .equals(Gloves.TITANS_MITTS) &&
                    inventory.getItem(KeyItem.FLUTE).isOwned();
        }
        
        /**
         * Check to see if the South shore portal is open
         * It's open if both:
         * 1) The Power Gloves are acquired
         * 2) The Hammer is acquired
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean southShorePortalOpen(Inventory inventory) {
            return inventory.getItem(Gloves.GLOVES).isOwned() &&
                    inventory.getItem(KeyItem.HAMMER).isOwned();
        }
        
       /**
         * Check to see if the Lake Hylia portal is open
         * It's open if both:
         * 1) The Titan's Mitts are acquired
         * 2) The Flippers are acquired
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean lakeHyliaPortalOpen(Inventory inventory) {
            return inventory.getItem(Gloves.GLOVES).getDescription()
                    .equals(Gloves.TITANS_MITTS) &&
                    inventory.getItem(KeyItem.FLIPPERS).isOwned();
        }
        
        /**
         * Check to see if the East Hyrule portal is open
         * It's open if both:
         * 1) The Power Gloves are acquired
         * 2) The Hammer is acquired
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean eastHyrulePortalOpen(Inventory inventory) {
            return inventory.getItem(Gloves.GLOVES).isOwned() &&
                    inventory.getItem(KeyItem.HAMMER).isOwned();            
        }
        
        /**
         * Check to see if the West Death Mountain portal is open
         * It's open if Death Mountain is open
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean deathMountainWestPortalOpen(Inventory inventory) {
            return !deathMountain.closed(inventory);
        }
        
        /**
         * Check to see if the South East Death Mountain portal is open
         * It's open if Death Mountain is open 
         * and the Titan's Mitts are acquired
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean deathMountainSouthEastPortalOpen(Inventory inventory) {
            if (deathMountain.eastDeathMountainAccess(inventory))
                return inventory.getItem(Gloves.GLOVES).getDescription()
                        .equals(Gloves.TITANS_MITTS);
            
            return false;
        }
        
        /**
         * Check to see if the North East Death Mountain portal is open
         * It's open if Death Mountain is open and both:
         * 1) The Titan's Mitts are acuired
         * 2) The Hammer is acquired
         * @param inventory The current inventory
         * @return True or False if the portal is open
         */
        public boolean deathMountainNorthEastPortalOpen(Inventory inventory) {
            if (deathMountain.eastDeathMountainAccess(inventory))
                return inventory.getItem(Gloves.GLOVES).getDescription()
                        .equals(Gloves.TITANS_MITTS) && 
                        inventory.getItem(KeyItem.HAMMER).isOwned();
                    
            return false;            
        }
    }
}