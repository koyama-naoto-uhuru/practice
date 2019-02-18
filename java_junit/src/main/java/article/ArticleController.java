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

    public String show(int id) {
        Records records = dataBase.find("select * from articles where id = " + id + ";");
        return records.firstMapTo(Article.class).toJson();
    }

    public String index() {
        Records records = dataBase.find("select * from articles;");
        return new Articles(records.mapTo(Article.class)).toJson();
    }

}
