/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myroot.driverhistory.process;

import com.myroot.driverhistory.domain.DriverHistoryDTO;
import static com.myroot.driverhistory.process.CalculateDriverScore.parseDataAndReportSummary;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jay Madathil
 */
public class CalculateDriverScoreTest {

    public CalculateDriverScoreTest() {
    }

    CalculateDriverScore calculateDriverScoreSpy;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class CalculateDriverScore.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = new String[1];
        args[0] = "C:\\Users\\S278311\\Documents\\DrivetTripData.txt";
        CalculateDriverScore.main(args);
    }

    @Test
    public void testProcess() throws Exception {
        List<DriverHistoryDTO> dhdtos = new ArrayList<>();
        List<DriverHistoryDTO> calHistoryDTOs = CalculateDriverScore.parseDataAndReportSummary(new ArrayList<>());
        assert calHistoryDTOs.size() == dhdtos.size();
    }
}
