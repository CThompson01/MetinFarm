package main;

import crops.Corn;
import crops.Crop;
import crops.Empty;
import crops.Pumpkin;

class Planting {
    static String[] plantNames = {"corn", "pumpkin", "empty"};
    static Class[] plantTypes = {Corn.class, Pumpkin.class, Empty.class};
    /**
     * Method that deals with the basis of planting crops
     * @param crop The type of crop to be planted
     * @param player The player information
     */
    static void plant(String crop, PlayerData player, boolean cost) {
//        for (int i = 0; i < plantNames.length; i++) {
//            if (plantNames[i].equalsIgnoreCase(crop)) {
//                if (player.money >= GameData.cornCost) {
//                    player.plots[player.selectedPlot] = plantTypes[i];
//                    player.money -= GameData.cornCost;
//                } else
//                    System.out.println("Not enough money");
//            }
//        }
        if (crop.equalsIgnoreCase("corn"))
            corn(player, cost);
        else if (crop.equalsIgnoreCase("pumpkin") || crop.equalsIgnoreCase("pumpkins"))
            pumpkin(player, cost);
        else if (crop.equalsIgnoreCase("Empty Plot") || crop.equalsIgnoreCase("empty"))
            empty(player);
        else
            System.out.println("That is not a crop.\nType \"crops\" for a list of crops");
    }

    /**
     * Deals with the planting process of the corn crop
     * @param player Holds player information
     */
    private static void corn(PlayerData player, boolean cost) {
        if (cost) {
            if (player.money >= GameData.cornCost) {
                player.plots[player.selectedPlot] = new Corn();
                player.money -= GameData.cornCost;
            } else
                System.out.println("Not enough money");
        } else
            player.plots[player.selectedPlot] = new Corn();
    }

    /**
     * Deals with the planting process of the pumpkin crop
     * @param player Holds player information
     */
    private static void pumpkin(PlayerData player, boolean cost) {
        if (cost) {
            if (player.money >= GameData.pumpkinCost) {
                player.plots[player.selectedPlot] = new Pumpkin();
                player.money -= GameData.pumpkinCost;
            } else
                System.out.println("Not enough money");
        } else
            player.plots[player.selectedPlot] = new Pumpkin();
    }

    private static void empty(PlayerData player) {
        player.plots[player.selectedPlot] = new Empty();
    }
}
