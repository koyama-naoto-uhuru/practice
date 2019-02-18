package article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Articles {
    List<Article> items = new ArrayList();

    public Articles(List<Article> items) {
        this.items = items;
    }

    public String toJson() {
        return items.stream().map(Article::toJson).collect(Collectors.toList()).toString();
    }


}
