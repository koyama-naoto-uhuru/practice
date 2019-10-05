import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmasTest {

    private List<String> days;

    @Test
    void today_is_xmas() {
        all_of("2019/12/24", "2020/12/24", "0000/12/24").should_be_xmas();
        all_of("2019/12/25", "2019/12/23", "wrong something string").should_not_be_xmas();
    }

    private void should_not_be_xmas() {
        days.forEach(d -> {
            HolidayTest holiday = new HolidayTest();
            holiday.setDay(d);
            assertEquals(false, holiday.isXmas());
        });
    }

    private void should_be_xmas() {
        days.forEach(d -> {
            HolidayTest holiday = new HolidayTest();
            holiday.setDay(d);
            assertEquals(true, holiday.isXmas());
        });
    }

    private XmasTest all_of(String s, String s1, String s2) {
        days = Arrays.asList(s, s1, s2);
        return this;
    }


    private class Holiday {
        public boolean isXmas() {
            String substring = getDay().substring(5, 10);
            return substring.equals("12/24");
        }

        protected String getDay() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
            LocalDate localDate = LocalDate.now();
            return dtf.format(localDate);
        }
    }

    private class HolidayTest extends Holiday {

        private String day;

        @Override
        protected String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }
    }
}
