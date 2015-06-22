package cz.sideeffect.testmarket;

import cz.sideeffect.testmarket.export.ExportHtml;
import cz.sideeffect.testmarket.input.DataReader;
import cz.sideeffect.testmarket.input.DataRow;

import java.io.InputStreamReader;
import java.util.List;

/**
 * Parse a CSV from stdin, export reports to reports.html .
 */
public class Main {
    public static void main(String[] args) {
        DataReader reader = new DataReader(new InputStreamReader(System.in));
        List<DataRow> input = reader.readData();
        reader.close();

        List<Report> reports = ReportComputer.computeReports(input);

        ExportHtml.export2File(reports, "reports.html");
    }
}
