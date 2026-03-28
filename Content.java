package cms.model;

import java.sql.Timestamp;

public class Content {
    private int id;
    private String title;
    private String body;
    private Timestamp createdAt;

    public Content() {}

    public Content(int id, String title, String body, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    // convenience constructor if createdAt not needed
    public Content(int id, String title, String body) {
        this(id, title, body, null);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
