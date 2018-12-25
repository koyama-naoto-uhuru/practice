package trainticketvendingmachine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainTicketVendingMachineTest {


    @Test
    public void buyTokyoToAkihabara() {
        TrainTicketVendingMachine ttvm = new TrainTicketVendingMachine();
        ttvm.currentStation("Tokyo");
        assertEquals(ttvm.buy("Akihabara"), 200);
    }

    @Test
    public void buyTokyoToUeno() {
        TrainTicketVendingMachine ttvm = new TrainTicketVendingMachine();
        ttvm.currentStation("Tokyo");
        assertEquals(ttvm.buy("Ueno"), 250);
    }

    @Test
    public void buyAkihabaraToUeno() {
        TrainTicketVendingMachine ttvm = new TrainTicketVendingMachine();
        ttvm.currentStation("Akihabara");
        assertEquals(ttvm.buy("Ueno"), 150);
    }
}
