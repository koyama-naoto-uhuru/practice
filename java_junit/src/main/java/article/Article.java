package article;

class Article {
    public final String title;
    public final String body;
    public static final int MAX_TITLE_SIZE = 20;
    public static final int MAX_BODY_SIZE = 2000;

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public boolean isInValidBody() {
        return body != null && body.length() > MAX_BODY_SIZE;
    }

    public boolean isInValidTitle() {
        return title != null && title.length() > MAX_TITLE_SIZE;
    }

    public boolean isInValid() {
        return isInValidTitle() || isInValidBody();
    }

    @Override
    public String toString() {
        return "'" + title + "','" + body + "'";
    }
}
