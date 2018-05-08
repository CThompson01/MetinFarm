package main;

import crops.Crop;
import crops.Empty;

import java.io.*;

class PlayerData {
    // Variables containing plot information
    Crop[] plots = {new Empty(), new Empty(), new Empty(), new Empty(), new Empty()};
    int selectedPlot = 0;

    // Variables containing currency
    double money = 5.00;

    /**
     * Saves the current game state to a text document
     */
    void savePlayerData() {
        System.out.println("Saving Game...");
        boolean saveSuccess = false;
        try {
            PrintWriter printWriter = new PrintWriter("save.txt", "UTF-8");
            printWriter.println("money=" + this.money);
            for (int i = 0; i < this.plots.length; i++) {
                printWriter.println("plot" + i + "=" + this.plots[i].typeOfCrop);
                printWriter.println("timeLeft" + i + "=" + this.plots[i].finishTime);
            }
            printWriter.close();
            saveSuccess = true;
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (!saveSuccess)
            System.out.println("Saving Failed...");
        else
            System.out.println("Saving Successful");
    }

    /**
     * Loads the current game state from a text document
     */
    void loadSave() {
        try {
            FileReader fileReader = new FileReader("save.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Object[] saveData = bufferedReader.lines().toArray();
            for (Object saves : saveData) {
                if (saves.toString().contains("money="))
                    this.money = Double.parseDouble(saves.toString().substring(6));
                else if (saves.toString().contains("plot")) {
                    this.selectedPlot = Integer.parseInt(saves.toString().substring(4, 5));
                    Planting.plant(saves.toString().substring(6), this, false);
                } else if (saves.toString().contains("timeLeft")) {
                    this.selectedPlot = Integer.parseInt(saves.toString().substring(8, 9));
                    this.plots[this.selectedPlot].loadTime(Integer.parseInt(saves.toString().substring(10)));
                }
                else
                    System.out.println("Nice try hacker but that's not a proper save command");
            }
        } catch (FileNotFoundException e) {
            // Creates save data if the save file is not found
            System.out.println("Save data not found. Creating save data now...");
            savePlayerData();
        }
    }
}
