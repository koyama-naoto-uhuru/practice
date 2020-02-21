package article;

class Article {
    public final String title;
    public final String body;

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public boolean isInValidBody() {
        return body != null && body.length() > 2000;
    }

    public boolean isInValidTitle() {
        return title != null && title.length() > 20;
    }

    public boolean isInValid() {
        return isInValidTitle() || isInValidBody();
    }
}
