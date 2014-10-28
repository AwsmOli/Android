package Persitancy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Parsing Setupdata from CSV-File
 * Created by Oliver on 12.10.2014.
 */
public class CSVParser {
    public static final String UNDERSTEER = "Understeer";
    public static final String OVERSTEER = "Oversteer";
    public static final String TRACTION_ROLL = "Traction Roll";
    public static final String STRAIGHT_LINE_STABILITY = "Straight Line Stability";
    public static final String STEERING_RESPONSE = "Steering Response";

    public static final String CORNER_ENTRY = "Corner Entry";
    public static final String MID_CORNER = "Mid Corner";
    public static final String CORNER_EXIT = "Corner Exit";
    public static final String BREAKING = "Breaking";

    public static final String OFF_THROTTLE = "Off";
    public static final String ON_THROTTLE = "On";



    private static final String HANDLING_CHARACTERISTIC = "Handling Characteristic";
    private static final String POSITION = "Position";
    private static final String THROTTLE_POSITION = "Throttle Position";


    public static List<Problem> readCSV(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<Problem> problemList = new ArrayList<Problem>();
        //Read Header
        String line = reader.readLine();
        String[] headline =  line.split(";");
        //Read IncDexValues
        line = reader.readLine();
        String[] incDecValues = line.split(";");

        while ((line = reader.readLine()) != null) {
            String[] RowData = line.split(";");
            String characteristic = RowData[0].trim();
            String position = RowData[1].trim();
            String throttlePosition = RowData[2].trim();

            boolean onThrottle = throttlePositionToBoolen(throttlePosition);

            Problem p = new Problem(characteristic,position,onThrottle);
            for(int i = 3; i < RowData.length-1; i++) {
                String value = RowData[i].trim();
                Suggestion s = new Suggestion(getOrder(value),getSettingByColIndex(i,headline,incDecValues), getChangeDirection(value));
                p.addSuggestion(s);
            }
            problemList.add(p);
        }
        System.out.println("Found " + problemList.size() + " possible Problems!");
        return problemList;
    }

    private static Boolean getChangeDirection(String value) {
        System.out.println(value);
        System.out.println(getOrder(value) < 0);
        return getOrder(value) > 0;
    }

    private static int getOrder(String value) {
        if(!value.equals("")) return Integer.parseInt(value);

        return -1;
    }

    private static Setting getSettingByColIndex(int i, String[] headline, String[] incDecValues) {
        Setting retVal = new Setting();
        retVal.setName(headline[i].trim());
        incDecValues = incDecValues[i].split(",");
        retVal.setIncPrefix(incDecValues[0].trim());
        retVal.setDecPrefix((incDecValues[1].trim()));
        return retVal;
    }

    private static boolean throttlePositionToBoolen(String throttlePosition) {
        return !throttlePosition.equals(OFF_THROTTLE) && throttlePosition.equals(ON_THROTTLE);

    }
}
