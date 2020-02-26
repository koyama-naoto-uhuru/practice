package group_c;

public class User
{

    private final String name;
    private final String company;
    private final String mail;
    private final String password;

    public User(String name, String company, String mail, String password)
    {
        this.name = name;
        this.company = company;
        this.mail = mail;
        this.password = password;
    }

    public boolean checkPassword()
    {
        if (this.password == null)
        {
            return false;
        }
        return this.password.length() >= 8;
    }

    public String getSignature()
    {
        String checkPasswordResult = getSignatureCheckResult();
        String companyPart = getSignatureCompanyPart();
        return companyPart + this.name + " " + checkPasswordResult;
    }

    private String getSignatureCompanyPart() {
        if (this.company.length() > 0)
        {
            return this.company + ": ";
        }
        return "";
    }

    private String getSignatureCheckResult() {
        if (this.checkPassword())
        {
            return "OK";
        }
        return "NG";
    }
}
