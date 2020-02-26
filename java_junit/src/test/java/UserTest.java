
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    public void validationTest_8moji() {
        User maker = new User("鈴木", "kaisya", "abc@test.jp", "password");
        assertEquals(true, maker.validate());
    }

    @Test
    public void validationTest_10moji() {
        User maker = new User("鈴木", "", "", "1234567890");
        assertEquals(true, maker.validate());
    }

    @Test
    public void validationTest_4moji_ng() {
        User maker = new User("鈴木", "", "", "pass");
        assertEquals(false, maker.validate());
    }

    @Nested
    class CreateShomeiTest {

        @BeforeAll
        public void TestCate(){

        }

        @Test
        public void createTest_無職佐藤_passwordOK() {
            String name = "佐藤";
            String pass = "password";
            User user = new User(name, "", "", pass);
            String shomei = user.getShomei();
            assertEquals(name + " OK", shomei);
        }

        @Test
        void createTest_無職佐藤_passwordNG() {
            //given
            TestCase testCase = new TestCase(new User("佐藤", "", "", "pass"), "佐藤 NG");
            //when
            String shomei = testCase.user.getShomei();
            //then
            assertEquals(testCase.expected, shomei);
        }

        @Test
        public void createTest_会社員鈴木_passwordOK() {
            String name = "鈴木";
            String pass = "password";
            String company = "ブルボン";
            String expected = company + "：" + name + " OK";
            User user = new User(name, company, "", pass);
            String shomei = user.getShomei();

            assertEquals(expected, shomei);
        }

        @Test
        public void createTest_会社員鈴木_passwordNG() {
            String name = "鈴木";
            String pass= "pass";
            String company = "ブルボン";
            User user = new User(name, company, "", pass);
            String shomei = user.getShomei();
            assertEquals(company + "：" + name + " NG", shomei);
        }

        private class TestCase {
            private final User user;
            private final String expected;

            public TestCase(User user, String expected) {
                this.user = user;
                this.expected = expected;
            }
        }
    }
}

