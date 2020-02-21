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

    private ArticleController articleController = new ArticleController(new FakeLogger());

    @BeforeEach
    void setup() {
        init();
    }

    @Nested
    class Create {
        private Map<String, String> params = new HashMap();
        private final String validTitle = createWord(Article.MAX_TITLE_SIZE);
        private final String inValidTitle = createWord(Article.MAX_TITLE_SIZE + 1);
        private final String validBody = createWord(Article.MAX_BODY_SIZE);
        private final String inValidBody = createWord(Article.MAX_BODY_SIZE + 1);

        @Test
        void createThenReturnCount1Record() {
            //given
            params.put("title", validTitle);
            //when
            articleController.create(params);
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals(1, records.size());
        }

        @Test
        void InValidInputReturnCount0Record() {
            //given
            params.put("title", inValidTitle);
            //when
            articleController.create(params);
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals(0, records.size());
        }

        @Test
        void createdTitle() {
            //given
            params.put("title", validTitle);
            //when
            articleController.create(params);
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals(validTitle, records.first().get("title"));
        }

        @Test
        void createdBody() {
            //given

            //when
            articleController.create(defaultParams());
            //then
            Records records = new DataBase().find("select * from articles;");
            assertEquals(validBody, records.first().get("body"));
        }

        private Map<String, String> defaultParams() {
            Map<String, String> map = new HashMap<>();
            map.put("title", validTitle);
            map.put("body", validBody);
            return map;
        }

        @Test
        void validInputReturnSuccess() {
            //given
            //when
            String createResponse = articleController.create(defaultParams());
            //then
            assertEquals("success", createResponse);
        }

        @Test
        void invalidTitleReturnInvalid() {
            //given
            params.put("title", inValidTitle);
            params.put("body", validBody);
            //when
            String createResponse = articleController.create(params);
            //then
            assertEquals("invalid", createResponse);
        }

        @Test
        void invalidBodyReturnInvalid() {
            //given
            params.put("title", validTitle);
            params.put("body", inValidBody);
            //when
            String createResponse = articleController.create(params);
            //then
            assertEquals("invalid", createResponse);
        }

        @Test
        void logger() {
            //given
            params.put("title", "hoge");
            params.put("body", "fuga");
            //when
            String createResponse = articleController.create(params);
            //then
            FakeLogger logger = (FakeLogger) articleController.logger;
            assertEquals("'hoge','fuga'", logger.getContent());
        }

        private String createWord(int wordSize) {
            String word = "";
            for (int i = 0; i < wordSize; i++) {
                word += "a";
            }
            return word;
        }

    }

    @Nested
    class Search {

        @Test
        void searchByExistTitle() {
            //given
            createArticle("12345678901234567890", "I love Beer Body.");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("searchWord", "12345678901234567890");
            List<Article> articleList = articleController.search(paramsForSearch);
            //then
            assertEquals(1, articleList.size());
        }

        @Test
        void searchByNotExistTitle() {
            //given
            createArticle("12345678901234567890", "12345678901234567890");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("searchWord", "hogehoge");
            List<Article> articleList = articleController.search(paramsForSearch);
            //then
            assertEquals(0, articleList.size());
        }

        @Test
        void searchByExistBody() {
            //given
            createArticle("12345678901234567890", "I love Beer Body.");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("searchWord", "I love Beer Body.");
            List<Article> articleList = articleController.search(paramsForSearch);
            //then
            assertEquals(1, articleList.size());
        }

        @Test
        void searchByNotExistBody() {
            //given
            createArticle("12345678901234567890", "I love Beer Body.");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("searchWord", "hogehoge");
            List<Article> articleList = articleController.search(paramsForSearch);
            //then
            assertEquals(0, articleList.size());
        }

        @Test
        void searchByPartOfWord() {
            //given
            createArticle("12345678901234567890", "I love Beer Body.");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("searchWord", "Beer");
            List<Article> articleList = articleController.search(paramsForSearch);
            //then
            assertEquals(1, articleList.size());
        }

        @Test
        void searchForMultipleArticles() {
            //given
            createArticle("12345678901234567890", "I love Beer Body.");
            createArticle("12345678901234567891", "I love Beer Body Case2.");
            createArticle("12345678901234567892", "I do not love Beef.");
            //when
            Map<String, String> paramsForSearch = new HashMap();
            paramsForSearch.put("searchWord", "Beer");
            List<Article> articleList = articleController.search(paramsForSearch);
            //then
            assertEquals(2, articleList.size());
            articleList.forEach(article -> {
                assertEquals(true, article.body.contains("Beer"));
            });

        }

        private void createArticle(String title, String body) {
            Map<String, String> paramsForCreate = new HashMap();
            paramsForCreate.put("title", title);
            paramsForCreate.put("body", body);
            articleController.create(paramsForCreate);
        }

    }

    void init() {
        new DataBase().execute("delete from articles;");
    }


    public class FakeLogger extends Logger {
        private String content;

        @Override
        public void info(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

}





















