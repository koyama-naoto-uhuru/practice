import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    @Test
    void execute() {
        List<TestCase> testCaseList = Arrays.asList(
            new TestCase(1, "1"),
            new TestCase(2,"2"),
            new TestCase(3,"Fizz")
        );

        testCaseList.forEach(testCase -> {
            String fizzBuzzResult = new FizzBuzz(testCase.inputValue).execute();
            assertEquals(testCase.expected, fizzBuzzResult);
        });

    }


    private class TestCase {
        private final int inputValue;
        private final String expected;

        public TestCase(int inputValue, String expected) {
            this.inputValue = inputValue;
            this.expected = expected;
        }
    }
}
