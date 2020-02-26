package group_c;

import group_c.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Nested
    class CheckPassword {

        @Test
        void patterns() {
            Arrays.asList(
                    new TestCase("password is null", new UserTestBuilder().setPassword(null).please(), false)
                    , new TestCase("password is null", new UserTestBuilder().setPassword("Password").please(), true)
                    , new TestCase("password is null", new UserTestBuilder().setPassword("Passwor").please(), false)
            ).forEach(testCase -> {
                boolean actual = testCase.user.checkPassword();
                assertEquals(testCase.expected, actual, testCase.description);
            });
        }
        /*
        @Test
        void TestCheckPassword_8文字以上() {
            group_c.User target = new group_c.User("Name", "Company", "Mail", "Password");
            boolean result = target.checkPassword();
            assertEquals(true, result);
        }

        @Test
        void TestCheckPassword_7文字以下() {
            group_c.User target = new group_c.User("Name", "Company", "Mail", "Passwor");
            boolean result = target.checkPassword();
            assertEquals(false, result);
        }

        @Test
        void TestCheckPassword_NULL() {
            group_c.User target = new group_c.User("Name", "Company", "Mail", null);
            boolean result = target.checkPassword();
            assertEquals(false, result);
        }*/

        private class TestCase {
            private final String description;
            private final User user;
            private final boolean expected;

            public TestCase(String description, User user, boolean expected) {
                this.description = description;
                this.user = user;
                this.expected = expected;
            }
        }
    }


    @Nested
    class GetSignature {

/*        @Test
        void TestGetSignature_会社名あり_OK() {
            Arrays.asList(
                    new TestData("Name2", "Company2")
                    , new TestData("Test", "Company")
            ).forEach(testData -> {
                group_c.User target = new group_c.User(testData.name, testData.company, "Mail", "Password");
                String result = target.getSignature();
                assertEquals(testData.company + ": " + testData.name + " OK", result);
            });
        }

        @Test
        void TestGetSignature_会社名なし_OK() {
            group_c.User target = new group_c.User("Name", "", "Mail", "Password");
            String result = target.getSignature();
            assertEquals("Name OK", result);
        }

        @Test
        void TestGetSignature_会社名あり_NG() {
            Arrays.asList(
                    new TestData("Name2", "Company2")
                    , new TestData("Test", "Company")
            ).forEach(testData -> {
                group_c.User target = new group_c.User(testData.name, testData.company, "Mail", "Passwor");
                String result = target.getSignature();
                assertEquals(testData.company + ": " + testData.name + " NG", result);
            });
        }
        private class TestData {
            private final String name;
            private final String company;

            public TestData(String name, String company) {

                this.name = name;
                this.company = company;
            }
        }*/

        @Test
        void patterns() {
            Arrays.asList(
                    new TestCase("password ng case", new User("Name2", "", "Mail", "Passwor"), "Name2 NG"),
                    new TestCase("password ng and company exist", new User("Name", "Company", "Mail", "Passwor"), "Company: Name NG"),
                    new TestCase("password ok and no company", new User("Name", "", "Mail", "Password"), "Name OK"),
                    new TestCase("password ok and company exist", new User("Name", "Company2", "Mail", "Password"), "Company2: Name OK")
            ).forEach(testCase -> {
                String actual = testCase.user.getSignature();
                assertEquals(testCase.expected, actual, testCase.description);
            });
        }

        private class TestCase {
            private String description;
            private final User user;
            private final String expected;

            public TestCase(String description, User user, String expected) {
                this.description = description;
                this.user = user;
                this.expected = expected;
            }
        }
    }

    private class UserTestBuilder {
        private String password;

        public UserTestBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User please() {
            return new User("Name", "Company", "Mail", password);
        }
    }
}
