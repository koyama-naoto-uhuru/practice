package article;

import database.DataBase;
import database.Records;

public class ArticleRepository {
    private DataBase dataBase = new DataBase();

    Articles findAll() {
        Records records = dataBase.find(new ArticleQuery().select());
        return new Articles(records.mapTo(Article.class));
    }

    void create(Article article) {
        dataBase.execute(new ArticleQuery((article)).insert());
    }

    Articles search(Article article) {
        Records records = dataBase.find(new ArticleQuery(article).search());
        return new Articles(records.mapTo(Article.class));
    }

    Article findById(Article article) {
        Records records = dataBase.find(new ArticleQuery(article).findById());
        return records.firstMapTo(Article.class);
    }
}
