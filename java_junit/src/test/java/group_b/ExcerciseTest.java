package group_b;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcerciseTest {

    @Nested
    class CheckPassword {
        @Test
        void verification() {
            User user = new User("氏名", "会社名", "mail@mail.com", "12345678");
            Boolean result = user.verificationPassword();
            assertEquals(true, result);
        }

        @Test
        void verificationPasswordLength() {
            User user = new User("氏名", "会社名", "mail@mail.com", "1234567");
            Boolean result = user.verificationPassword();
            assertEquals(false, result);
        }
    }

    @Nested
    class checkSignature {
        @Test
        void verificationCompanyNameNull() {
            List<User> users = Arrays.asList(
                    new User("氏名", null, "mail@mail.com", "12345678")

                    );
            List<String> expectedResult = Arrays.asList(
                    "氏名 OK"
            );

            TestCase testCase = new TestCase(new User("氏名", null, "mail@mail.com", "12345678"), "氏名 OK");


//            group_a.User user = new group_a.User("氏名", null, "mail@mail.com", "12345678");
            for (User current : users) {
                String result = current.getShomei();
                assertEquals("氏名 OK", result);
            }

        }

        @Test
        void verificationCompanyNameKara() {
            User user = new User("氏名", "", "mail@mail.com", "12345678");
            String result = user.getShomei();
            assertEquals("氏名 OK", result);
        }

        @Test
        void verificationCompanyName() {
            User user = new User("氏名", "会社名", "mail@mail.com", "12345678");
            String result = user.getShomei();
            assertEquals("会社名：氏名 OK", result);
        }

        @Test
        void verificationCompanyName_NG() {
            User user = new User("氏名", "会社名_", "mail@mail.com", "12345678");
            String result = user.getShomei();
            assertEquals("会社名_：氏名 OK", result);
        }


        @Test
        void verificationCompanyName_PassWordNG() {
            User user = new User("氏名", "会社名_", "mail@mail.com", "1234567");
            String result = user.getShomei();
            assertEquals("会社名_：氏名 NG", result);
        }

        @Test
        void verificationCompanyNameKara_PassWordNG() {
            User user = new User("氏名", "", "mail@mail.com", "1234567");
            String result = user.getShomei();
            assertEquals("氏名 NG", result);
        }

        @Test
        void verificationCompanyNameNull_PassWordNG() {
            User user = new User("氏名", null, "mail@mail.com", "1234567");
            String result = user.getShomei();
            assertEquals("氏名 NG", result);
        }
    }

}
