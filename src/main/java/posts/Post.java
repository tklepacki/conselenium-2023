package posts;
public class Post {
    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Post setAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getId() {
        return id;
    }

    public Post setId(int id) {
        this.id = id;
        return this;
    }

    private String title;
    private String author;
    private int id;

}
