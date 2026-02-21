public class TextReportBuilder implements IReportBuilder {
    private String header = "";
    private String content = "";
    private String footer = "";

    @Override
    public void setHeader(String header) {
        this.header = "=== " + header + " ===";
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setFooter(String footer) {
        this.footer = "--- " + footer + " ---";
    }

    @Override
    public Report getReport() {
        return new Report(header, content, footer);
    }
}
