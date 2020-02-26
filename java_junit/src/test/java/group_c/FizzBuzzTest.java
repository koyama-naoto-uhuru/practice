package group_c;

import group_c.FizzBuzz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {

    @Test
    void FizzBuzz() {
        String resultFizzBuzz = FizzBuzz.run(3);
        assertEquals("fizz",resultFizzBuzz);
    }
    @Test
    void FizzBuzz2() {
        String resultFizzBuzz = FizzBuzz.run(5);
        assertEquals("buzz",resultFizzBuzz);
    }
    @Test
    void FizzBuzz3() {
        String resultFizzBuzz = FizzBuzz.run(1);
        assertEquals("1",resultFizzBuzz);
    }
    @Test
    void FizzBuzz15() {
        String resultFizzBuzz = FizzBuzz.run(15);
        assertEquals("fizzbuzz",resultFizzBuzz);
    }
}
