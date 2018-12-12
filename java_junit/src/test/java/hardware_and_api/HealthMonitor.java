package hardware_and_api;

public class HealthMonitor {
    private HealthMonitorTest.MockHardWareController mockHardWareController;

    public HealthMonitor(HealthMonitorTest.MockHardWareController mockHardWareController) {
        this.mockHardWareController = mockHardWareController;
    }

    public void run() {
        mockHardWareController.changeColor("Green");
    }
}
