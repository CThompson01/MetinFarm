package main;

import crops.Crop;
import crops.Empty;

import java.text.NumberFormat;
import java.util.Scanner;

import static main.GameData.printHelp;
import static main.GameData.reloadCrops;
import static main.Planting.plant;

public class Main {

    // Variables needed for basic functionality
    private static boolean isRunning;
    private static Scanner input;
    private static NumberFormat formatter;

    // Initializes the player
    private static PlayerData player = new PlayerData();

    /**
     * Main method
     * Continues running until the user types quit or exit
     */
    public static void main(String[] args) {
        isRunning = true;
        input = new Scanner(System.in);
        formatter = NumberFormat.getCurrencyInstance();

        // Loads previously saved game data automatically
        player.loadSave();

        // Prints out a splash screen for new players
        System.out.println("Welcome to MetinFarm v" + GameData.version + "\n" +
                "If you are new to the game type \"help\"");

        // The main game loop
        while (isRunning) {
            System.out.print("> ");
            getCommand(input.nextLine());
        }

        // Exit clauses
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
            player.savePlayerData();
        else if (command.equalsIgnoreCase("difficulty"))
            setDifficulty();
        else if (command.contains("difficulty "))
            setDifficulty(command);
        else if (command.equalsIgnoreCase("help"))
            printHelp(GameData.helpText);
        else if (command.equalsIgnoreCase("reset") || command.equalsIgnoreCase("restart"))
            System.out.println("Delete the save.txt file to reset your save!");
        else if (command.equalsIgnoreCase("crops")){
            reloadCrops(); printHelp(GameData.listOfCrops);}
        else if (command.equalsIgnoreCase("money") || command.equalsIgnoreCase("balance"))
            System.out.println("Current Balance: " + formatter.format(player.money));

        // Contains farming based commands
        else if (command.equalsIgnoreCase("plant"))
            needCrop();
        else if (command.contains("plant ")) {
            if (plantStuff(command.substring(6)))
                player.plots[player.selectedPlot].printTimeLeft();
        }
        else if (command.equalsIgnoreCase("harvest all") || command.equalsIgnoreCase("harvestall"))
            harvestAll();
        else if (command.contains("harvest"))
            plotStuff("harvest");
        else if (command.equalsIgnoreCase("plots ready"))
            printHarvestable();
        else if (command.contains("plot "))
            plotStuff(command.substring(5));
        else if (command.contains("plots "))
            plotStuff(command.substring(6));
        else if (command.equalsIgnoreCase("plots"))
            printHarvestable();
        else
            System.out.println("That is not a command.\nTry typing \"help\" for a list of commands");
    }

    /**
     * Handles stuff dealing with the planting command
     * @param crop The type of crop
     */
    private static boolean plantStuff(String crop) {
        return plant(crop, player, true);
    }

    private static void needCrop() {
        System.out.println("Please select a crop to plant");
        printHelp(GameData.listOfCrops);
        System.out.print("Crop > ");
        plantStuff(input.nextLine());
    }

    /**
     * Handles stuff dealing with the plot commands
     * @param command The sub command for plots
     */
    private static void plotStuff(String command) {
        if (command.equalsIgnoreCase("harvest")) {
            harvest(false);
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
    private static void harvest(boolean harvestAll) {
        if (player.plots[player.selectedPlot].typeOfCrop.equalsIgnoreCase("Empty Plot") && !harvestAll)
            System.out.println("Plot is empty. Please plant a crop first.\n" +
                    "Type \"help\" if you don't know how to plant.");
        else if (!player.plots[player.selectedPlot].typeOfCrop.equalsIgnoreCase("Empty Plot")) {
            if (player.plots[player.selectedPlot].harvestSuccess() &&
                    !player.plots[player.selectedPlot].typeOfCrop.equalsIgnoreCase("Empty Plot")) {
                double moneyMade = player.plots[player.selectedPlot].harvest();
                player.plots[player.selectedPlot] = new Empty();
                player.money += moneyMade;
                System.out.println("Money Made: " + formatter.format(moneyMade));
                getCommand("balance");
            } else {
                player.plots[player.selectedPlot].harvestFail();
            }
        }
    }

    /**
     * Harvests all harvestable plants
     */
    private static void harvestAll() {
        for (int i = 0; i < player.plots.length; i++) {
            player.selectedPlot = i;
            harvest(true);
        }
    }

    /**
     * Prints a graphical view to show which plots are harvestable
     */
    private static void printHarvestable() {
        int i = 0;
        for (Crop plot : player.plots) {
            i++;
            System.out.print("Plot " + i + ": ");
            if (plot.typeOfCrop.equalsIgnoreCase("Empty Plot"))
                System.out.println("#   Empty Plot    #");
            else if (plot.harvestSuccess())
                System.out.println("#   Harvestable   #");
            else
                System.out.println("#   Crop: " + plot.typeOfCrop + "  Time Left: " + plot.timeLeftFull() + "    #");
        }
    }

    private static void setDifficulty() {
        int current = GameData.difficulty;
        String currentDifficulty = (current == 60 ? "extreme" :
                current == 6 ? "hard" : current == 2 ? "medium" : "easy");
        System.out.println("Please select a difficulty!\n" +
                "Current Difficulty: " + currentDifficulty + "\n" +
                "Easy               Growth time is normal\n" +
                "Medium             Growth time is multiplied by 2\n" +
                "Hard               Growth time is multiplied by 6\n" +
                "Extreme Farming    Growth time is multiplied by 60");
        String difficulty = input.nextLine();
        GameData.difficulty = (difficulty.contains("extreme") ? 60 :
                difficulty.contains("hard") ? 6 :
                difficulty.contains("medium") ? 2 :
                difficulty.contains("easy") ? 1 : GameData.difficulty);
        System.out.println("Difficulty set to " + difficulty + "(" + GameData.difficulty + ")");
    }

    private static void setDifficulty(String input) {
        String difficulty = input.substring(11);
        GameData.difficulty = (difficulty.contains("extreme") ? 60 :
                difficulty.contains("hard") ? 6 :
                difficulty.contains("medium") ? 2 :
                difficulty.contains("easy") ? 1 : GameData.difficulty);
        System.out.println("Difficulty set to " + difficulty + "(" + GameData.difficulty + ")");
    }
}
