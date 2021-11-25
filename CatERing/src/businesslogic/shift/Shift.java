package businesslogic.shift;

import java.sql.Time;

public class Shift {
    private int id;
    private int startTime;
    private int endTime;
    private String date;
    private String location;
    private boolean full;

    public Shift(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }
    public int getStartTime() {
        return startTime;
    }
    public int getEndTime() {
        return endTime;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }


}
