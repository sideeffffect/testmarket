package cz.sideeffect.testmarket.input;

import cz.sideeffect.testmarket.elements.Country;
import cz.sideeffect.testmarket.elements.Quarter;
import cz.sideeffect.testmarket.elements.Vendor;

import java.math.BigDecimal;

/**
 * This class represents one row of the input data.
 */
public class DataRow {
    public final Country country;
    public final Quarter quarter;
    public final Vendor vendor;
    public final BigDecimal units;

    public DataRow(Country country, Quarter quarter, Vendor vendor, BigDecimal units) {
        this.country = country;
        this.quarter = quarter;
        this.vendor = vendor;
        this.units = units;
    }

    @Override
    public String toString() {
        return "(" +
                   country +
                   ", " + quarter +
                   ", " + vendor +
                   ", " + units +
                   ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataRow dataRow = (DataRow) o;

        if (!country.equals(dataRow.country)) return false;
        if (!quarter.equals(dataRow.quarter)) return false;
        if (!vendor.equals(dataRow.vendor)) return false;
        return units.equals(dataRow.units);

    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + quarter.hashCode();
        result = 31 * result + vendor.hashCode();
        result = 31 * result + units.hashCode();
        return result;
    }
}
