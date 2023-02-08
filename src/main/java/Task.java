public class Task {
    private final boolean mark;
    private final String content;

    public Task(String content) {
        this.content = content;
        this.mark = false;
    }

    private Task(String content, boolean mark) {
        this.content = content;
        this.mark = mark;
    }

    public Task markTast() {
        return new Task(this.content, true);
    }

    public Task unmarkTask() {
        return new Task(this.content, false);
    }

    @Override
    public String toString() {
        String done;
        if(mark) {
            done = "[X]";
        } else {
            done = "[ ]";
        }
        return(done + " " + content);
    }

}
