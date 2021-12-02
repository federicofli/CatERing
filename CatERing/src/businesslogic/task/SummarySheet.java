package businesslogic.task;

import businesslogic.event.EventInfo;
import businesslogic.event.ServiceInfo;
import businesslogic.shift.KitchenShift;
import businesslogic.shift.ServiceShift;
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
    private ServiceInfo service;


    public SummarySheet(EventInfo event, User owner, ServiceInfo service) {
        this.event = event;
        this.owner = owner;
        this.service = service;
        this.tasklist = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasklist.add(task);
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
