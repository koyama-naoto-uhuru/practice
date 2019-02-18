package article;

public class Article {
    private String id;
    public String title;
    public String body;
    private String status;
    private String createdAt;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toJson() {
        return "title: " + title + " body: " + body;
    }

    boolean inValid() {
        if (title.length() > 20) {
            return true;
        }
        if (body.length() > 50) {
            return true;
        }
        return false;
    }

    String searchQuery() {
        return new QueryBuilder().like("title", title).like("body", body).build();
    }
}
