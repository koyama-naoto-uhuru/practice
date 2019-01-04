package vendingmachine;

import java.util.Arrays;

class VendingMachine {
    Inventory inventory;
    private ChargeBox chargeBox = new ChargeBox();

    VendingMachine() {
        inventory = new Inventory();
        for (int i = 0; i < 5; i++) {
            inventory.add(new Drink("coke", 120));
        }
    }

    VendingMachine charge(int money) {
        if (isValidMoney(money)) {
            chargeBox.add(money);
        }
        return this;
    }

    private boolean isValidMoney(int money) {
        return Arrays.asList(10, 50, 100, 500, 1000).contains(money);
    }

    Integer currentCharge() {
        return chargeBox.value;
    }

    Integer resetCharge() {
        return chargeBox.reset();
    }

    boolean canBy(String name) {
        if (!inventory.existBy(name)) {
            return false;
        }
        return !inventory.canBuy(name, currentCharge());
    }

    void buy(String name) {
        int price = inventory.buyBy(name);
        chargeBox.minus(price);
    }


    String inventory() {
        return inventory.info();
    }

    VendingMachine addDrink(String name, String price) {
        if (inventory.countBy(name) < 10) {
            inventory.add(new Drink(name, Integer.parseInt(price)));
        }
        return this;
    }


}
