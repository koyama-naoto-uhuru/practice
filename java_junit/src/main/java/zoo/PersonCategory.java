package zoo;

public class PersonCategory {
    private String personCategory;

    public PersonCategory(String personCategory) {
        this.personCategory = personCategory;
    }

    int price() {
        return PersonCategoryEnum.valueOf(personCategory).obj.price();
    }

    enum PersonCategoryEnum {
        adult(new Adult()),
        child(new Child()),
        senior(new Senior());

        private IPersonCategory obj;

        private PersonCategoryEnum(IPersonCategory personCategory) {
            this.obj = personCategory;
        }
    }

}
