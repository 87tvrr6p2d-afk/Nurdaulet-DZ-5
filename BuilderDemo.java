public class BuilderDemo {
    public static void run() {
        ReportDirector director = new ReportDirector();

        IReportBuilder textBuilder = new TextReportBuilder();
        director.constructReport(textBuilder, "Sales", "Total: 1,000,000 KZT", "2026");
        Report textReport = textBuilder.getReport();
        System.out.println("TEXT REPORT:\n" + textReport.render());

        IReportBuilder htmlBuilder = new HtmlReportBuilder();
        director.constructReport(htmlBuilder, "Sales", "Total: 1,000,000 KZT", "2026");
        Report htmlReport = htmlBuilder.getReport();
        System.out.println("\nHTML REPORT:\n" + htmlReport.render());
    }
}
