package article;

public class ArticleService {


    public ArticleService() {
        this.articleRepository = new ArticleRepository();
    }

    public ArticleRepository articleRepository;

    public void create(Article article) {
        articleRepository.create(article);
    }

}