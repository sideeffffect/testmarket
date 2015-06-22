package cz.sideeffect.testmarket.export;

import cz.sideeffect.testmarket.Report;
import cz.sideeffect.testmarket.ReportRow;

/**
 * This class exports Report instances to an Excel OOXML document.
 */
public class ExportExcel {

    /**
     * converts a Report into an Excel OOXML document, uses report2xml, adds appropriate start/end of the XML document and performs compression
     * @param report
     * @return
     */
    private static byte[] report2bin(Report report){
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * converts a whole report into a XML fragment, uses reportLine2xml
     * @param report
     * @return
     */
    private static String report2xml(Report report){
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * converts one line in report to a XML fragment
     * 
     * @param reportRow
     * @return
     */
    private static String reportLine2xml(ReportRow reportRow){
        throw new UnsupportedOperationException("Method not implemented");
    }
}
