package zoo;

public class ZooTicketMachine {
    private String personCategory = "adult";
    private int money;
    private ZooDate zooDate;

    public ZooTicketMachine(ZooDate mockZooDate) {
        this.zooDate = mockZooDate;
    }

    public void insert(int money) {
        this.money = money;
    }

    public int buy() {
        int price = PersonCategoryEnum.priceBy(personCategory);
        if (zooDate.isWeekend()) {
            price = price + 200;
        }
        return money - price;
    }

    public void setPersonCaetgory(String personCategory) {
        this.personCategory = personCategory;
    }

    public enum PersonCategoryEnum {
        adult(new Adult()),
        child(new Child()),
        senior(new Senior());

        private PersonCategory obj;

        private PersonCategoryEnum(PersonCategory personCategory) {
            this.obj = personCategory;
        }

        public static int priceBy(String personCategory) {
            return PersonCategoryEnum.valueOf(personCategory).obj.price();
        }
    }

}
