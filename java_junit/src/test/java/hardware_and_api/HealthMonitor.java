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
            List list = dataBase.find("select * from log where status = 0;");
            String color;
            if (list.size() >= 1) {
                color = "Red";
            } else {
                color = "Green";
            }
            return color;
        }

    }
}
