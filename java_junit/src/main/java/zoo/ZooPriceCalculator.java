package zoo;

public class ZooPriceCalculator {
    private IZooDate zooDate;
    private int numberOfTickets = 1;
    private PersonCategory personCategory = new PersonCategory("adult");

    public ZooPriceCalculator(IZooDate zooDate) {
        this.zooDate = zooDate;
    }

    int price() {
        return (personCategory.price() + zooDate.getAdditionalPrice()) * numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setPersonCategory(PersonCategory personCategory) {
        this.personCategory = personCategory;
    }
}
