package crops;

public class Pumpkin extends Crop{
    public Pumpkin() {
        timeToGrow = 60;
        finishTime = setFinishTime();
        cost = 2.50;
        profit = 1.50;
        typeOfCrop = "pumpkin";
        printTimeLeft();
    }
}
