package database;

import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

    DataBase con;

    @BeforeEach
    public void setUp() {
        this.con = new DataBase();
        con.execute("delete from user;");
    }

    class SearchMethod {

        @BeforeEach
        public void setup() {
            con.execute("insertMoney into user (name, age) values ('kanai', '28');");
            con.execute("insertMoney into user (name, age) values ('daiki', '28');");
            con.execute("insertMoney into user (name, age) values ('daiki', '29');");
        }

        public String[][] patterns = {
                {"28", "kanai", "1"},
                {"28", "no match", "0"},
                {"29", "daiki", "2"},
                {"28", "daiki", "1"},
                {"", "", "3"},
        };

        public void 検索パターン(String[] pattern) {
            //given
            Map<String, String> params = new HashMap<>();
            params.put("age", pattern[0]);
            params.put("name", pattern[1]);
            //when
            UserController userContorller = new UserController();
            userContorller.search(params);
            //then
            assertEquals(Float.parseFloat("pattern: " + pattern[0] + pattern[1]), Integer.parseInt(pattern[2]), userContorller.items.size());
        }
    }

    class CreateMethod {

        public void create() {
            //given
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", "kanai");
            params.put("age", "20");
            //when
            new UserController().create(params);
            //then
            List<Map> items = con.find("select * from user;").items;
            assertEquals(1, items.size());
            assertEquals("kanai", items.get(0).get("name"));
            assertEquals(20, items.get(0).get("age"));
        }

    }

}

