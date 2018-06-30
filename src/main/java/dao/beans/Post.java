package dao.beans;

import java.time.LocalDate;

public class Post {
    private int postId;
    private String user;
    private String postText;
    private LocalDate created;
    private LocalDate updated;

    public Post() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (postId != post.postId) return false;
        if (!user.equals(post.user)) return false;
        return postText.equals(post.postText);
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + user.hashCode();
        result = 31 * result + postText.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", user='" + user + '\'' +
                ", postText='" + postText + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
