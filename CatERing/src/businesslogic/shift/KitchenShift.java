package businesslogic.shift;

import businesslogic.user.User;

import java.sql.Time;

public class KitchenShift extends Shift {
    private User user;

    public KitchenShift(int startTime, int endTime, User user) {
        super(startTime, endTime);
        this.user = user;
    }

    public String toStringKitchenShift() {
        String s = "Start Time: " + this.getStartTime() + " End Time: " + this.getEndTime() + " User: " + this.user;
        return s;
    }
}
