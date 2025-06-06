package part11.posts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getViews() {
        return views;
    }

    public Post setViews(int views) {
        this.views = views;
        return this;
    }

    public String getId() {
        return id;
    }

    public Post setId(String id) {
        this.id = id;
        return this;
    }

    private String title;
    private int views;
    private String id;
}
