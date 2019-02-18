package article;

public class ArticleQuery {

    private Article article;

    public ArticleQuery(Article article) {
        this.article = article;
    }

    public String findById() {
        return select() + (" where id = " + article.id + ";");
    }

    public String like() {
        return select() + new QueryBuilder()
                .like("title", article.title)
                .like("body", article.body)
                .build();
    }

    private String select() {
        return "select * from articles";
    }
}
