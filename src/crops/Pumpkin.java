package crops;

import main.GameData;

public class Pumpkin extends Crop{
    public Pumpkin() {
        timeToGrow = 60;
        finishTime = setFinishTime();
        cost = GameData.pumpkinCost;
        profit = 1.50;
        typeOfCrop = "pumpkin";
//        printTimeLeft();
    }
}
