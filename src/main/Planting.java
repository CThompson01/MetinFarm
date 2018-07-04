package main;

import crops.Crop;

class Planting {
    /**
     * Method that deals with the basis of planting crops
     * @param crop The type of crop to be planted
     * @param player The player information
     */
    public static boolean plant(String cropName, PlayerData player, boolean cost) {
    	// Check if crop entered exists.
    	Crop crop = Crop.fromName(cropName);
    	
    	// Exit if wrong crop name entered
    	if (crop == null) {
    		if (cost) System.out.println("Entered name is not a crop.\nType \"crops\" for a list of crops"); // Print message if user is trying to plant non-existent crop
    		return false;
    	}
    	
    	// Exit if the player doesn't have enough money
    	if (cost && !player.canAffordCrop(crop)) {
    		System.out.println("Not enough money");
    		return false;
    	}
    	
        // Harvest crop before planting a new one
    	if (!player.plots[player.selectedPlot].isEmpty()) {
    		if (player.plots[player.selectedPlot].canBeHarvested()) {
    			// Harvest
    			System.out.println("Please harvest this plot before planting here!");
    		} else {
    			// Alert user there is a crop here
    			System.out.println("There is already " + player.plots[player.selectedPlot].typeOfCrop + " growing here!");
    		}
    		
    		return false;
    	}
    	
    	// Tries to plant the crop specified
        player.plots[player.selectedPlot] = crop;
        if (cost) player.money -= crop.cost;
        return true;
    }
}
