package article;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ArticleController {

    private ArticleRepository articleRepository;
    private ObjectMapper objectMapper;

    public ArticleController() {
        articleRepository = new ArticleRepository();
        objectMapper = new ObjectMapper();
    }

    public String create(Map<String, String> params) {
        Article article = extractArticle(params);
        if (article.inValid()) return "invalid";
        articleRepository.create(article);
        return "created";
    }

    public String show(Map<String, String> params) {
        return articleRepository
                .findById(extractArticle(params))
                .toJson();
    }


    public String index() {
        return articleRepository
                .findAll()
                .toJson();
    }

    public String search(Map<String, String> params) {
        return articleRepository
                .search(extractArticle(params))
                .toJson();
    }

    private Article extractArticle(Map<String, String> params) {
        return objectMapper.convertValue(params, Article.class);
    }

}
