package main;

import crops.Corn;
import crops.Pumpkin;

class Planting {
    /**
     * Method that deals with the basis of planting crops
     * @param crop The type of crop to be planted
     * @param player The player information
     */
    static void plant(String crop, PlayerData player) {
        if (crop.equalsIgnoreCase("corn"))
            corn(player);
        else if (crop.equalsIgnoreCase("pumpkin") || crop.equalsIgnoreCase("pumpkins"))
            pumpkin(player);
        else
            System.out.println("That is not a crop.\nType \"crops\" for a list of crops");
    }

    /**
     * Deals with the planting process of the corn crop
     * @param player Holds player information
     */
    private static void corn(PlayerData player) {
        if (player.money >= GameData.cornCost) {
            player.plots[player.selectedPlot] = new Corn();
            player.money -= GameData.cornCost;
        } else
            System.out.println("Not enough money");
    }

    /**
     * Deals with the planting process of the pumpkin crop
     * @param player Holds player information
     */
    private static void pumpkin(PlayerData player) {
        if (player.money >= GameData.pumpkinCost) {
            player.plots[player.selectedPlot] = new Pumpkin();
            player.money -= GameData.pumpkinCost;
        } else
            System.out.println("Not enough money");
    }
}
