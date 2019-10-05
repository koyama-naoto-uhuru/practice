package article;

public class LoggerDecorator implements IArticleService {
    private ILogger logger;
    private ArticleService articleService;

    LoggerDecorator(ArticleService articleService, ILogger logger) {
        this.articleService = articleService;
        this.logger = logger;
    }

    @Override
    public void create(Article article) {
        this.articleService.create(article);
        logger.info("create log");
    }
}