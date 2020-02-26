package group_b;

public class User {
    private final String name;
    private final String company_name;
    private final String mailAdress;
    private final String password;

    public User(String name, String company_name, String mailAdress, String password) {

        this.name = name;
        this.company_name = company_name;
        this.mailAdress = mailAdress;
        this.password = password;
    }

    public Boolean verificationPassword() {
        return this.password.length() >= 8;
    }

    public String getShomei() {
        String result = " NG";
        if (this.verificationPassword()) result = " OK";

        if (this.company_name == null || this.company_name == "") {
            return name + result;
        }
        return company_name + "：" + name + result;
    }
}
