package todo;

public class Todo {
    private String id;
    private String title;
    private String body;
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
        return "title: " + title;
    }
}
