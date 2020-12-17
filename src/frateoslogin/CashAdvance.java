package frateoslogin;

public class CashAdvance {
    int day,year;
    String month;
    double amount;

    public int getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getAmount() {
        return amount;
    }

    CashAdvance(int day, String month, int year, double amount){
        this.day = day;
        this.month = month;
        this.year = year;
        this.amount = amount;

    }
}
