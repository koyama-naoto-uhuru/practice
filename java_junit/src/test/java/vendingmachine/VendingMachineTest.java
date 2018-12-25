package vendingmachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendingMachineTest {

    @Nested
    class TopDown {

        @Test
        void currentChargeWhenBuyDrink() {
            VendingMachine vm = new VendingMachine();
            vm.charge(1000);
            vm.addDrink("coke");
            vm.buy("coke");
            assertEquals(vm.currentCharge(), 880);
        }

        @Test
        void currentChargeWhenBuyDrink2() {
            VendingMachine vm = new VendingMachine();
            vm.charge(1000);
            vm.addDrink("water");
            vm.buy("water");
            assertEquals(vm.currentCharge(), 850);
        }

        @Test
        void inventoryInfoWhenBuyDrink() {
            List<Pattern> patterns = Arrays.asList(
                    new Pattern(Arrays.asList("coke"), "coke", "coke:0 water:0"),
                    new Pattern(Arrays.asList("coke", "coke"), "coke", "coke:1 water:0"),
                    new Pattern(Arrays.asList("water"), "water", "coke:0 water:0"),
                    new Pattern(Arrays.asList("coke", "water", "water"), "water", "coke:1 water:1")
            );
            patterns.forEach(pattern -> {
                VendingMachine vm = new VendingMachine();
                vm.charge(1000);
                pattern.drinks.forEach(drink -> {
                    vm.addDrink(drink);
                });
                vm.buy(pattern.buyName);
                assertEquals(vm.inventory(), pattern.expected);
            });
        }

        class Pattern {

            private final List<String> drinks;
            private final String buyName;
            private final String expected;

            public Pattern(List<String> drinks, String buyName, String expected) {

                this.drinks = drinks;
                this.buyName = buyName;
                this.expected = expected;
            }
        }

        private class VendingMachine {
            Inventory inventory = new Inventory();
            private int money;

            public void buy(String name) {
                inventory.drinks.stream()
                        .filter(drink -> drink.equals(name))
                        .findFirst()
                        .map(drink -> inventory.drinks.remove(drink));
            }

            public void addDrink(String name) {
                inventory.drinks.add(name);
                if (name.equals("coke")) {
                    money = money - 120;
                } else {
                    money = money - 150;
                }
            }

            public String inventory() {
                return "coke:" + inventory.countBy("coke") + " water:" + inventory.countBy("water");
            }

            public void charge(int money) {
                this.money = money;
            }

            public int currentCharge() {
                return money;
            }

            class Inventory {
                List<String> drinks = new ArrayList();

                public int countBy(String name) {
                    return (int) drinks.stream().filter(n -> n.equals(name)).count();
                }
            }
        }
    }

    @Test
    void chargeValidMoney() {
        Arrays.asList(10, 50, 500, 1000).forEach(charge -> {
            assertThat(new VendingMachine().charge(charge).currentCharge(), is(charge));
        });
    }

    @Test
    void chargeInvalidMoney() {
        Arrays.asList(1, 5, 5000, 10000).forEach(charge -> {
            assertThat(new VendingMachine().charge(charge).currentCharge(), is(0));
        });
    }

    @Test
    void multiCharge() {
        Integer charge = new VendingMachine().charge(100).charge(1000).currentCharge();
        assertThat(charge, is(1100));
    }

    @Test
    void resetCharge() {
        VendingMachine vm = new VendingMachine().charge(100).charge(1000);
        assertThat(vm.resetCharge(), is(1100));
        assertThat(vm.currentCharge(), is(0));
    }

    @Test
    void has5CokeInit() {
        VendingMachine vm = new VendingMachine();
        assertThat(vm.inventory.drinks.size(), is(5));
        vm.inventory.drinks.forEach((drink) -> {
            assertThat(drink.name, is("coke"));
            assertThat(drink.price, is(120));
        });

    }

    @Test
    void addDrink() {
        VendingMachine vm = new VendingMachine().addDrink("water", "100");
        assertThat(vm.inventory.drinks.size(), is(6));
        assertThat(vm.inventory.drinks.get(5).name, is("water"));
        assertThat(vm.inventory.drinks.get(5).price, is(100));
    }

    @Test
    void cantAddOverMaxDrink() {
        VendingMachine vm = new VendingMachine();
        for (int i = 1; i <= 11; i++) {
            vm.addDrink("water", "100");
        }
        assertThat(vm.inventory.countBy("water"), is(10));
    }


    @Test
    void inventory() {
        VendingMachine vendingMachine = new VendingMachine();
        assertThat(vendingMachine.inventory(), is("coke 120yen: 5"));
        vendingMachine.addDrink("coke", "120");
        assertThat(vendingMachine.inventory(), is("coke 120yen: 6"));
        vendingMachine.addDrink("water", "100");
        assertThat(vendingMachine.inventory(), is("coke 120yen: 6\nwater 100yen: 1"));
    }


    @Nested
    public class canBuy {
        private VendingMachine vendingMachine;

        @BeforeEach
        void before() {
            vendingMachine = new VendingMachine();
        }

        @Test
        void lessChargeEnoughInventory() {
            vendingMachine.addDrink("tea", "150");
            vendingMachine.charge(100).charge(10);
            assertThat(vendingMachine.canBy("tea"), is(false));
            assertThat(vendingMachine.canBy("coke"), is(false));
        }

        @Test
        void enoughChargeAndInventory() {
            vendingMachine.charge(100).charge(10).charge(10);
            assertThat(vendingMachine.canBy("coke"), is(true));
        }

        @Test
        void lessInventoryEnoughtCharge() {
            vendingMachine.charge(100).charge(50);
            assertThat(vendingMachine.canBy("tea"), is(false));
        }
    }

    @Nested
    public class buy {

        private VendingMachine vm;

        @BeforeEach
        void before() {
            vm = new VendingMachine();
            vm.addDrink("tea", "150");
            vm.charge(500);
        }

        @Test
        void updateCharge() {
            Arrays.asList(
                    new String[]{"coke", "380"},
                    new String[]{"tea", "230"}
            ).forEach(pattern -> {
                vm.buy(pattern[0]);
                assertThat(vm.currentCharge(), is(Integer.valueOf(pattern[1])));
            });
        }

        @Test
        void updateInventory() {
            vm.buy("tea");
            assertThat(vm.inventory(), is("coke 120yen: 5"));
        }

        @Test
        void cantBuyWhenNoInventory() {
            vm.buy("tea");
            vm.buy("tea");
            assertThat(vm.currentCharge(), is(350));
            assertThat(vm.inventory(), is("coke 120yen: 5"));
        }
    }
}




