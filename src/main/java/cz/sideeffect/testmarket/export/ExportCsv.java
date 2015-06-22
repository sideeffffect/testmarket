package cz.sideeffect.testmarket.export;

import cz.sideeffect.testmarket.Report;
import cz.sideeffect.testmarket.ReportRow;
import cz.sideeffect.testmarket.elements.Country;
import cz.sideeffect.testmarket.elements.Quarter;

/**
 * This class exports Report instances to a CSV document. 
 */
public class ExportCsv {

    /**
     * converts a report into a list of lines of CSVs, uses reportLine2csv
     * @param report
     * @return
     */
    private static String report2csv(Report report){
        throw new UnsupportedOperationException("Method not implemented");
    }
    
    /**
     * converts one line in report to one line of a future CSV
     * 
     * country,quarter,vendor,units,share
     * 
     * @param country
     * @param quarter
     * @param reportRow
     * @return
     */
    private static String reportLine2csv(Country country, Quarter quarter, ReportRow reportRow){
        throw new UnsupportedOperationException("Method not implemented");
    }
}
