package article;

public class ArticleQuery {

    private Article article;

    public ArticleQuery(Article article) {
        this.article = article;
    }

    public ArticleQuery() {
    }

    public String findById() {
        return select() + (" where id = " + article.id + ";");
    }

    public String search() {
        return select() + new QueryBuilder()
                .like("title", article.title)
                .like("body", article.body)
                .build();
    }

    public String select() {
        return "select * from articles";
    }
}
