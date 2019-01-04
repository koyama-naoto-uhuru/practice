package zoo;

public class ZooPriceCalculator {
    public ZooDate zooDate;
    public int numberOfTickets = 1;
    private PersonCategory personCategory = new PersonCategory("adult");

    public ZooPriceCalculator(ZooDate zooDate) {
        this.zooDate = zooDate;
    }

    int price() {
        int price = personCategory.price();
        if (zooDate.isWeekend()) {
            price = price + 200;
        }
        return price * numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setPersonCategory(PersonCategory personCategory) {
        this.personCategory = personCategory;
    }
}
