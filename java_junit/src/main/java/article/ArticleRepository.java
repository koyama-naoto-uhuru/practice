package article;

import database.DataBase;
import database.Records;

public class ArticleRepository {
    private DataBase dataBase = new DataBase();

    void create(Article article) {
        dataBase.execute(new ArticleQuery((article)).insert());
    }

    Articles findAll() {
        String query = new ArticleQuery().select();
        Records records = dataBase.find(query);
        return new Articles(records.mapTo(Article.class));
    }

    Articles search(Article article) {
        String query = new ArticleQuery(article).search();
        Records records = dataBase.find(query);
        return new Articles(records.mapTo(Article.class));
    }

    Article findById(Article article) {
        String query = new ArticleQuery(article).findById();
        Records records = dataBase.find(query);
        return records.firstMapTo(Article.class);
    }
}
