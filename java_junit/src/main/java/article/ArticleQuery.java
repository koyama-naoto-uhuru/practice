package article;

public class ArticleQuery {

    private Article article;
    private String tableName = "articles";

    public ArticleQuery(Article article) {
        this.article = article;
    }

    public ArticleQuery() {
    }

    public String findById() {
        return select() + new QueryBuilder()
                .where("id", article.id)
                .build();
    }

    public String search() {
        return select() + new QueryBuilder()
                .like("title", article.title)
                .like("body", article.body)
                .build();
    }

    public String select() {
        return "select * from " + tableName;
    }

    public String insert() {
        return "insert into " + tableName + " (title, body) values('" + article.title + "', '" + article.body + "');";
    }
}
