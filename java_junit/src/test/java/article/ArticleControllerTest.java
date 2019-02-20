package article;

import database.DataBase;
import database.Records;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleControllerTest {

    @Test
    void create() {
        //given
        Map<String, String> params = new HashMap();
        params.put("title", "I love Beer.");
        //when
        new ArticleController().create(params);
        //then
        Records records = new DataBase().find("select * from articles;");
        assertEquals(1, records.size());
    }
}
