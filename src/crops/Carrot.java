package crops;

import main.GameData;

public class Carrot extends Crop {
    public Carrot() {
        int id = 1;
        timeToGrow = GameData.growthTime[id];
        finishTime = setFinishTime();
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
