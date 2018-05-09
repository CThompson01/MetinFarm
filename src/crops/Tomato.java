package crops;

import main.GameData;

public class Tomato extends Crop {
    public Tomato() {
        int id = 3;
        timeToGrow = GameData.growthTime[id]; //120
        finishTime = setFinishTime(GameData.difficulty);
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
