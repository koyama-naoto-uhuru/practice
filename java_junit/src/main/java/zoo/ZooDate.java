package zoo;

public interface ZooDate {
    int getDayOfWeek();

    default boolean isWeekend() {
        return getDayOfWeek() == 1 || getDayOfWeek() == 7;
    }

    default int getAdditionalPrice() {
        if (isWeekend()) {
            return 200;
        }
        return 0;
    }
}
