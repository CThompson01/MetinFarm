package crops;

import main.GameData;

public class Pumpkin extends Crop{
    public Pumpkin() {
        timeToGrow = 60;
        finishTime = setFinishTime();
        cost = GameData.costs[1];
        profit = GameData.costs[1];
        typeOfCrop = "pumpkin";
//        printTimeLeft();
    }
}
