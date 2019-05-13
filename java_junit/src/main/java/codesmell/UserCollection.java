package codesmell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserCollection {

    private List<Person> people;

    public UserCollection() {
        Person person = new Person(1, "kanai", "daiki", "090-0000-0000");
        Person person2 = new Person(2, "kimura", "fumika", "090-1111-1111");
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
