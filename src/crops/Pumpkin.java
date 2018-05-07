package crops;

import main.GameData;

public class Pumpkin extends Crop{
    static int id = 6;
    public Pumpkin() {
        timeToGrow = GameData.growthTime[id]; //180
        finishTime = setFinishTime();
        cost = GameData.costs[id];
        profit = GameData.profits[id];
        typeOfCrop = GameData.plantNames[id];
    }
}
