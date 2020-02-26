package group_a;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {

    @Test
    void valid() {
        FizzBuzz fizzbuzz = new FizzBuzz();
        String test = fizzbuzz.fizzbuzz(1);
        assertEquals("1",test);
    }

    @Test
    void valid2() {
        FizzBuzz fizzbuzz = new FizzBuzz();
        String test = fizzbuzz.fizzbuzz(2);
        assertEquals("2",test);
    }

    @Test
    void validFizz() {
        FizzBuzz fizzbuzz = new FizzBuzz();
        String test = fizzbuzz.fizzbuzz(3);
        assertEquals("fizz",test);
    }

    @Test
    void validBuzz() {
        FizzBuzz fizzbuzz = new FizzBuzz();
        String test = fizzbuzz.fizzbuzz(5);
        assertEquals("buzz",test);
    }

    @Test
    void validFizzBuzz() {
        FizzBuzz fizzbuzz = new FizzBuzz();
        String test = fizzbuzz.fizzbuzz(15);
        assertEquals("fizzbuzz",test);
    }
}
