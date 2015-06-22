package cz.sideeffect.testmarket.elements;

import java.time.Month;
import java.time.YearMonth;

/**
 * This class represents a quarter some year.
 */
public class Quarter implements Comparable<Quarter> {
    private final YearMonth yearMonth;

    /**
     * 
     * @param year the year
     * @param quarter the quarter
     */
    public Quarter(int year, int quarter){
        Month month;
        if(quarter == 1){
            month = Month.JANUARY;
        }
        else if(quarter == 2){
            month = Month.APRIL;
        }
        else if(quarter == 3){
            month = Month.JULY;
        }
        else if(quarter == 4){
            month = Month.OCTOBER;
        }
        else {
            throw new IllegalArgumentException("Quarter must be one of 1, 2, 3, 4; but got " + quarter);
        }
        yearMonth = YearMonth.of(year, month);
    }
    
    private int quater(){
        return ((yearMonth.getMonthValue() - 1)/3) + 1;
    }

    @Override
    public String toString() {
        return yearMonth.getYear() + " Q" + quater();
    }
    
    public String format(){
        int year = yearMonth.getYear() % 100;
        return quater() + "Q" + year;
    }

    @Override
    public int compareTo(Quarter quarter) {
        return yearMonth.compareTo(quarter.yearMonth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quarter that = (Quarter) o;

        return yearMonth.equals(that.yearMonth);

    }

    @Override
    public int hashCode() {
        return yearMonth.hashCode();
    }
}
