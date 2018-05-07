package crops;

import main.GameData;

public class Tomato extends Crop {
    static int id = 3;
    public Tomato() {
        timeToGrow = GameData.growthTime[id]; //120
        finishTime = setFinishTime();
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
