package article;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.DataBase;
import database.Records;

import java.util.Map;

public class ArticleController {

    private DataBase dataBase = new DataBase();

    public String create(Map<String, String> params) {
        Article article = new ObjectMapper().convertValue(params, Article.class);
        if (article.inValid()) return "invalid";
        dataBase.execute("insert into articles (title, body) values('" + article.title + "', '" + article.body + "');");
        return "created";
    }

    public String show(Map<String, String> params) {
        Article article = new ObjectMapper().convertValue(params, Article.class);
        Records records = dataBase.find(new ArticleQuery(article).findById());
        return records.firstMapTo(Article.class).toJson();
    }


    public String index() {
        Records records = dataBase.find(new ArticleQuery().select());
        return new Articles(records.mapTo(Article.class)).toJson();
    }

    public String search(Map<String, String> params) {
        Article article = new ObjectMapper().convertValue(params, Article.class);
        Records records = dataBase.find(new ArticleQuery(article).search());
        return new Articles(records.mapTo(Article.class)).toJson();
    }

}
