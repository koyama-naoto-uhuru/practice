package coinparking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinParkingTest {

    //TODO
    //- 1h 500円
    //- 土日は 1h 1000円
    //- 1h以内の場合は 1h分課金
    //- 00分に課金
    //- 夜間最大5000円 21:00-07:00
    //- 売上総計
    //- 1日何台止まったか

    @Test
    public void calcStandardFee() {
        CoinParking cp = new CoinParking();
        cp.start("10");
        cp.finish("11");
        assertEquals(500, cp.fee());
    }
}

