package frateoslogin;

public class EmployeeFinance {
    String month;
    Double salary, cashAdvance;
    int year, absences, hours;

    public EmployeeFinance(){
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setCashAdvance(Double cashAdvance) {
        this.cashAdvance = cashAdvance;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getMonth() {
        return month;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getCashAdvance() {
        return cashAdvance;
    }

    public int getYear() {
        return year;
    }

    public int getAbsences() {
        return absences;
    }

    public int getHours() {
        return hours;
    }
}
