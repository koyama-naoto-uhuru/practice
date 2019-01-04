package addressbook;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressBookTest {

    @Test
    void addPerson() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson("taro");
        assertThat(addressBook.persons.get(0).name, is("taro"));
    }

    @Test
    void addPersonWhenInvalidName() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson("");
        assertThat(addressBook.persons.size(), is(0));
    }

    @Test
    void showAllPerson() {
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson("taro");
        addressBook.addPerson("jiro");
        List<Person> persons = addressBook.showAllPerson();
        assertThat(persons.size(), is(2));
    }

    @Test
    void showAllPersonWhenEmpty() {
        AddressBook addressBook = new AddressBook();
        List<Person> persons = addressBook.showAllPerson();
        assertThat(persons.size(), is(0));
    }

}
