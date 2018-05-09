package main;

import crops.*;

public class GameData {
    // Other Game Data
    static final String version = "1.0";
    public static int difficulty = 1;

    // All Data For Crops
    // The names that are associated with each crop
    public static String[] plantNames = {
            "wheat", //#0
            "carrot", "carrots", //#1
            "tomato", "tomatoes", "tomatos", //#3
            "pumpkin", "pumpkins", //#6
            "corn", //#8
            "empty"};
    // The classes for the crops
    static Class[] plantTypes = {
            Wheat.class, //#0
            Carrot.class, Carrot.class, //#1
            Tomato.class, Tomato.class, Tomato.class, //#3
            Pumpkin.class, Pumpkin.class, //#6
            Corn.class, //#8
            Empty.class};
    // The cost for each crop
    public static final double[] costs = {
            3.00, //#0
            7.50, 7.50, //#1
            19.50, 19.50, 19.50, //#3
            35.00, 35.00, //#6
            50.75, //#8
            0.00};
    // The profits for each crop
    public static double[] profits = {
            0.50, //#0
            1.50, 1.50, //#1
            4.00, 4.00, 4.00, //#3
            7.25, 7.25, //#6
            10.50, //#8
            0.00};
    // The time for each of the crops to grow
    public static int[] growthTime = {
            30, //#0
            60, 60, //#1
            120, 120, 120, //#3
            180, 180, //#6
            240, //#8
            0};


    // All Help Text
    static String[] helpText = {
            "To plant on a plot use the plant command once a plot is selected",
            "plant crop_name    plants the crop specified on the selected plot",
            "plot help          prints a list of commands dealing with plots",
            "harvest            harvests the crops on the selected plot",
            "money              prints the current amount of money you have",
            "crops              prints a list of all the crops",
            "save               saves the game",
            "exit               exits the game"};
    static String[] plotHelp = {
            "plot plot_number   selects the specified plot",
            "plot selected      prints the plot that\'s selected",
            "plot type          prints the crop that\'s planted",
            "plot time          prints the time left till you can harvest",
            "plot harvest       harvests the crop that\'s planted",
            "plots              prints out all your plots and what crops are on each",
            "plots ready        prints out all your plots current timeleft till harvestability"};
    static String[] listOfCrops = {
            GameData.plantNames[0] + "    Grow Time: " + (growthTime[0] * difficulty) + "s     Cost: $" + costs[0] + "0",
            GameData.plantNames[1] + "    Grow Time: " + (growthTime[1] * difficulty) + "s     Cost: $" + costs[1] + "0",
            GameData.plantNames[3] + "    Grow Time: " + (growthTime[3] * difficulty) + "s     Cost: $" + costs[3] + "0",
            GameData.plantNames[6] + "    Grow Time: " + (growthTime[6] * difficulty) + "s     Cost: $" + costs[6] + "0",
            GameData.plantNames[8] + "    Grow Time: " + (growthTime[8] * difficulty) + "s     Cost: $" + costs[8]};

    /**
     * Prints out the specified help menu
     * Used to print the main help, plot help and crops
     */
    static void printHelp(String[] helpText) {
        for (String aHelpText : helpText) {
            System.out.println(aHelpText);
        }
    }

    static void reloadCrops() {
        listOfCrops[0] = GameData.plantNames[0] + "      Grow Time: " + convertToHours(growthTime[0] * difficulty) + "      Cost: $" + costs[0] + "0";
        listOfCrops[1] = GameData.plantNames[1] + "     Grow Time: " + convertToHours(growthTime[1] * difficulty) + "      Cost: $" + costs[1] + "0";
        listOfCrops[2] = GameData.plantNames[3] + "     Grow Time: " + convertToHours(growthTime[3] * difficulty) + "      Cost: $" + costs[3] + "0";
        listOfCrops[3] = GameData.plantNames[6] + "    Grow Time: " + convertToHours(growthTime[6] * difficulty) + "      Cost: $" + costs[6] + "0";
        listOfCrops[4] = GameData.plantNames[8] + "       Grow Time: " + convertToHours(growthTime[8] * difficulty) + "      Cost: $" + costs[8];
    }

    static String convertToHours(int seconds) {
        int timeleft = seconds;
        int minutesLeft = timeleft / 60;
        int hoursLeft = minutesLeft / 60;
        minutesLeft = minutesLeft % 60;
        int secondsLeft = timeleft % 60;
        return (hoursLeft + ":" + (minutesLeft < 10 ? "0" + minutesLeft : minutesLeft) + ":" + (secondsLeft < 10 ? "0" + secondsLeft : secondsLeft));

    }
}
