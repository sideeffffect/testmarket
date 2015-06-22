package cz.sideeffect.testmarket.input;

import com.opencsv.CSVReader;
import cz.sideeffect.testmarket.elements.Country;
import cz.sideeffect.testmarket.elements.Quarter;
import cz.sideeffect.testmarket.elements.Vendor;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for reading and parsing the input data from supplied csv file.
 */
public class DataReader {
    
    private static String quarterOfYearRegex = "^\\s*(\\d{4})\\s*(Q|q)\\s*(1|2|3|4)\\s*$";
    private static int quarterOfYearRegex_year = 1;
    private static int quarterOfYearRegex_quarter = 3;

    private final CSVReader reader;

    /**
     * creates an instance from any Reader
     * @param reader provides the CSV input
     */
    public DataReader(Reader reader) {
        this.reader = new CSVReader(reader);
    }

    /**
     * creates an instance from the name of the CSV file, caller is responsible for closing the reader
     * @param fileName name of the CSV file
     */
    public DataReader(String fileName) {
        try {
            Reader reader = new FileReader(fileName);
            this.reader = new CSVReader(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * reads and parses the input data
     * @return list of parsed rows from the input
     */
    public List<DataRow> readData(){
        String [] nextLine;
        List<DataRow> lines = new ArrayList<>();
        try {
            nextLine = reader.readNext();
            if(! (nextLine.length == 4)
            || !  nextLine[0].equals("Country")
            || !  nextLine[1].equals("Timescale")
            || !  nextLine[2].equals("Vendor")
            || !  nextLine[3].equals("Units"))
            {
                throw new RuntimeException("Wrong format of input data.");
            }
            
            while ((nextLine = reader.readNext()) != null){
                lines.add(parseLine(nextLine));
            }
            
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * closes the Reader
     */
    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private DataRow parseLine(String[] input){
        if(input.length != 4){
            throw new RuntimeException("Wrong collumns in line, expected 4, got " + input.length);
        }
        
        Country country = new Country(input[0]);
        
        Quarter quarterOfYear;
        Matcher m = Pattern.compile(quarterOfYearRegex).matcher(input[1]);
        if(m.find()
        && m.group(quarterOfYearRegex_year) != null
        && m.group(quarterOfYearRegex_quarter) != null)
        {
            int year = Integer.parseInt(m.group(quarterOfYearRegex_year));
            int quarter = Integer.parseInt(m.group(quarterOfYearRegex_quarter));
            quarterOfYear = new Quarter(year, quarter);
        }
        else {
            throw new RuntimeException("Wrongly formatted quarter, got " + input[1]);
        }
        
        Vendor vendor = new Vendor(input[2]);
        BigDecimal units = new BigDecimal(input[3]);
        if(units.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Units must be >= 0, got " + units);
        }
        
        return new DataRow(country, quarterOfYear, vendor, units);
    }
}
