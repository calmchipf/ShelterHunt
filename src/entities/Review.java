package entities;

public class Review {
    private int id;
    private String content;

    public Review(int id, String content) {
        setId(id);
        setContent(content);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
