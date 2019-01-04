package zoo;

public class ZooTicketMachine {
    private ZooMoney chargedMoney = new ZooMoney(0);
    private ZooPriceCalculator zooPriceCalculator;

    public ZooTicketMachine(ZooDate mockZooDate) {
        zooPriceCalculator = new ZooPriceCalculator(mockZooDate);
    }

    public void insertMoney(int money) {
        if (new ZooMoney(money).invalidMoney()) return;
        this.chargedMoney = new ZooMoney(money);
    }

    public int buy() {
        int change = calcChange();
        if (change < 0) return 0;
        chargedMoney = new ZooMoney(0);
        return change;
    }

    private int calcChange() {
        return chargedMoney.value - zooPriceCalculator.price();
    }

    public void setPersonCaetgory(String personCategory) {
        zooPriceCalculator.setPersonCategory(new PersonCategory(personCategory));
    }

    public void numberOfTickets(int numberOfTickets) {
        zooPriceCalculator.setNumberOfTickets(numberOfTickets);
    }

    public int chargedMoney() {
        return chargedMoney.value;
    }
}
