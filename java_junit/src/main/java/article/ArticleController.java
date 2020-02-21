package article;

import database.DataBase;
import database.Records;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleController {
    public Logger logger;

    public ArticleController(Logger logger) {
        this.logger = logger;
    }

    public ArticleController() {
        this.logger = new Logger();
    }

    public String create(Map<String, String> params) {
        Article article = new Article(params.get("title"), params.get("body"));

        if (article.isInValid()) {
            return "invalid";
        }

        new DataBase().execute("insert into articles (title, body) values('" + article.title + "', '" + article.body + "');");
        logger.info(article.toString());
        return "success";
    }

    public List<Article> search(Map<String, String> params) {
        String searchWord = params.get("searchWord");
        Records records = new DataBase().find("select * from articles where title like '%" + searchWord + "%' or body like '%" + searchWord + "%';");

        List<Article> articleList = new ArrayList<>();
        records.items.forEach(item -> {
            Map<String ,String> row = (Map<String, String>) item;
            articleList.add(new Article(row.get("title"), row.get("body")));
        });
        return articleList;
    }
}
