public class DeadLines extends Task{
    private final String deadLine;
    DeadLines(String content, String deadLine) {
        super(content);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + deadLine + ")";
    }
}
