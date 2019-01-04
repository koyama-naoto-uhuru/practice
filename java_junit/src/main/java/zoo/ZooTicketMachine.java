package zoo;

public class ZooTicketMachine {
    private PersonCategory personCategory = new PersonCategory("adult");
    private int money;
    private ZooDate zooDate;

    public ZooTicketMachine(ZooDate mockZooDate) {
        this.zooDate = mockZooDate;
    }

    public void insert(int money) {
        this.money = money;
    }

    public int buy() {
        int price = personCategory.price();
        if (zooDate.isWeekend()) {
            price = price + 200;
        }
        return money - price;
    }

    public void setPersonCaetgory(String personCategory) {
        this.personCategory = new PersonCategory(personCategory);
    }

}
