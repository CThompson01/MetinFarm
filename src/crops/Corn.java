package crops;

import main.GameData;

public class Corn extends Crop {
    public Corn() {
        timeToGrow = 30;
        finishTime = setFinishTime();
        cost = GameData.cornCost;
        profit = 0.50;
        typeOfCrop = "corn";
//        printTimeLeft();
    }
}
