package core.testdata;

import org.openqa.selenium.By;

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
    private String hotelName;
    private String hotelAddress;

    public SearchTestData(String destination, int check_out, String occupancy) {
        Calendar cal = Calendar.getInstance();

        // print current date
        Date checkIn = cal.getTime();

        // add 7 days to the calendar
        cal.add(Calendar.DATE, check_out);
        Date checkOut = cal.getTime();

        this.destination = destination;
        this.check_in = toddMMyy(checkIn);
        this.check_out = toddMMyy(checkOut);
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(day);
        return date;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }


    @Override
    public String toString() {
        return "SearchTestData{" +
                "destination='" + destination + '\'' +
                ", check_out='" + check_out + '\'' +
                ", check_in='" + check_in + '\'' +
                ", occupancy='" + occupancy + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                '}';
    }
}
