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
}
