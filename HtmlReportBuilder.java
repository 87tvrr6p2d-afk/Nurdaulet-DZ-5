public class HtmlReportBuilder implements IReportBuilder {
    private String header = "";
    private String content = "";
    private String footer = "";

    @Override
    public void setHeader(String header) {
        this.header = "<h1>" + escape(header) + "</h1>";
    }

    @Override
    public void setContent(String content) {
        this.content = "<p>" + escape(content) + "</p>";
    }

    @Override
    public void setFooter(String footer) {
        this.footer = "<small>" + escape(footer) + "</small>";
    }

    @Override
    public Report getReport() {
        return new Report(header, content, footer);
    }

    private String escape(String s) {
        return s == null ? "" : s.replace("&", "&amp;")
                .replace("<", "&lt;").replace(">", "&gt;");
    }
}
