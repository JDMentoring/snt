package bigpic.bean;

import java.util.ArrayList;
import java.util.List;

public class WeekSchedule {
    private String userName;
    private List<Day> workingDays;

    public enum Day{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public WeekSchedule() {
        workingDays = new ArrayList<>();
        workingDays.add(Day.MONDAY);
        workingDays.add(Day.WEDNESDAY);
        workingDays.add(Day.FRIDAY);
        workingDays.add(Day.SATURDAY);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle(){
        return " The scheduler for "+userName;
    }

    public void addWorkingDay(Day day){
        workingDays.add(day);
    }

    public int getWorkingDayNumbers(){
        return workingDays.size();
    }

    public List<Day> getWorkingDays() {
        return workingDays;
    }

    public int getNumbersDay(){
        return 7;
    }
}
