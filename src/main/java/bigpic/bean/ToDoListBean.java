package bigpic.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "todoList")
@SessionScoped

public class ToDoListBean implements Serializable {
    private String title;
    private boolean remind;
    private Date remindDate;
    private String imageUrl;
    private List<ToDoThing> items = new ArrayList<>();

    public ToDoListBean() {
        this.reset();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRemind() {
        return remind;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ToDoThing> getItems() {
        return items;
    }

    public void reset() {
        this.title = "[LIST TITLE]";
        this.imageUrl = "list.jpg";
        this.remind = false;
        this.items.add(new ToDoThing("Buy milk", 5));
        this.items.add(new ToDoThing("Wash up cat", 9));
        this.items.add(new ToDoThing("Tidy room", 3));

    }

    public String getInformation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ToDo list : " + this.title);
        stringBuilder.append(", ");
        stringBuilder.append(" things in list : " + items.size());
        stringBuilder.append(", ");
        stringBuilder.append(" remind : " + this.remind);
        return stringBuilder.toString();
    }
}
