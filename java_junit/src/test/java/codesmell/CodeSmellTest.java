package codesmell;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeSmellTest {

    @Test
    void validate() {
        Person person = new Person(1, "kimura", "fumika", "09011112222");
        assertEquals(true, person.validate());

        Person person2 = new Person(2, "kimura!", "fumika", "09011112222");
        assertEquals(false, person2.validate());

        Person person3 = new Person(3, "kimura", "fumika!", "09011112222");
        assertEquals(false, person3.validate());

        Person person4 = new Person(4, "kimura", "fumika", "09011112222!");
        assertEquals(false, person4.validate());
    }

    @Test
    void userCollectionText() {
        List<String> actual = new UserCollection().text();
        assertEquals("1. kanai daiki", actual.get(0));
        assertEquals("2. kimura fumika", actual.get(1));
    }

    @Test
    void cellPhoneNumberWithDash() {
        Person person = new Person(1, "kanai", "daiki", "09011112222");
        assertEquals("090-1111-2222", person.cellPhoneNumberWithDash());
    }
}
