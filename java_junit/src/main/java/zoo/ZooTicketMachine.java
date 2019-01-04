package zoo;

public class ZooTicketMachine {
    private int money;
    private ZooPriceCalculator zooPriceCalculator;

    public ZooTicketMachine(ZooDate mockZooDate) {
        zooPriceCalculator = new ZooPriceCalculator(mockZooDate);
    }

    public void insertMoney(int money) {
        this.money = money;
    }

    public int buy() {
        return money - zooPriceCalculator.price();
    }

    public void setPersonCaetgory(String personCategory) {
        zooPriceCalculator.setPersonCategory(new PersonCategory(personCategory));
    }

    public void numberOfTickets(int numberOfTickets) {
        zooPriceCalculator.setNumberOfTickets(numberOfTickets);
    }
}
