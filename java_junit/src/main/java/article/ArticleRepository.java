package article;

import database.DataBase;
import database.Records;

public class ArticleRepository {
    private DataBase dataBase = new DataBase();

    public Articles findAll() {
        Records records = dataBase.find(new ArticleQuery().select());
        return new Articles(records.mapTo(Article.class));
    }
}
