package crops;

public class Empty extends Crop {
    public Empty() {
        timeToGrow = 0;
        finishTime = (int)System.currentTimeMillis()/1000;
        cost = 0.0;
        profit = 0.0;
        typeOfCrop = "Empty Plot";
    }
}
