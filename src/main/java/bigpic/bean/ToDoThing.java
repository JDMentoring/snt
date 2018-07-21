package bigpic.bean;

public class ToDoThing {
    private String subscription;
    private int priority;
    private boolean isDone;

    public ToDoThing() {
    }

    public ToDoThing(String subscription, int priority) {
        this.subscription = subscription;
        this.priority = priority;
        this.isDone = false;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "ToDoThing{" +
                "subscription='" + subscription + '\'' +
                ", priority=" + priority +
                ", isDone=" + isDone +
                '}';
    }
}
