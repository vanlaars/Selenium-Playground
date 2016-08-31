package core.testdata;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by svanlaar on 26/08/2016.
 */
public class SearchTestData {

    private String destination;
    private String check_out;
    private String check_in;
    private String occupancy;

    public SearchTestData(String destination, String check_out, String occupancy) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date tommrrow = cal.getTime();

        this.destination = destination;
        this.check_in = toddMMyy(today);
        this.check_out = toddMMyy(tommrrow);
        this.occupancy = occupancy;
    }


    public String getDestination() {
        return destination;
    }


    public String getOccupancy() {
        return occupancy;
    }

    public String getCheck_out() {
        return check_out;
    }

    public String getCheck_in() {
        return check_in;
    }

    private static String toddMMyy(Date day){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        String date = formatter.format(day);
        return date;
    }

    @Override
    public String toString() {
        return "SearchTestData{" +
                "destination='" + destination + '\'' +
                ", check_out='" + check_out + '\'' +
                ", check_in='" + check_in + '\'' +
                ", occupancy='" + occupancy + '\'' +
                '}';
    }
}
