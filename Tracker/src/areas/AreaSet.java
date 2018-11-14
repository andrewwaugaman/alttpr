package areas;

import java.util.ArrayList;
import java.util.HashMap;
import items.Inventory;
import items.Location;

/**
 *
 * @author Andrew
 */
public class AreaSet {
    
    //A way to track all the different areas
    private final HashMap<String,Area> areas;
    
    //Names of the areas

    /**
     * Constructor Method
     * Create all the different areas and put them into the hash
     * @param rewards The different rewards, used for Agahnim 1/2
     */
    public AreaSet(RewardSet rewards) {
        areas = new HashMap<>();
        
        areas.put(LightWorld.NAME, new LightWorld());
        areas.put(HyruleCastle.NAME, new HyruleCastle());
        areas.put(EasternPalace.NAME, new EasternPalace());   
        areas.put(DesertPalace.NAME, new DesertPalace());
        areas.put(DeathMountain.NAME, new DeathMountain());
        areas.put(TowerOfHera.NAME, new TowerOfHera(
                (DeathMountain)(areas.get(DeathMountain.NAME))));
        areas.put(CastleTower.NAME, new CastleTower(
                rewards.getRewards().get(Reward.AGAHNIM_1)));
        
        areas.put(DarkWorld.NAME, new DarkWorld(
                (DeathMountain)(areas.get(DeathMountain.NAME)),
                rewards.getRewards().get(Reward.AGAHNIM_1)));
        areas.put(DarkPalace.NAME, new DarkPalace(
                (DarkWorld)(areas.get(DarkWorld.NAME))));
        areas.put(SwampPalace.NAME, new SwampPalace(
                (DarkWorld)(areas.get(DarkWorld.NAME))));
        areas.put(SkullWoods.NAME, new SkullWoods(
                (DarkWorld)(areas.get(DarkWorld.NAME))));
        areas.put(ThievesTown.NAME, new ThievesTown(
                (DarkWorld)(areas.get(DarkWorld.NAME))));
        areas.put(IcePalace.NAME, new IcePalace(
                (DarkWorld)(areas.get(DarkWorld.NAME))));
        areas.put(MiseryMire.NAME, new MiseryMire(
                (DarkWorld)(areas.get(DarkWorld.NAME))));

        areas.put(TurtleRock.NAME, new TurtleRock(
                (DarkWorld)(areas.get(DarkWorld.NAME))));
        /*
        
        areas.put(GanonsTower.NAME, new GanonsTower());
        */

    }
    
    /**
     * Get the locations that are currently in logic from each area
     * @param inventory The current inventory
     * @param rewards The different rewards, used for specific checks
     * @return The locations that are in logic
     */
    public ArrayList<Location> locationsInLogic(Inventory inventory,
            RewardSet rewards) {
        ArrayList<Location> locations = new ArrayList();
        for (String name : areas.keySet()) {
            locations.addAll(areas.get(name).locationsInLogic(inventory));
        }
        
        /*
        Extra checks that require rewards
        if(areas.get(LightWorld.NAME).pedestalCheck(inventory, rewards))
            locations.add(areas.get(LightWorld.NAME).getPedestal());
                    
        if(areas.get(LightWorld.NAME).sahasrahlaCheck(inventory, rewards))
            locations.add(areas.get(LightWorld.NAME).getSahasrahla());
                    
        if(areas.get(DeathMountain.NAME).mimicCaveCheck(inventory,
                areas.get(TurtleRock.NAME)))
            locations.add(areas.get(DeathMountain.NAME).getMimicCave());
        
        ArrayList<Location> ganonsTower = 
                areas.get(GanonsTower.NAME).locationsInLogic(inventory, rewards);
        for (int i = 0; i < ganonsTower.size(); i++)
                locations.add(ganonsTower.get(i));
        */
        
        return locations;
    }
    
    public void extraLocations(Inventory inventory, RewardSet rewards) {
        /*
        areas.get(LightWorld.NAME).pedestalCheck(inventory,rewards);
        areas.get(LightWorld.NAME).sahasrahlaCheck(inventory,rewards);
        areas.get(LightWorld.NAME).mimicCaveCheck(inventory,
                areas.get(TurtleRock.NAME));
        areas.
        */
    }
}
