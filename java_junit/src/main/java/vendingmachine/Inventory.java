package vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    List<Drink> drinks;

    Inventory() {
        this.drinks = new ArrayList<>();
    }

    void add(Drink drink) {
        drinks.add(drink);
    }

    boolean existBy(String name) {
        return drinks.stream().anyMatch(drink -> drink.name.equals(name));
    }

    boolean canBuy(String name, Integer currentCharge) {
        return currentCharge < findBy(name).price;
    }

    int buyBy(String name) {
        if (!existBy(name)) {
            return 0;
        }
        Drink drink = findBy(name);
        removeBy(name);
        return drink.price;
    }

    private void removeBy(String name) {
        drinks.stream()
                .filter(drink -> drink.name.equals(name))
                .findFirst()
                .map(drink -> drinks.remove(drink));
    }

    private Drink findBy(String name) {
        return drinks.stream()
                .filter(drink -> drink.name.equals(name))
                .findFirst()
                .get();
    }

    private int sizeBy(String name) {
        return (int) drinks.stream()
                .filter(drink -> drink.name.equals(name))
                .count();
    }

    private Map<String, Drink> menu() {
        Map<String, Drink> menu = new HashMap<>();
        drinks.forEach(drink -> {
            menu.put(drink.name, drink);
        });
        return menu;
    }

    String info() {
        List<String> result = new ArrayList<>();
        menu().forEach((name, drink) -> {
            result.add(drink.info() + String.valueOf(sizeBy(name)));
        });
        return String.join("\n", result);
    }

    int countBy(String name) {
        return (int) drinks.stream().filter(d -> d.name.equals(name)).count();
    }
}
