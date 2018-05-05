package crops;

import main.GameData;

public class Corn extends Crop {
    public Corn() {
        timeToGrow = 30;
        finishTime = setFinishTime();
        cost = GameData.costs[0];
        profit = GameData.profits[0];
        typeOfCrop = "corn";
//        printTimeLeft();
    }
}
