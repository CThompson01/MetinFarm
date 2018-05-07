package crops;

import main.GameData;

public class Carrot extends Crop {
    static int id = 1;
    public Carrot() {
        timeToGrow = GameData.growthTime[id];
        finishTime = setFinishTime();
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
