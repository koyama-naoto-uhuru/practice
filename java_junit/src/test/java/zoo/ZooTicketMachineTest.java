package zoo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ZooTicketMachineTest {

    @Nested
    class insertAndChangeMoney {
        @Test
        void buyDefault() {
            Arrays.asList(
                    new int[]{1000, 200},
                    new int[]{1200, 400}
            ).forEach(data -> {
                ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(2));
                zooTicketMachine.insert(data[0]);
                int change = zooTicketMachine.buy();
                assertThat(change, is(data[1]));
            });
        }
    }

    @Nested
    class PersonCategory {
        @Test
        void buyChild() {
            ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(2));
            zooTicketMachine.insert(1000);
            zooTicketMachine.setPersonCaetgory("child");
            int change = zooTicketMachine.buy();
            assertThat(change, is(600));
        }

        @Test
        void buySenior() {
            ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(2));
            zooTicketMachine.insert(1000);
            zooTicketMachine.setPersonCaetgory("senior");
            int change = zooTicketMachine.buy();
            assertThat(change, is(800));
        }
    }

    @Nested
    class Weekend {
        @Test
        void buy() {
            Arrays.asList(
                    new int[]{1, 0},
                    new int[]{7, 0},
                    new int[]{2, 200}
            ).forEach(data -> {
                ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(data[0]));
                zooTicketMachine.insert(1000);
                int change = zooTicketMachine.buy();
                assertThat(change, is(data[1]));
            });
        }
    }

    private class MockZooDate implements ZooDate {
        private int dayOfWeek;

        public MockZooDate(int dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public int getDayOfWeek() {
            return dayOfWeek;
        }
    }
}
