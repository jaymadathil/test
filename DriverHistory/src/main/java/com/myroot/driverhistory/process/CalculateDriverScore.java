/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myroot.driverhistory.process;

import com.myroot.driverhistory.domain.DriverDTO;
import com.myroot.driverhistory.domain.DriverHistoryDTO;
import com.myroot.driverhistory.domain.TripDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Jay Madathil
 */
public class CalculateDriverScore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        try {
            processFile(args);

        } catch (IOException ex) {
            Logger.getLogger(CalculateDriverScore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CalculateDriverScore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private static void processFile(String[] args) throws IOException, Exception {
        Path inFile = null;
        if (args.length > 0) {
            inFile = Paths.get(args[0]);
        }
        if (null == inFile) {
            System.out.println("Enter file name");
            Scanner scanner = new Scanner(System.in);
            inFile = Paths.get(scanner.nextLine());
        }

        List<String> lines = Files.readAllLines(inFile);
        parseDataAndReportSummary(lines);

    }

    public static List<DriverHistoryDTO> parseDataAndReportSummary(List<String> lines) throws Exception {
        List<DriverDTO> drivers = new ArrayList<>();
        List<TripDTO> trips = new ArrayList<>();

        //** Read data, build object lists
        for (String row : lines) {
            String[] columns = row.split("\\s");
            String rowType = columns[0].trim();

            if ("Driver".equalsIgnoreCase(rowType)) {
                //*** Driver record
                DriverDTO driver = new DriverDTO(columns[1].trim());
                drivers.add(driver);
            }
            if ("Trip".equalsIgnoreCase(rowType)) {
                //** Trip record
                trips.add(addTrip(columns));
            }
        }

        //** Generate report
        List<DriverHistoryDTO> dhdtoList = reportDriverHistory(trips, drivers);
        
        return dhdtoList;
    }

    private static List<DriverHistoryDTO> reportDriverHistory(List<TripDTO> trips, List<DriverDTO> drivers) throws Exception {
        Map<DriverDTO, List<TripDTO>> tripsByDriverMap = trips.stream().collect(Collectors.groupingBy(TripDTO::getDriver));
        StringBuilder historySB = new StringBuilder();
        List<DriverHistoryDTO> dhdtoList = new ArrayList<>();

        for (DriverDTO d : tripsByDriverMap.keySet()) {
            List<TripDTO> tripsDriver = tripsByDriverMap.get(d);
            BigDecimal totalDuration = tripsDriver.stream().map(TripDTO::getDuration).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalMiles = tripsDriver.stream().map(TripDTO::getMiles).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal avgSpeed = (totalMiles.divide(totalDuration, 2, RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(60)).setScale(0, RoundingMode.HALF_UP);
            for (ListIterator<DriverDTO> drIter = drivers.listIterator(); drIter.hasNext();) {

                DriverDTO driver = drIter.next();
                if (driver.equals(d)) {
                    historySB = historySB.append(d.getDriverName()).append(": ").append(totalMiles).append(" miles @ ").append(avgSpeed).append(" mph").append(System.lineSeparator());
                    drIter.remove();

                    //*** Add history object to list for future reporting
                    dhdtoList.add(new DriverHistoryDTO(driver, totalMiles, avgSpeed));
                }
            }
        }
        for (DriverDTO idleDriver : drivers) {
            historySB = historySB.append(idleDriver.getDriverName()).append(": ").append("0 miles").append(System.lineSeparator());
        }

        System.out.println(historySB);
        return dhdtoList;
    }

    private static TripDTO addTrip(String[] columns) throws ParseException {
        TripDTO trip = new TripDTO();
        trip.setDriver(new DriverDTO(columns[1].trim()));
        trip.setStartTime(columns[2].trim());
        trip.setEndTime(columns[3].trim());
        trip.setMiles(new BigDecimal(columns[4].trim()).setScale(2));
        trip.setDuration(BigDecimal.valueOf(getDuration(trip.getStartTime(), trip.getEndTime())));
        return trip;
    }

    private static long getDuration(String from, String to) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date start = dateFormat.parse(from);
        Date end = dateFormat.parse(to);
        Long duration = end.getTime() - start.getTime();
        Long minutes = duration / (1000 * 60);
        return minutes;
    }

}
