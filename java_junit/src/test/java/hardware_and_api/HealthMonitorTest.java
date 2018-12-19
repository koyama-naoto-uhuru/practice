package hardware_and_api;

import database.DataBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthMonitorTest {

    private DataBase dataBase;

    @BeforeEach
    public void setup(){
        dataBase = new DataBase();
        dataBase.execute("delete from log");
    }

    @Test
    public void データがない時緑になる(){
        MockHardWareController mockHardWareController = new MockHardWareController();
        HealthMonitor healthMonitor = new HealthMonitor(mockHardWareController);
        healthMonitor.run();
        assertEquals(1, mockHardWareController.callCountOfChangeColor);
        assertEquals("Green", mockHardWareController.argOfChangeColor);
    }

    @Test
    public void 赤データが1件の時赤になる(){
        dataBase.execute("insert into log (status) values (0);");
        MockHardWareController mockHardWareController = new MockHardWareController();
        HealthMonitor healthMonitor = new HealthMonitor(mockHardWareController);
        healthMonitor.run();
        assertEquals(1, mockHardWareController.callCountOfChangeColor);
        assertEquals("Red", mockHardWareController.argOfChangeColor);
    }

    @Test
    public void 赤データが2件の時赤になる(){
        dataBase.execute("insert into log (status) values (0);");
        dataBase.execute("insert into log (status) values (0);");
        MockHardWareController mockHardWareController = new MockHardWareController();
        HealthMonitor healthMonitor = new HealthMonitor(mockHardWareController);
        healthMonitor.run();
        assertEquals(1, mockHardWareController.callCountOfChangeColor);
        assertEquals("Red", mockHardWareController.argOfChangeColor);
    }

    @Test
    public void 緑データが1件の時緑になる(){
        dataBase.execute("insert into log (status) values (1);");
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

