package addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    public List<Person> persons = new ArrayList<>();

    public void addPerson(String name) {
        if (name.isEmpty()) return;
        persons.add(new Person(name));
    }

    public List<Person> showAllPerson() {
        return persons;
    }
}
