package crops;

public class Crop {
    int timeToGrow;
    int finishTime;
    double cost;
    double profit;
    public String typeOfCrop;
    public String cropType;

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
    public boolean harvestSuccess() {
        return ((int)System.currentTimeMillis()/1000) >= finishTime;
    }

    /**
     * Handles the failure of a harvest
     * Prints the amount of time needing
     * to be waited before the crop can
     * be successfully harvested
     */
    public void harvestFail() {
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
     * Prints the amount of time left for the plot
     */
    public void printTimeLeft() {
        System.out.println("This plot of " + typeOfCrop + " will finish growing in: " + timeLeft() + " seconds");
    }

    /**
     * Calculates the amount of time left for a crop to grow
     * @return The amount of seconds remaining until finished growing
     */
    public int timeLeft() {
        return finishTime - (int)System.currentTimeMillis()/1000;
    }

    /**
     * Sets the time left for a crop to grow on the load of the game
     * @param timeLeft The time left for the crop to grow once the game is loaded
     */
    public void loadTime(int timeLeft) { timeToGrow = timeLeft; }
}
