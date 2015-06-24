package cz.sideeffect.testmarket;

import cz.sideeffect.testmarket.elements.Country;
import cz.sideeffect.testmarket.elements.Quarter;
import cz.sideeffect.testmarket.elements.Share;
import cz.sideeffect.testmarket.elements.Vendor;
import cz.sideeffect.testmarket.input.DataRow;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is used for computing reports. 
 */
public class ReportComputer {

    /**
     * Computes reports from the input
     * @param inputRows list of individual parsed input rows
     * @return reports, in a flat list
     */
    public static List<Report> computeReports(List<DataRow> inputRows){
        List<Report> reports = new ArrayList<>();
        SortedMap<Country, SortedMap<Quarter, Report>> structured = computeReportsStructured(inputRows);
        
        for(SortedMap<Quarter, Report> map : structured.values()){
            reports.addAll(map.values().stream().collect(Collectors.toList()));
        }
        return reports;
    }

    /**
     * Computes reports from the input
     * @param inputRows list of individual parsed input rows
     * @return reports, structured by Country and Quarter
     */
    public static SortedMap<Country, SortedMap<Quarter, Report>> computeReportsStructured(List<DataRow> inputRows){
        SortedMap<Country, List<DataRow>> inputRowsByCountry = inputByCountry(inputRows);
        SortedMap<Country, SortedMap<Quarter, Report>> reportsByCountry = new TreeMap<>();
        
        for(Country country : inputRowsByCountry.keySet()) {
            SortedMap<Quarter, List<DataRow>> inputRowsByQuarter = inputByQuarter(inputRowsByCountry.get(country));

            SortedMap<Quarter, Report> reportsByQuarter = new TreeMap<>();
            for (Quarter quarter : inputRowsByQuarter.keySet()) {
                Report report = computeReport(country, quarter, inputRowsByQuarter.get(quarter));
                reportsByQuarter.put(quarter, report);
            }
            
            reportsByCountry.put(country, reportsByQuarter);
        }
        
        return reportsByCountry;
    }

    private static SortedMap<Quarter, List<DataRow>> inputByQuarter(List<DataRow> inputRows){
        SortedMap<Quarter, List<DataRow>> inputRowsByQ = new TreeMap<>();

        for(DataRow row : inputRows){
            if(inputRowsByQ.containsKey(row.quarter)){
                List<DataRow> rowsForQ = inputRowsByQ.get(row.quarter);
                rowsForQ.add(row);
            }
            else {
                List<DataRow> rowSingleton = new ArrayList<>();
                rowSingleton.add(row);
                inputRowsByQ.put(row.quarter, rowSingleton);
            }
        }

        return inputRowsByQ;
    }

    private static SortedMap<Country, List<DataRow>> inputByCountry(List<DataRow> inputRows) {
        SortedMap<Country, List<DataRow>> inputRowsByC = new TreeMap<>();

        for(DataRow row : inputRows){
            if(inputRowsByC.containsKey(row.country)){
                List<DataRow> rowsForQ = inputRowsByC.get(row.country);
                rowsForQ.add(row);
            }
            else {
                List<DataRow> rowSingleton = new ArrayList<>();
                rowSingleton.add(row);
                inputRowsByC.put(row.country, rowSingleton);
            }
        }

        return inputRowsByC;
    }

    /**
     * Computes a Report for the given Country and Quarter from the given rows
     * 
     * @param country the Country for which the Report is being computed
     * @param quarter the Quarter for which the Report is being computed
     * @param inputRows the rows of parsed input, only those corresponding to the given Country and Quarter
     * @return the computed Report
     */
    private static Report computeReport(Country country, Quarter quarter, List<DataRow> inputRows){
        SortedMap<Vendor,BigDecimal> unitsByVendor = new TreeMap<>();
        BigDecimal total = new BigDecimal(0);
        
        for(DataRow row : inputRows){
            BigDecimal oldUnits;
            if(unitsByVendor.containsKey(row.vendor)){
                // this causes to duplicit (country,quarter,vendor) rows to add up units
                // the other option is to throw an exception
                oldUnits = unitsByVendor.get(row.vendor);
            }
            else {
                oldUnits = new BigDecimal(0);
            }
            BigDecimal newUnits = oldUnits.add(row.units);
            unitsByVendor.put(row.vendor, newUnits);

            total = total.add(row.units);
        }
        
        List<ReportRow> reportRows = new ArrayList<>();
        for(Map.Entry<Vendor,BigDecimal> entry : unitsByVendor.entrySet()){
            Share share;
            if(!total.equals(new BigDecimal(0))) {
                share = new Share(entry.getValue(), total);
            }
            else {
                // to cover pathological input where the total = 0
                // we don't want to divide by 0
                // the other option is to throw an exception
                share = new Share(0);
            }
            ReportRow reportRow = new ReportRow(entry.getKey(), entry.getValue(), share);
            reportRows.add(reportRow);
        }
        
        return new Report(country, quarter, reportRows, total).sortByUnits();
    }
}
