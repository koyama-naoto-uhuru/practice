package group_a;

public class User {

    private String company;
    private String mail;
    private String pass;
    private String name;

    public User(String name, String company, String mail, String pass) {
        this.name = name;
        this.company = company;
        this.mail = mail;
        this.pass = pass;
    }

    public User(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public User(String name) {
        this.name = name;
    }

    public boolean validate() {
        if (pass.length() >= 8) {
            return true;
        }
        return false;
    }


    public String getShomei() {

        //会社なし
        if (company == null || company.equals("")) {
            if (validate() == true) {
                return name + " OK";
            } else {
                return name + " NG";
            }

        }
        // 会社あり
        if (!(company == null || company.equals(""))) {
            if (validate() == false) {
                return company + "：" + name + " NG";
            }else {
                return company + "：" + name + " OK";
            }
        }

        return "ERROR";

    }
}
