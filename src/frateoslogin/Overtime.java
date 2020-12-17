package frateoslogin;

public class Overtime {
    int day,year, hours;
    String month;

    Overtime(int day, String month, int year, int hours){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
    }

    public int getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHours() {
        return hours;
    }
}
