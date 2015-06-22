package cz.sideeffect.testmarket;

import cz.sideeffect.testmarket.elements.Share;
import cz.sideeffect.testmarket.elements.Vendor;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * This class represents a row of a Report.
 */
public class ReportRow {
    public final Vendor vendor;
    public final BigDecimal units;
    public final Share share;

    public ReportRow(Vendor vendor, BigDecimal units, Share share) {
        this.vendor = vendor;
        this.units = units;
        this.share = share;
    }

    @Override
    public String toString() {
        return "(" +
                   vendor +
                   ", " + units +
                   ", " + share +
                   ')';
    }

    /**
     * Class for comparing ReportRow instances by vendor
     */
    public static class VendorComparator implements Comparator<ReportRow> {
        @Override
        public int compare(ReportRow r1, ReportRow r2) {
            return r1.vendor.compareTo(r2.vendor);
        }
    }

    /**
     * Class for comparing ReportRow instances by units, in descending order
     */
    public static class UnitsComparator implements Comparator<ReportRow> {
        @Override
        public int compare(ReportRow r1, ReportRow r2) {
            return r2.units.compareTo(r1.units);
        }
    }
}
