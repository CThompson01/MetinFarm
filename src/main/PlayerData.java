package main;

import crops.Crop;
import crops.Empty;

class PlayerData {
    // Variables containing plot information
    Crop[] plots = {new Empty(), new Empty(), new Empty(), new Empty(), new Empty()};
    int selectedPlot = 0;

    // Variables containing currency
    double money = 5.00;
}
