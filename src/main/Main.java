package main;

import crops.Corn;
import crops.Crop;
import crops.Empty;
import crops.Pumpkin;

import java.util.Scanner;

public class Main {

    // Variables needed for basic functionality
    private static boolean isRunning;
    private static Scanner input;

    // Variables containing plot information
    private static Crop[] plots = {new Empty(), new Empty(), new Empty(), new Empty(), new Empty()};
    private static int selectedPlot = 0;

    // Variables containing currency
    private static double money = 5.00;

    public static void main(String[] args) {
        isRunning = true;
        input = new Scanner(System.in);
        while (isRunning) {
            System.out.print("> ");
            getCommand(input.nextLine());
        }
        System.out.println("Exiting your farm... \nGoodbye!");
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
            printHelp();
        else if (command.equalsIgnoreCase("crops"))
            printCrops();
        else if (command.equalsIgnoreCase("money") || command.equalsIgnoreCase("balance"))
            System.out.println("Current Balance: " + money);

        // Contains farming based commands
        else if (command.contains("plant "))
            plantStuff(command.substring(6));
        else if (command.contains("plot "))
            plotStuff(command.substring(5));
        else if (command.contains("plots "))
            plotStuff(command.substring(6));
        else if (command.equalsIgnoreCase("plots"))
            System.out.println("You have " + plots.length + " plots total");
        else
            System.out.println("That is not a command.\nTry typing \"help\" for a list of commands");
    }

    /**
     * Handles stuff dealing with the planting command
     * @param crop The type of crop
     */
    private static void plantStuff(String crop) {
        if (crop.equalsIgnoreCase("corn")){
            System.out.println("Planting corn in plot " + (selectedPlot + 1));
            plots[selectedPlot] = new Corn();
        } else if (crop.equalsIgnoreCase("pumpkin")) {
            System.out.println("Planting pumpkin in plot " + (selectedPlot + 1));
            plots[selectedPlot] = new Pumpkin();
        } else {
            System.out.println("That is not a type of crop");
        }
        money -= plots[selectedPlot].cost;
    }

    /**
     * Handles stuff dealing with the plot commands
     * @param command The sub command for plots
     */
    private static void plotStuff(String command) {
        if (command.equalsIgnoreCase("harvest")) {
            if (plots[selectedPlot].harvestSuccess()) {
                money += plots[selectedPlot].harvest();
                plots[selectedPlot] = new Empty();
            } else {
                plots[selectedPlot].harvestFail();
            }
        } else if (command.equalsIgnoreCase("time") || command.equalsIgnoreCase("time left"))
            plots[selectedPlot].printTimeLeft();

        // Other plot commands
        else if (command.equalsIgnoreCase("help")) // Prints plot help
            printPlotHelp();
        else if (command.equalsIgnoreCase("selected")) // Prints selected plot
            System.out.println("Plot " + (selectedPlot + 1) + " selected");
        else if (command.equalsIgnoreCase("planted") ||
                command.equalsIgnoreCase("crop") ||
                command.equalsIgnoreCase("type")) // Prints the type of crop
            System.out.println(plots[selectedPlot].typeOfCrop);
        else { // Tries to select a plot
            try {
                int oldPlot = selectedPlot;
                selectedPlot = Integer.parseInt(command);
                if (selectedPlot > 0 && selectedPlot < plots.length + 1) {
                    selectedPlot -= 1;
                    System.out.println("Plot " + (selectedPlot + 1) + " selected");
                } else {
                    selectedPlot = oldPlot;
                    System.out.println("Please select a real plot");
                }
            } catch(Throwable e) {
                System.out.println("Please enter a real command");
            }

        }
    }

    /**
     * Prints out the help menu
     */
    private static void printHelp() {
        for (String aHelpText : GameData.helpText) {
            System.out.println(aHelpText);
        }
    }

    /**
     * Prints out the help menu for plots
     */
    private static void printPlotHelp() {
        for (String aPlotHelp : GameData.plotHelp) {
            System.out.println(aPlotHelp);
        }
    }

    /**
     * Prints out the list of crops and time to grow for each crop
     */
    private static void printCrops() {
        for (String aListOfCrops : GameData.listOfCrops) {
            System.out.println(aListOfCrops);
        }
    }
}
