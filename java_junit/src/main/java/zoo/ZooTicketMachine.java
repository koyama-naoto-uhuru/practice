package zoo;

public class ZooTicketMachine {
    private PersonCategory personCategory = new PersonCategory("adult");
    private int money;
    private ZooDate zooDate;
    private int numberOfTickets = 1;

    public ZooTicketMachine(ZooDate mockZooDate) {
        this.zooDate = mockZooDate;
    }

    public void insertMoney(int money) {
        this.money = money;
    }

    public int buy() {
        int price = personCategory.price();
        if (zooDate.isWeekend()) {
            price = price + 200;
        }
        price = price * numberOfTickets;
        return money - price;
    }

    public void setPersonCaetgory(String personCategory) {
        this.personCategory = new PersonCategory(personCategory);
    }

    public void numberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
