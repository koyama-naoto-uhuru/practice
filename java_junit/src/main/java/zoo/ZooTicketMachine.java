package zoo;

public class ZooTicketMachine {
    private ZooMoney chargedMoney = new ZooMoney(0);
    private ZooPriceCalculator zooPriceCalculator;

    public ZooTicketMachine(ZooDate mockZooDate) {
        zooPriceCalculator = new ZooPriceCalculator(mockZooDate);
    }

    public void insertMoney(int money) {
        ZooMoney moneyObj = new ZooMoney(money);
        if (moneyObj.invalidMoney()) return;
        this.chargedMoney = moneyObj;
    }

    public int buy() {
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
