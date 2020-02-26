package group_b;

public class TestCase {
    private final User name;
    private final String expected_message;

    public TestCase(User name, String expected_message) {
        this.name = name;
        this.expected_message = expected_message;
    }
}
