package zoo;

import java.util.Calendar;

public class ZooDate implements IZooDate {

    @Override
    public int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
