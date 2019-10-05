package article;

import database.DataBase;
import database.Records;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleControllerTest {

    private DataBase dataBase;
    private ArticleController articleController = new ArticleController();

    @BeforeEach
    void beforeEach() {
        dataBase = new DataBase();
        dataBase.execute("delete from articles;");
    }

    @Nested
    class create {
        @Test
        void success() {
            //given
            //when
            String responseBody = articleController.create(params("buy beer", "good beer"));
            //then
            Records records = dataBase.find("select * from articles;");
            assertEquals(1, records.size());
            assertEquals("buy beer", records.mapTo(Article.class).get(0).title);
            assertEquals(responseBody, "created");
            assertEquals(true, records.firstMapTo(Article.class).toJson().contains("good beer"), records.firstMapTo(Article.class).toJson());
        }

        @Test
        void tooLongTitle() {
            //given
            String responseBody = articleController.create(params("buy beer xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "good beer"));
            //when
            Records records = dataBase.find("select * from articles;");
            //then
            assertEquals(0, records.size());
            assertEquals(responseBody, "invalid");
        }

        @Test
        void tooLongBody() {
            //given
            String responseBody = articleController.create(params("buy beer", "good beer xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
            //when
            Records records = dataBase.find("select * from articles;");
            //then
            assertEquals(0, records.size());
            assertEquals(responseBody, "invalid");
        }
    }

    @Test
    void show() {
        //given
        articleController.create(params("buy beer", "good beer"));
        Records records = dataBase.find("select * from articles;");
        //when
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(records.first().get("id")));
        String responseBody = articleController.show(params);
        //then
        assertEquals(true, responseBody.contains("title: buy beer"), responseBody);
    }

    @Test
    void index() {
        //given
        articleController.create(params("buy beer", "good beer"));
        articleController.create(params("buy wine", "good beer"));
        //when
        String responseBody = articleController.index();
        //then
        assertEquals(true, responseBody.contains("title: buy beer"), responseBody);
        assertEquals(true, responseBody.contains("title: buy wine"), responseBody);
    }

    @Nested
    class search {
        @BeforeEach
        void beforeEach() {
            articleController.create(params("buy beer", "good beer"));
            articleController.create(params("buy wine", "good beer"));
            articleController.create(params("buy chili wine", "bad beer"));
        }

        @Test
        void likeTitleAndBody() {
            //given
            Map<String, String> params = params("wine", "good");
            //when
            String responseBody = articleController.search(params);
            //then
            assertEquals(false, responseBody.contains("title: buy beer"), responseBody);
            assertEquals(true, responseBody.contains("title: buy wine"), responseBody);
            assertEquals(false, responseBody.contains("title: buy chili wine"), responseBody);
        }

        @Test
        void likeTitle() {
            //given
            Map<String, String> params = params("wine", "");
            //when
            String responseBody = articleController.search(params);
            //then
            assertEquals(true, responseBody.contains("title: buy wine"), responseBody);
            assertEquals(true, responseBody.contains("title: buy chili wine"), responseBody);
            assertEquals(false, responseBody.contains("title: buy beer"), responseBody);
        }

        @Test
        void likeBody() {
            //given
            Map<String, String> params = params("", "bad");
            //when
            String responseBody = articleController.search(params);
            //then
            assertEquals(false, responseBody.contains("title: buy beer"), responseBody);
            assertEquals(false, responseBody.contains("title: buy wine"), responseBody);
            assertEquals(true, responseBody.contains("title: buy chili wine"), responseBody);
        }
    }


    private Map<String, String> params(String title, String body) {
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("body", body);
        return map;
    }

}

