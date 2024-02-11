package entities;

public class Photo {
    private int id;
    private String link;

    public Photo(int id, String link){
        setId(id);
        setLink(link);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
