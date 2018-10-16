import java.util.List;
import java.util.Map;

public class UserController {
    public void create(Map<String, String> params) {
        DataBase con = new DataBase();
        String name = params.get("name");
        String age = params.get("age");
        con.execute("insert into user (name,age) values ('" + name + "', '" + age + "')");
    }

    public List<Map> searchName(String name) {
        DataBase con = new DataBase();
        return con.find("select * from user where name = '" + name + "';");
    }

    public List<Map> searchAge(String age) {
        DataBase con = new DataBase();
        return con.find("select * from user where age = '" + age + "';");
    }

    public List<Map> searchAgeAndName(String age, String name) {
        DataBase con = new DataBase();
        return con.find("select * from user where age = '" + age + "'" + " and name = '" + name + "';");
    }
}

