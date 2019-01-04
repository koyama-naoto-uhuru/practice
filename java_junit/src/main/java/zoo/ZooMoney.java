package zoo;

public class ZooMoney {
    public int value;

    public ZooMoney(int value) {
        this.value = value;
    }

    boolean invalidMoney() {
        return value == 1 || value == 5;
    }
}
