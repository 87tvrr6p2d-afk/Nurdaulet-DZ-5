public class ReportDirector {
    public void constructReport(IReportBuilder builder, String header, String content, String footer) {
        builder.setHeader(header);
        builder.setContent(content);
        builder.setFooter(footer);
    }
}
