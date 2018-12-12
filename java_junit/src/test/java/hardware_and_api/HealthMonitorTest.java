package hardware_and_api;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthMonitorTest {

    @Test
    public void データがない時緑になる(){
        MockHardWareController mockHardWareController = new MockHardWareController();
        HealthMonitor healthMonitor = new HealthMonitor(mockHardWareController);
        healthMonitor.run();
        assertEquals(1, mockHardWareController.callCountOfChangeColor);
        assertEquals("Green", mockHardWareController.argOfChangeColor);
    }

   class MockHardWareController {
        public int callCountOfChangeColor;
        public String argOfChangeColor;

        public void changeColor(String arg) {
            callCountOfChangeColor++;
            argOfChangeColor = arg;
        }
    }
}

