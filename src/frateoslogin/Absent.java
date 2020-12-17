package frateoslogin;

public class Absent{
    int day,year;
    String month;

    Absent(int day, String month, int year){
        this.day = day;
        this.month = month;
        this.year = year;

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
}
