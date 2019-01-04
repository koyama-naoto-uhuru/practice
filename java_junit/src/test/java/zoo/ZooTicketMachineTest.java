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
                zooTicketMachine.insertMoney(data[0]);
                int change = zooTicketMachine.buy();
                assertThat(change, is(data[1]));
            });
        }

        @Test
        void invalidMoney() {
            Arrays.asList(
                    new int[]{1, 0},
                    new int[]{5, 0}
            ).forEach(data -> {
                ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(2));
                zooTicketMachine.insertMoney(data[0]);
                assertThat(zooTicketMachine.chargedMoney(), is(data[1]));
            });
        }
    }

    @Nested
    class PersonCategory {
        @Test
        void buyChild() {
            ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(2));
            zooTicketMachine.insertMoney(1000);
            zooTicketMachine.setPersonCaetgory("child");
            int change = zooTicketMachine.buy();
            assertThat(change, is(600));
        }

        @Test
        void buySenior() {
            ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(2));
            zooTicketMachine.insertMoney(1000);
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
                zooTicketMachine.insertMoney(1000);
                int change = zooTicketMachine.buy();
                assertThat(change, is(data[1]));
            });
        }
    }

    @Test
    void buy2Tickets() {
        ZooTicketMachine zooTicketMachine = new ZooTicketMachine(new MockZooDate(1));
        zooTicketMachine.insertMoney(2000);
        zooTicketMachine.numberOfTickets(2);
        int change = zooTicketMachine.buy();
        assertThat(change, is(0));
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
