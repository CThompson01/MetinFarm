package main;

import crops.Corn;
import crops.Crop;
import crops.Empty;
import crops.Pumpkin;

import java.lang.reflect.InvocationTargetException;

class Planting {
    static String[] plantNames = {"corn", "pumpkin", "empty"};
    static Class[] plantTypes = {Corn.class, Pumpkin.class, Empty.class};
    /**
     * Method that deals with the basis of planting crops
     * @param crop The type of crop to be planted
     * @param player The player information
     */
    static void plant(String crop, PlayerData player, boolean cost) {
        // Runs through a list of crops and compares them to the crop wanted
        for (int i = 0; i < plantNames.length; i++) {
            // Checks if the plant name and the crop wanting to be planted align
            if (plantNames[i].equalsIgnoreCase(crop)) {
                // Checks if the player has enough money to plant the crop
                if ((player.money >= GameData.costs[i] && cost) || !cost) {
                    // Tries to plant the crop specified
                    try {
                        player.plots[player.selectedPlot] = (Crop) plantTypes[i].getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    // If the player is supposed to be charged
                    // (cost == true)
                    // The money is taken from the player account
                    if (cost)
                        player.money -= GameData.costs[i];
                } else
                    System.out.println("Not enough money");
            }
        }

//        else
//            System.out.println("That is not a crop.\nType \"crops\" for a list of crops");
    }
}
