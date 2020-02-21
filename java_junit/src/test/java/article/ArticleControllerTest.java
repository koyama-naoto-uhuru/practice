package article;

import database.DataBase;
import database.Records;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleControllerTest {

    @BeforeEach
    void setup(){
        init();
    }

    @Nested
    class Create {

        @Test
        void createThenReturnCount1Record() {
            //given
            Map<String, String> params = new HashMap();
            params.put("title", "I love Beer.");
            //when
            new ArticleController().create(params);
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals(1, records.size());
        }

        @Test
        void InValidInputReturnCount0Record() {
            //given
            Map<String, String> params = new HashMap();
            params.put("title", getInvalidTitle());
            //when
            new ArticleController().create(params);
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals(0, records.size());
        }

        @Test
        void createdTitle() {
            //given
            Map<String, String> params = new HashMap();
            params.put("title", "I love Beer.");
            //when
            new ArticleController().create(params);
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals("I love Beer.", records.first().get("title"));
        }

        @Test
        void createdBody() {
            //given
            Map<String, String> params = new HashMap();
            params.put("title", "I love Beer.");
            params.put("body", "I love Beer Body.");
            //when
            new ArticleController().create(params);
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals("I love Beer Body.", records.first().get("body"));
        }

        @Test
        void validInputReturnSuccess() {
            //given
            Map<String, String> params = new HashMap();
            params.put("title", "I love Beer.");
            params.put("body", "I love Beer Body.");
            //when
            String createResponse = new ArticleController().create(params);
            //then
            assertEquals("success", createResponse);
        }

        @Test
        void invalidTitleReturnInvalid() {
            //given
            Map<String, String> params = new HashMap();
            params.put("title", getInvalidTitle());
            params.put("body", "I love Beer Body.");
            //when
            String createResponse = new ArticleController().create(params);
            //then
            assertEquals("invalid", createResponse);
        }

        @Test
        void invalidBodyReturnInvalid() {
            //given
            Map<String, String> params = new HashMap();
            params.put("title", "12345678901234567890");
            params.put("body", createInvalidBody());
            //when
            String createResponse = new ArticleController().create(params);
            //then
            assertEquals("invalid", createResponse);
        }

        private String getInvalidTitle() {
            return "123456789012345678901";
        }

        private String createInvalidBody() {
            String invalidBody = "";
            for (int i = 0; i < 2001; i++) {
                invalidBody += "a";
            }
            return invalidBody;
        }

    }

    @Nested
    class Search {
        @Test
        void searchArticleByExistTitle() {
            //given
            createArticle("12345678901234567890", "I love Beer Body.");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("title", "12345678901234567890");
            List<Article> articleList = new ArticleController().search(paramsForSearch);
            //then
            assertEquals(1, articleList.size());
        }

        @Test
        void searchArticleByNotExistTitle() {
            //given
            createArticle("12345678901234567890", "12345678901234567890");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("title", "hogehoge");
            List<Article> articleList = new ArticleController().search(paramsForSearch);
            //then
            assertEquals(0, articleList.size());
        }

        private void createArticle(String title, String body) {
            Map<String, String> paramsForCreate = new HashMap();
            paramsForCreate.put("title", title);
            paramsForCreate.put("body", body);
            String createResponse = new ArticleController().create(paramsForCreate);
        }
    }
    void init() {
        new DataBase().execute("delete from articles;");
    }
}
