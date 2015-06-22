package cz.sideeffect.testmarket;

import cz.sideeffect.testmarket.elements.Share;
import cz.sideeffect.testmarket.elements.Vendor;

import java.math.BigDecimal;

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
}
