package hardware_and_api;

import database.DataBase;

import java.util.List;

public class HealthMonitor {
    private HealthMonitorTest.MockHardWareController mockHardWareController;

    public HealthMonitor(HealthMonitorTest.MockHardWareController mockHardWareController) {
        this.mockHardWareController = mockHardWareController;
    }

    public void run() {
        String color = new DataApi().getColor();
        mockHardWareController.changeColor(color);
    }


    private class DataApi {
        private String getColor() {
            DataBase dataBase = new DataBase();
            List items = dataBase.find("select * from log where status = 0;").items;
            String color;
            if (items.size() >= 1) {
                color = "Red";
            } else {
                color = "Green";
            }
            return color;
        }

    }
}
