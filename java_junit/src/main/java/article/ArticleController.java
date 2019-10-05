package article;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ArticleController {

    private IArticle articleService;
    private ObjectMapper objectMapper;
    private ArticleRepository articleRepository;

    ArticleController(ILogger logger) {
        objectMapper = new ObjectMapper();
        articleService = new LoggerDecorator(new ArticleService(), logger);
        articleRepository = new ArticleRepository();
    }

    public String create(Map<String, String> params) {
        Article article = extractArticle(params);
        if (article.inValid()) return "invalid";
        articleService.create(article);
        return "created";
    }

    String show(Map<String, String> params) {
        return articleRepository
                .findById(extractArticle(params))
                .toJson();
    }

    String index() {
        return articleRepository
                .findAll()
                .toJson();
    }

    String search(Map<String, String> params) {
        return articleRepository
                .search(extractArticle(params))
                .toJson();
    }

    private Article extractArticle(Map<String, String> params) {
        return objectMapper.convertValue(params, Article.class);
    }

}
