/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public AreaSet() {
        areas = new HashMap<>();
        
        //areas.put(LightWorld.NAME, new LightWorld());
        //areas.put(HyruleCastle.NAME, new HyruleCastle());
        areas.put(EasternPalace.NAME, new EasternPalace());   
        areas.put(DesertPalace.NAME, new DesertPalace());
        //areas.put(DeathMountain.NAME, new DeathMountain());
        //areas.put(TowerOfHera.NAME, new TowerOfHera());
        //areas.put(CastleTower.NAME, new CastleTower());
        
        /*
        areas.put(DarkWorld.NAME, new DarkWorld());
        areas.put(DarkPalace.NAME, new DarkPalace());
        areas.put(SwampPalace.NAME, new SwampPalace());
        areas.put(SkullWoods.NAME, new SkullWoods());
        areas.put(ThievesTown.NAME, new ThievesTown());
        areas.put(IcePalace.NAME, new IcePalace());
        areas.put(MiseryMire.NAME, new MiseryMire());
        areas.put(TurtleRock.NAME, new TurtleRock());
        
        areas.put(GanonsTower.NAME, new GanonsTower());
        */

    }
    
    
    public ArrayList<Location> locationsInLogic(Inventory inventory,
            RewardSet rewards) {
        ArrayList<Location> locations = new ArrayList();
        for (String name : areas.keySet()) {
            ArrayList<Location> available = 
                    areas.get(name).locationsInLogic(inventory);
            for (int i = 0; i < available.size(); i++)
                locations.add(available.get(i));
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
