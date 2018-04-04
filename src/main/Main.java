package main;

import crops.Crop;
import crops.Empty;

import java.util.Scanner;

public class Main {

    // Variables needed for basic functionality
    private static boolean isRunning;
    private static Scanner input;

    // Initializes the player
    private static PlayerData player = new PlayerData();

    /**
     * Main method
     * Continues running until the user types quit or exit
     */
    public static void main(String[] args) {
        isRunning = true;
        input = new Scanner(System.in);
        while (isRunning) {
            System.out.print("> ");
            getCommand(input.nextLine());
        }
        System.out.println("Exiting your farm... \nGoodbye!");
        input.close();
    }

    /**
     * Identifies the current command being used
     * Then evaluates the command and does the subsequent actions
     * @param command The command input by the user
     */
    private static void getCommand(String command) {
        // Contains basic menu and help commands
        if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit"))
            isRunning = false;
        else if (command.equalsIgnoreCase("save"))
            System.out.println("Saving game...");
        else if (command.equalsIgnoreCase("help"))
            printHelp(GameData.helpText);
        else if (command.equalsIgnoreCase("crops"))
            printHelp(GameData.listOfCrops);
        else if (command.equalsIgnoreCase("money") || command.equalsIgnoreCase("balance"))
            System.out.println("Current Balance: $" + player.money);

        // Contains farming based commands
        else if (command.contains("plant "))
            plantStuff(command.substring(6));
        else if (command.contains("harvest"))
            plotStuff("harvest");
        else if (command.equalsIgnoreCase("plots ready"))
            printHarvestable();
        else if (command.contains("plot "))
            plotStuff(command.substring(5));
        else if (command.contains("plots "))
            plotStuff(command.substring(6));
        else if (command.equalsIgnoreCase("plots"))
            printPlots();
        else
            System.out.println("That is not a command.\nTry typing \"help\" for a list of commands");
    }

    /**
     * Handles stuff dealing with the planting command
     * @param crop The type of crop
     */
    private static void plantStuff(String crop) {
        Planting.plant(crop, player);
    }

    /**
     * Handles stuff dealing with the plot commands
     * @param command The sub command for plots
     */
    private static void plotStuff(String command) {
        if (command.equalsIgnoreCase("harvest")) {
            harvest();
        } else if (command.equalsIgnoreCase("time") || command.equalsIgnoreCase("time left"))
            player.plots[player.selectedPlot].printTimeLeft();

        // Other plot commands
        else if (command.equalsIgnoreCase("help")) // Prints plot help
            printHelp(GameData.plotHelp);
        else if (command.equalsIgnoreCase("selected")) // Prints selected plot
            System.out.println("Plot " + (player.selectedPlot + 1) + " selected");
        else if (command.equalsIgnoreCase("planted") ||
                command.equalsIgnoreCase("crop") ||
                command.equalsIgnoreCase("type")) // Prints the type of crop
            System.out.println(player.plots[player.selectedPlot].typeOfCrop);
        else { // Tries to select a plot
            try {
                int oldPlot = player.selectedPlot;
                player.selectedPlot = Integer.parseInt(command);
                if (player.selectedPlot > 0 && player.selectedPlot < player.plots.length + 1) {
                    player.selectedPlot -= 1;
                    System.out.println("Plot " + (player.selectedPlot + 1) + " selected");
                } else {
                    player.selectedPlot = oldPlot;
                    System.out.println("Please select a real plot");
                }
            } catch(Throwable e) {
                System.out.println("Please enter a real command");
            }

        }
    }

    /**
     * If the plant is ready to be harvested it harvests the plant
     * If the plant is not ready to be harvested tell remaining time
     */
    private static void harvest() {
        if (player.plots[player.selectedPlot].harvestSuccess()) {
            double moneyMade = player.plots[player.selectedPlot].harvest();
            player.plots[player.selectedPlot] = new Empty();
            player.money += moneyMade;
            System.out.println("Money Made: $" + moneyMade);
            getCommand("balance");
        } else {
            player.plots[player.selectedPlot].harvestFail();
        }
    }

    /**
     * Prints out a graphic to show what crops are on which plot
     */
    private static void printPlots() {
        for (Crop plot : player.plots) {
            System.out.println( "#   " + plot.typeOfCrop + "   #");
        }
    }

    /**
     * Prints a graphical view to show which plots are harvestable
     */
    private static void printHarvestable() {
        for (Crop plot : player.plots) {
            if (plot.typeOfCrop.equalsIgnoreCase("Empty Plot"))
                System.out.println("#   Empty Plot    #");
            else if (plot.harvestSuccess())
                System.out.println("#   Harvestable   #");
            else
                System.out.println("#   " + plot.timeLeft() + "s   #");
        }
    }

    /**
     * Prints out the specified help menu
     * Used to print the main help, plot help and crops
     */
    private static void printHelp(String[] helpText) {
        for (String aHelpText : helpText) {
            System.out.println(aHelpText);
        }
    }
}
