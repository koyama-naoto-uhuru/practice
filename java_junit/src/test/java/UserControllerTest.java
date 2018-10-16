import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    private DataBase con;

    @Before
    public void initDB() {
        con = new DataBase();
        con.execute("delete from user;");
    }

    @Test
    public void create() {
        //given
        Map<String, String> params = createUserMap("kanai", "20");
        //when
        new UserController().create(params);
        //then
        List<Map> list = con.find("select * from user;");
        assertEquals(1, list.size());
        assertUserMap(list.get(0), "kanai", 20);
    }

    @Test
    public void multi_create() {
        createMultiUsers();

        //then
        List<Map> list = con.find("select * from user order by id;");
        assertEquals(2, list.size());
        assertEquals("kanai", list.get(0).get("name"));
        assertEquals(20, list.get(0).get("age"));
        assertEquals("yoshimitsu", list.get(1).get("name"));
        assertEquals(30, list.get(1).get("age"));
    }

    @Test
    public void searchName() {
        createMultiUsers();

        //when
        List<Map> list = new UserController().searchName("kanai");

        //then
        assertEquals(1, list.size());
        assertEquals("kanai", list.get(0).get("name"));
        assertEquals(20, list.get(0).get("age"));
    }

    @Test
    public void searchAge() {
        createMultiUsers();

        //when
        List<Map> list = new UserController().searchAge("20");

        //then
        assertEquals(1, list.size());
        assertUserMap(list.get(0), "kanai", 20);
        assertEquals("kanai", list.get(0).get("name"));
        assertEquals(20, list.get(0).get("age"));
    }

    private void assertUserMap(Map map, String name, int age) {
        assertEquals(name, map.get("name"));
        assertEquals(age, map.get("age"));
    }

    @Test
    public void searchAgeAndName() {
        createMultiUsers();

        //when
        List<Map> list = new UserController().searchAgeAndName("20", "kanai");

        //then
        assertEquals(1, list.size());
        assertEquals("kanai", list.get(0).get("name"));
        assertEquals(20, list.get(0).get("age"));
    }

    @Test
    public void searchAgeAndName2() {
        createMultiUsers();
        new UserController().create(createUserMap("Yuito", "20"));
        //when
        List<Map> list = new UserController().searchAgeAndName("20", "Yuito");

        //then
        assertEquals(1, list.size());
        assertEquals("Yuito", list.get(0).get("name"));
        assertEquals(20, list.get(0).get("age"));
    }

    private void createMultiUsers() {
        Map<String, String> params1 = createUserMap("kanai", "20");
        Map<String, String> params2 = createUserMap("yoshimitsu", "30");
        new UserController().create(params1);
        new UserController().create(params2);

    }

    private Map<String, String> createUserMap(String name, String age) {
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("name", name);
        userMap.put("age", age);
        return userMap;
    }
}


