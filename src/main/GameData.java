package main;

import java.io.*;
import java.util.stream.Stream;

public class GameData {
    // Other game data
    static final String version = "0.3.5";

    // Costs for crops
    public static final double[] costs = {3.00, 7.50, 0.00};
    public static double[] profits = {0.50, 1.50, 0.00};

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
            "Corn       Grow Time: 30s     Cost: $" + costs[0],
            "Pumpkin    Grow Time: 60s     Cost: $" + costs[1]};

    /**
     * Prints out the specified help menu
     * Used to print the main help, plot help and crops
     */
    static void printHelp(String[] helpText) {
        for (String aHelpText : helpText) {
            System.out.println(aHelpText);
        }
    }
}
