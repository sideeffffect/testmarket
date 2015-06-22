package cz.sideeffect.testmarket;

import cz.sideeffect.testmarket.elements.Country;
import cz.sideeffect.testmarket.elements.Quarter;
import cz.sideeffect.testmarket.elements.Share;
import cz.sideeffect.testmarket.elements.Vendor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class represents a report for a specific country and quarter.
 * 
 * A Report provides contains information about units of PCs sold by a Vendor in a Country in a Quarter of year.
 */
public class Report {
    public final Country country;
    public final Quarter quarter;
    public final List<ReportRow> lines;
    public final BigDecimal total;

    public Report(Country country, Quarter quarter, List<ReportRow> lines, BigDecimal total) {
        this.country = country;
        this.quarter = quarter;
        this.lines = lines;
        this.total = total;
    }

    /**
     * 
     * @param vendor
     * @return units sold by the specified vendor
     */
    public BigDecimal unitsByVendor(Vendor vendor){
        for(ReportRow line : lines){
            if(line.vendor.equals(vendor)){
                return line.units;
            }
        }
        throw new RuntimeException("Vendor not found in QuarterReport");
    }

    /**
     * 
     * @param vendor
     * @return share of the specified vendor
     */
    public Share shareByVendor(Vendor vendor){
        for(ReportRow line : lines){
            if(line.vendor.equals(vendor)){
                return line.share;
            }
        }
        throw new RuntimeException("Vendor not found in QuarterReport");
    }

    /**
     * 
     * @param vendor
     * @return order of the specified vendor in the report
     */
    public int vendorsOneBasedIndex(Vendor vendor){
        for(int i = 0; i < lines.size(); ++i){
            if(lines.get(i).vendor.equals(vendor)){
                return i + 1;
            }
        }
        throw new RuntimeException("Vendor not found in QuarterReport");
    }

    /**
     * 
     * @return a new report sorted by vendors' names
     */
    public Report sortByVendor(){
        return sort(new ReportRow.VendorComparator());
    }

    /**
     * 
     * @return a new report sorted by units, in descending order
     */
    public Report sortByUnits(){
        return sort(new ReportRow.UnitsComparator());
    }

    /**
     * 
     * @param comparator
     * @return a new report sorted according to the comparator
     */
    public Report sort(Comparator<ReportRow> comparator){
        List<ReportRow> newLines = new ArrayList<>(lines);
        newLines.sort(comparator);

        return new Report(country, quarter, newLines, total);
    }
}
