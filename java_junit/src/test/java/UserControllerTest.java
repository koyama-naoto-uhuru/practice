import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {

        // user table: id, name, age

        @Test
        public void create() {
            //given
            DataBase con = new DataBase();
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", "tanaka");
            params.put("age", "20");
            //when
//            new UserController().create(params);
            //then
            List<Map> list = con.find("select * from user;");
            assertEquals(1, list.size());
        }

}

