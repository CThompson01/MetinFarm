package main;

public class GameData {
    public static String[] helpText = {
            "plant %crop%       plants the crop specified on the selected plot",
            "plot help          prints a list of commands dealing with plots",
            "crops              prints a list of all the crops",
            "save               saves the game",
            "exit               exits the game"};
    public static String[] plotHelp = {
            "To plant on a plot use the plant command once a plot is selected",
            "plot %number%      selects the specified plot",
            "plot selected      prints the plot that\'s selected",
            "plot type          prints the crop that\'s planted",
            "plot time          prints the time left till you can harvest",
            "plot harvest       harvests the crop that\'s planted",
            "plots              prints the amount of plots owned"};
    public static String[] listOfCrops = {
            "Corn               Grow Time: 30 seconds",
            "Pumpkin            Grow Time: 60 seconds"};
}
