package main;

import crops.Crop;

import java.text.NumberFormat;
import java.util.Scanner;

import static main.GameData.printHelp;
import static main.GameData.reloadCrops;
import static main.Planting.plant;

public class Game {
    // Variables needed for basic functionality
    private boolean isRunning;
    private Scanner input;
    private NumberFormat formatter;

    // Initializes the player
    private PlayerData player = new PlayerData();

    /**
     * Main method
     * Continues running until the user types quit or exit
     */
    public static void main(String[] args) {
        Game metinFarm = new Game();
        metinFarm.loop();
    }
    
    private Game() {
    	isRunning = true;
        input = new Scanner(System.in);
        formatter = NumberFormat.getCurrencyInstance();

        // Loads previously saved game data automatically
        player.loadSave();

        // Prints out a splash screen for new players
        System.out.println("Welcome to MetinFarm v" + GameData.version + "\n" +
                "If you are new to the game type \"help\"");
    }
    
    /**
     * Main game loop
     */
    public void loop() {
        while (isRunning) {
            System.out.print("> ");
            executeCommand(input.nextLine());
        }

        // Exit clauses
        System.out.println("Exiting your farm... \nGoodbye!");
        input.close();
    }
    
    /**
     * Get the player's currently selected crop
     * @return the player's currently selected crop.
     */
    public Crop getSelectedCrop() {
    	return player.plots[player.selectedPlot];
    }

    /**
     * Identifies the current command being used
     * Then evaluates the command and does the subsequent actions
     * @param command The command input by the user
     */
    private void executeCommand(String command) {
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
            if (plantCrop(command.substring(6)))
                getSelectedCrop().printTimeLeft();
        }
        else if (command.equalsIgnoreCase("harvest all") || command.equalsIgnoreCase("harvestall"))
            harvestAll();
        else if (command.contains("harvest"))
            executePlotCommand("harvest");
        else if (command.equalsIgnoreCase("plots ready"))
            printPlots();
        else if (command.contains("plot "))
            executePlotCommand(command.substring(5));
        else if (command.contains("plots "))
            executePlotCommand(command.substring(6));
        else if (command.equalsIgnoreCase("plots"))
            printPlots();
        else
            System.out.println("That is not a command.\nTry typing \"help\" for a list of commands");
    }

    /**
     * Handles stuff dealing with the planting command
     * @param crop The type of crop
     */
    private boolean plantCrop(String crop) {
        return plant(crop, player, true);
    }

    private void needCrop() {
        System.out.println("Please select a crop to plant");
        printHelp(GameData.listOfCrops);
        System.out.print("Crop > ");
        plantCrop(input.nextLine());
    }

    /**
     * Handles stuff dealing with the plot commands
     * @param command The sub command for plots
     */
    private void executePlotCommand(String command) {
        if (command.equalsIgnoreCase("harvest")) {
            harvest(false);
        } else if (command.equalsIgnoreCase("time") || command.equalsIgnoreCase("time left"))
            getSelectedCrop().printTimeLeft();

        // Other plot commands
        else if (command.equalsIgnoreCase("help")) // Prints plot help
            printHelp(GameData.plotHelp);
        else if (command.equalsIgnoreCase("selected")) // Prints selected plot
            System.out.println("Plot " + (player.selectedPlot + 1) + " selected");
        else if (command.equalsIgnoreCase("planted") ||
                command.equalsIgnoreCase("crop") ||
                command.equalsIgnoreCase("type")) // Prints the type of crop
            System.out.println(getSelectedCrop().typeOfCrop);
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
            } catch (Throwable e) {
                System.out.println("Please enter a real command");
            }
        }
    }

    /**
     * If the plant is ready to be harvested it harvests the plant
     * If the plant is not ready to be harvested tell remaining time
     */
    private void harvest(boolean harvestAll) {
        if (getSelectedCrop().typeOfCrop.equalsIgnoreCase("Empty Plot") && !harvestAll)
            System.out.println("Plot is empty. Please plant a crop first.\n" +
                    "Type \"help\" if you don't know how to plant.");
        else if (!getSelectedCrop().typeOfCrop.equalsIgnoreCase("Empty Plot")) {
            if (getSelectedCrop().canBeHarvested()) {
                double moneyMade = getSelectedCrop().harvest();
                player.plots[player.selectedPlot] = new Crop();
                player.money += moneyMade;
                System.out.println("Money Made: " + formatter.format(moneyMade));
                executeCommand("balance");
            } else {
                getSelectedCrop().printRemainingTime();
            }
        }
    }

    /**
     * Harvests all harvestable plants
     */
    private void harvestAll() {
        for (int i = 0; i < player.plots.length; i++) {
            player.selectedPlot = i;
            harvest(true);
        }
    }

    /**
     * Prints a graphical view of the plots
     */
    private void printPlots() {
        for (int i = 0; i < player.plots.length; i++) {
        	Crop plot = player.plots[i];
            System.out.print("Plot " + (i+1) + ": ");
            if (plot.typeOfCrop.equalsIgnoreCase("Empty Plot"))
                System.out.println("#   Empty Plot    #");
            else if (plot.canBeHarvested())
                System.out.println("#   Harvestable   #");
            else
                System.out.println("#   Crop: " + plot.typeOfCrop + "  Time Left: " + plot.timeLeftFull() + "    #");
        }
    }

    private void setDifficulty() {
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

    private void setDifficulty(String input) {
        String difficulty = input.substring(11);
        GameData.difficulty = (difficulty.contains("extreme") ? 60 :
                difficulty.contains("hard") ? 6 :
                difficulty.contains("medium") ? 2 :
                difficulty.contains("easy") ? 1 : GameData.difficulty);
        System.out.println("Difficulty set to " + difficulty + "(" + GameData.difficulty + ")");
    }
}
