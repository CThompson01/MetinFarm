package crops;

public class Corn extends Crop {
    public Corn() {
        timeToGrow = 30;
        finishTime = setFinishTime();
        cost = 1.50;
        profit = 0.50;
        typeOfCrop = "corn";
        printTimeLeft();
    }
}
