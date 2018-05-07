package crops;

import main.GameData;

public class Corn extends Crop {
    static int id = 8;
    public Corn() {
        timeToGrow = GameData.growthTime[id]; //240
        finishTime = setFinishTime();
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
