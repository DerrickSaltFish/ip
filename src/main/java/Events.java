public class Events extends Task{
    private String dates;
    Events(String content, String dates) {
        super(content);
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + dates + ")";
    }
}
