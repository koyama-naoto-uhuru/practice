package codesmell;

public class Person {
    private int id;
    private final String lastName;
    private final String firstName;
    private String cellPhoneNumber;

    public Person(int id, String lastName, String firstName, String cellPhoneNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String cellPhoneNumberWithDash() {
        return this.cellPhoneNumber.substring(0, 3) + "-" +
                this.cellPhoneNumber.substring(3, 7) + "-" +
                this.cellPhoneNumber.substring(7, 11);
    }

    public boolean validate() {
        if (lastName.length() > 6) {
            return false;
        }

        boolean invalidFirstName = firstName.length() > 6;
        if (invalidFirstName) {
            return false;
        }

        return cellPhoneNumber.length() <= 11;
    }
}
