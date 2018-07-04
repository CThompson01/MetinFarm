package main;

import crops.Crop;

import java.io.*;

class PlayerData {
    // Variables containing plot information
    public Crop[] plots = {new Crop(), new Crop(), new Crop(), new Crop(), new Crop()};
    public int selectedPlot = 0;

    // Variables containing currency
    public double money = 5.50;

    public boolean canAffordCrop(Crop crop) {
    	return money >= crop.cost;
    }
    
    /**
     * Saves the current game state to a text document
     */
    public void savePlayerData() {
        System.out.println("Saving Game...");
        try {
            PrintWriter printWriter = new PrintWriter("save.txt", "UTF-8");
            printWriter.println("money=" + this.money);
            printWriter.println("difficulty=" + GameData.difficulty);
            for (int i = 0; i < this.plots.length; i++) {
                printWriter.println("plot" + i + "=" + this.plots[i].typeOfCrop);
                printWriter.println("timeLeft" + i + "=" + this.plots[i].finishTime);
            }
            printWriter.close();
            System.out.println("Saving Successful!");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("Saving failed...");
        }
    }

    /**
     * Loads the current game state from a text document
     */
    public void loadSave() {
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
                } else if (saves.toString().contains("difficulty=")){
                    String diff = saves.toString().substring(11);
                    GameData.difficulty = Integer.parseInt(diff);
                }
                else
                    System.out.println("Nice try hacker but that's not a proper save command");
            }
            
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // Creates save data if the save file is not found
            System.out.println("Save data not found. Creating save data now...");
            savePlayerData();
        } catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't close buffered reader during save");
		}
        
        selectedPlot = 0; // Reset selected plot
    }
}
