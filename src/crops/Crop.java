package crops;

import main.GameData;

public class Crop {
    private int timeToGrow;
    public int finishTime;
    private double cost;
    private double profit;
    public String typeOfCrop;
    
    public Crop() {
    	timeToGrow = 0;
        finishTime = (int)System.currentTimeMillis()/1000;
        cost = 0.0;
        profit = 0.0;
        typeOfCrop = "Empty Plot";
    }
    
    public Crop(int id) {
    	timeToGrow = GameData.growthTime[id];
        finishTime = setFinishTime(GameData.difficulty);
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }

    /**
     * Harvests the current crop
     * @return Returns the amount of money made from the crop
     */
    public double harvest() {
        System.out.println("Harvesting " + typeOfCrop + "...");
        return cost + profit;
    }

    /**
     * Determines whether a harvest is successful or not
     * @return IF the time in millis is greater than the finish time harvest is successful
     */
    public boolean canBeHarvested() {
        return ((int)System.currentTimeMillis()/1000) >= finishTime;
    }

    /**
     * Handles the failure of a harvest
     * Prints the amount of time needing
     * to be waited before the crop can
     * be successfully harvested
     */
    public void printRemainingTime() {
        System.out.println("Wait " + (finishTime - ((int)System.currentTimeMillis()/1000)) + " more seconds before harvesting.");
    }

    /**
     * Calculates the time at which the crop will finish growing
     * @return Returns an int holding the value of when the crop will be finished
     */
    int setFinishTime() {
        return (int)System.currentTimeMillis()/1000 + timeToGrow;
    }

    /**
     * Calculates the time at which the crop will finish growing
     * @param multiplier The multiplying effect depending on the difficulty
     * @return Returns an int holding the value of when the crop will be finished
     */
    int setFinishTime(int multiplier) { return (int)System.currentTimeMillis()/1000 + (timeToGrow * multiplier); }

    /**
     * Prints the amount of time left for the plot
     */
    public void printTimeLeft() {
        System.out.println("This plot of " + typeOfCrop + " will finish growing in: " + timeLeftFull());

    }

    /**
     * Calculates the amount of time left for a crop to grow
     * @return The amount of seconds remaining until finished growing
     */
    public int timeLeft() {
        return finishTime - (int)System.currentTimeMillis()/1000;
    }

    public String timeLeftFull() {
        int timeleft = timeLeft();
        int minutesLeft = timeleft / 60;
        int hoursLeft = minutesLeft / 60;
        minutesLeft = minutesLeft % 60;
        int secondsLeft = timeleft % 60;
        return (hoursLeft + ":" + (minutesLeft < 10 ? "0" + minutesLeft : minutesLeft) + ":" + (secondsLeft < 10 ? "0" + secondsLeft : secondsLeft));
    }

    /**
     * Sets the time left for a crop to grow on the load of the game
     * @param timeLeft The time left for the crop to grow once the game is loaded
     */
    public void loadTime(int timeLeft) { finishTime = timeLeft; }
}
