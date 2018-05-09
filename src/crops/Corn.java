package crops;

import main.GameData;

public class Corn extends Crop {
    public Corn() {
        int id = 8;
        timeToGrow = GameData.growthTime[id];
        finishTime = setFinishTime(GameData.difficulty);
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
