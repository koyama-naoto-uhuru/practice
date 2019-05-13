package codesmell;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeSmellTest {

    @Test
    void featureEnvy() {
        List<String> actual = new UserCollection().text();
        assertEquals("1. kanai daiki", actual.get(0));
        assertEquals("2. kimura fumika", actual.get(1));
    }

    @Test
    void primitiveObsession() {
        Person person = new Person(1, "kanai", "daiki", "09011112222");
        assertEquals("090-1111-2222", person.cellPhoneNumberWithDash());
    }
}
