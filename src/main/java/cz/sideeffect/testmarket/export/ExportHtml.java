package cz.sideeffect.testmarket.export;

import cz.sideeffect.testmarket.Report;
import cz.sideeffect.testmarket.ReportRow;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

/**
 * This class exports Report instances to an HTML document.
 */
public class ExportHtml {

    /**
     * writes the HTML document to a file specified by its name
     * @param reports reports to export
     * @param outFileName name of the output file
     */
    public static void export2File(List<Report> reports, String outFileName) throws IOException {
        Writer writer = new FileWriter(outFileName);
        export2File(reports, writer);
        writer.close();
    }

    /**
     * writes the HTML document to a given Writer
     * @param reports reports to export
     * @param writer caller is responsible for closing the writer
     */
    public static void export2File(List<Report> reports, Writer writer) throws IOException {
        writer.append(export(reports));
        writer.flush();
    }

    /**
     * exports list Report instances to HTML
     * @param reports list of Reports to be exported to HTML
     * @return HTML document generated from the Reports
     */
    public static String export(List<Report> reports){
        StringBuilder sb = new StringBuilder();
        
        sb.append("<!DOCTYPE html>\n" +
                      "<html>\n" +
                      "<body>\n\n" +
                      "<style>\n" +
                      "table, th, td {\n" +
                      "    border: 1px solid black;\n" +
                      "    border-collapse: collapse;\n" +
                      "}\n" +
                      "table{\n" +
                      "    table-layout: fixed;\n" +
                      "    width: 600px;\n" +
                      "}\n" +
                      "\n" +
                      "th {\n" +
                      "    background-color: #D9D9D9;\n" +
                      "    font-weight: normal;\n" +
                      "}" +
                      "</style>\n\n");
        
        int i = 1;
        for (Report report : reports){
            sb.append("<p>");
            sb.append((report2html(i++, report.sortByUnits())));
            sb.append(("</p>\n<br/>\n"));
        }
        
        sb.append("\n" +
                      "</body>\n" +
                      "</html>\n");
        
        return sb.toString();
    }

    private static String report2html(int i,  Report report){
        StringBuilder sb = new StringBuilder();
        sb.append("Table " + i + ", PC Quarterly Market Share, " + report.country + ", " + report.quarter.format() + "\n");
        sb.append("<table>\n"
                      + "<tr>\n"
                      + "  <th>Vendor</th>\n"
                      + "  <th>Units</th>\n"
                      + "  <th>Share</th>\n"
                      + "</tr>\n");

        for(ReportRow line : report.lines){
            sb.append(reportLine2html(line));
        }

        sb.append("<tr bgcolor=\"#FFFF99\">\n"
                      + "  <td align=\"center\">Total</td>\n"
                      + "  <td align=\"center\">" + formatUnits(report.total) + "</td>\n"
                      + "  <td align=\"center\">100 %</td>\n"
                      + "</tr>\n"
                      + "</table>");

        return sb.toString();
    }
    
    private static String reportLine2html(ReportRow reportRow){
        return "<tr>\n"
                   + "  <td align=\"center\">" + reportRow.vendor             + "</td>\n"
                   + "  <td align=\"center\">" + formatUnits(reportRow.units) + "</td>\n"
                   + "  <td align=\"center\">" + reportRow.share              + "</td>\n"
                   + "</tr>\n";
    }

    private static String formatUnits(BigDecimal units){
        return String.format("%,d", units.longValue());
    }
}
