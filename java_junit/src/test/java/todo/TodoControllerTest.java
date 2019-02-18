package todo;

import database.DataBase;
import database.Records;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoControllerTest {

    private DataBase dataBase;

    @BeforeEach
    void beforeEach() {
        dataBase = new DataBase();
        dataBase.execute("delete from todos;");
    }

    @Test
    void create() {
        //given
        String responseBody = new TodoController().create(params("buy beer"));
        //when
        Records records = dataBase.find("select * from todos;");
        //then
        assertEquals(1, records.size());
        assertEquals(responseBody, "created");
    }

    @Test
    void show() {
        //given
        new TodoController().create(params("buy beer"));
        Records records = dataBase.find("select * from todos;");
        //when
        String responseBody = new TodoController().show((Integer) records.first().get("id"));
        //then
        assertEquals(true, responseBody.contains("title: buy beer"), responseBody);
    }

    @Test
    void index() {
        //given
        new TodoController().create(params("buy beer"));
        new TodoController().create(params("buy wine"));
        //when
        String responseBody = new TodoController().index();
        //then
        assertEquals(true, responseBody.contains("title: buy beer"), responseBody);
        assertEquals(true, responseBody.contains("title: buy wine"), responseBody);
    }


    private Map<String, String> params(String title) {
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        return map;
    }

}

