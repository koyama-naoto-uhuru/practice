package zoo;

public interface IZooDate {
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
