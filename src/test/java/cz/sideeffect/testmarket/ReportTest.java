package cz.sideeffect.testmarket;

import cz.sideeffect.testmarket.elements.Share;
import cz.sideeffect.testmarket.elements.Vendor;
import cz.sideeffect.testmarket.export.ExportHtml;
import cz.sideeffect.testmarket.input.DataReader;
import cz.sideeffect.testmarket.input.DataRow;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class tests generating and other operatoins with reports.
 */
public class ReportTest {

    /**
     * Performs complex testing of generating report from a CSV file.
     * Checks that the file is correctly parsed and that the report is correctly computed.
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        DataReader reader = new DataReader("src/test/resources/data.csv");
        List<DataRow> input = reader.readData();
        reader.close();

        Assert.assertEquals(28, input.size());
        
        List<Report> reports = ReportComputer.computeReports(input);
        
        Assert.assertEquals(4, reports.size());
        
        Report report = reports.get(0);
        
        Vendor acer = new Vendor("Acer");
        
        BigDecimal acerUnits = report.unitsByVendor(acer);
        Assert.assertEquals("9570.718105", acerUnits.toString());

        Share acerShare = report.shareByVendor(acer);
        Assert.assertEquals("25.4 %", acerShare.toString());

        int order = report.vendorsOneBasedIndex(acer);
        Assert.assertEquals(2, order);
        
        report = report.sortByVendor();
        order = report.vendorsOneBasedIndex(new Vendor("ASUS"));
        Assert.assertEquals(1, order);

        ExportHtml.export2File(reports, "test.html");
        Assert.assertEquals(Files.readAllLines(Paths.get("src/test/resources/gold.html")),
                            Files.readAllLines(Paths.get("test.html")));
        boolean ignored = (new File("test.html")).delete();
    }
}
