package coinparking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CoinParking {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public void start(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void finish(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int fee() {
        int usingTime = (int)ChronoUnit.HOURS.between(startTime, endTime);
        return 500 * usingTime;
    }
}
