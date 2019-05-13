package codesmell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserCollection {

    private List<Person> people;

    public UserCollection() {
        Person person = new Person(1, "kanai", "daiki");
        Person person2 = new Person(2, "kimura", "fumika");
        people = Arrays.asList(person, person2);
    }

    public List<String> text() {
        List<String> list = new ArrayList<>();
        people.forEach(person -> {
            list.add(person.getId() + ". " + person.getLastName() + " " + person.getFirstName());
        });
        return list;
    }

}
