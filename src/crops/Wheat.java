package crops;

import main.GameData;

public class Wheat extends Crop {
    static int id = 0;
    public Wheat() {
        timeToGrow = GameData.growthTime[id];
        finishTime = setFinishTime();
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
