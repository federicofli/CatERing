package businesslogic.task;

import businesslogic.event.EventInfo;
import businesslogic.shift.KitchenShift;
import businesslogic.user.User;

import java.sql.Time;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class SummarySheet {
    private User owner;
    private EventInfo event;
    private ArrayList<Task> tasklist;
    private ArrayList<KitchenShift> kitchenShiftsList;

    public SummarySheet(EventInfo event, User owner) {
        this.event = event;
        this.owner = owner;
        this.tasklist = new ArrayList<Task>();
        this.kitchenShiftsList = new ArrayList<KitchenShift>();
    }

    public void addTask(Task task) {
        tasklist.add(task);
    }

    public void sortEsteem() {
        Collections.sort(tasklist, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getEsteem() == o2.getEsteem()) {
                    return 0;
                }
                return o1.getEsteem() < o2.getEsteem() ? -1 : 1;
            }
        });
    }

    public void sortDoses() {
        Collections.sort(tasklist, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getDoses() == o2.getDoses()) {
                    return 0;
                }
                return o1.getDoses() < o2.getDoses() ? -1 : 1;
            }
        });
    }

    public Task getTasklist(int index) {
        Task task = this.tasklist.get(index);
        return task;
    }

}
