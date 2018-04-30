package main;

import java.io.*;
import java.util.stream.Stream;

public class GameData {
    // Other game data
    static final String version = "0.3";

    // Costs for crops
    public static double cornCost = 3.00;
    public static double pumpkinCost = 7.50;

    // All Help Text
    public static String[] helpText = {
            "To plant on a plot use the plant command once a plot is selected",
            "plant crop_name    plants the crop specified on the selected plot",
            "plot help          prints a list of commands dealing with plots",
            "harvest            harvests the crops on the selected plot",
            "crops              prints a list of all the crops",
            "save               saves the game",
            "exit               exits the game"};
    public static String[] plotHelp = {
            "plot plot_number   selects the specified plot",
            "plot selected      prints the plot that\'s selected",
            "plot type          prints the crop that\'s planted",
            "plot time          prints the time left till you can harvest",
            "plot harvest       harvests the crop that\'s planted",
            "plots              prints out all your plots and what crops are on each",
            "plots ready        prints out all your plots current timeleft till harvestability"};
    public static String[] listOfCrops = {
            "Corn       Grow Time: 30s     Cost: $" + cornCost,
            "Pumpkin    Grow Time: 60s     Cost: $" + pumpkinCost};

    /**
     * Prints out the specified help menu
     * Used to print the main help, plot help and crops
     */
    static void printHelp(String[] helpText) {
        for (String aHelpText : helpText) {
            System.out.println(aHelpText);
        }
    }

    /**
     * Saves the current game state to a text document
     * @param player The player data
     */
    static void saveGame(PlayerData player) {
        System.out.println("Saving Game...");
        boolean saveSuccess = false;
        try {
            PrintWriter printWriter = new PrintWriter("save.txt", "UTF-8");
            printWriter.println("money=" + player.money);
            for (int i = 0; i < player.plots.length; i++) {
                printWriter.println("plot" + i + "=" + player.plots[i].typeOfCrop);
                printWriter.println("timeLeft" + i + "=" + player.plots[i].timeLeft());
            }
            printWriter.close();
            saveSuccess = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            saveSuccess = false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            saveSuccess = false;
        }

        if (!saveSuccess)
            System.out.println("Saving Failed...");
        else
            System.out.println("Saving Successful");
    }

    static void loadGame(PlayerData player) {
        try {
            FileReader fileReader = new FileReader("save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Object[] saveData = bufferedReader.lines().toArray();
            for (Object saves : saveData) {
                if (saves.toString().contains("money="))
                    player.money = Double.parseDouble(saves.toString().substring(6));
                else if (saves.toString().contains("plot")) {
                    player.selectedPlot = Integer.parseInt(saves.toString().substring(4, 5));
                    Planting.plant(saves.toString().substring(6), player, false);
                } else if (saves.toString().contains("timeLeft")) {
                    player.selectedPlot = Integer.parseInt(saves.toString().substring(8, 9));
                    player.plots[player.selectedPlot].loadTime(Integer.parseInt(saves.toString().substring(10)));
                }
                else
                    System.out.println("Nice try hacker but that's not a proper save command");
            }
        } catch (FileNotFoundException e) {
            // Creates save data if the save file is not found
            System.out.println("Save data not found. Creating save data now...");
            saveGame(player);
        }
    }
}
