public class Report {
    private final String header;
    private final String content;
    private final String footer;

    public Report(String header, String content, String footer) {
        this.header = header;
        this.content = content;
        this.footer = footer;
    }

    public String render() {
        return header + "\n" + content + "\n" + footer;
    }
}
