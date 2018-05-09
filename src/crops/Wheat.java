package crops;

import main.GameData;

public class Wheat extends Crop {
    public Wheat() {
        int id = 0;
        timeToGrow = GameData.growthTime[id];
        finishTime = setFinishTime(GameData.difficulty);
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
