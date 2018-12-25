package trainticketvendingmachine;

public class TrainTicketVendingMachine {
    private String currentStationName;

    public int buy(String toStationName) {
        if (currentStationName == "Tokyo") {
            if (toStationName == "Akihabara") {
                return 200;
            }
            return 250;
        }
        return 150;
    }

    public void currentStation(String currentStationName) {
        this.currentStationName = currentStationName;
    }
}
