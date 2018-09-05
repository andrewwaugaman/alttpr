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
    
    public void locationsInLogic(Inventory inventory, RewardSet rewards)
    {
        for (String name : areas.keySet()) {
            ArrayList<Location> available = 
                    areas.get(name).locationsInLogic(inventory);
            for (int i = 0; i < available.size(); i++) {
                System.out.println(available.get(i).getDescription());
            }
        }
    }
}
