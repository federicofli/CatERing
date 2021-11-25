import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.EventInfo;
import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import businesslogic.shift.KitchenShift;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.TaskManager;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class testArray {
    public static void main(String[] args) throws UseCaseLogicException {
        CatERing instance = CatERing.getInstance();
        User cook = User.loadUser("Marinella");
        instance.getUserManager().fakeLogin("Lidia");

        System.out.println("\nTEST CREATE MENU");
        Menu m = CatERing.getInstance().getMenuManager().createMenu("Menu Pinco Pallino");

        System.out.println("\nTEST GET EVENT INFO");
        ObservableList<EventInfo> events = CatERing.getInstance().getEventManager().getEventInfo();
        for (EventInfo e: events) {
            System.out.println(e);
            for (ServiceInfo s: e.getServices()) {
                System.out.println("\t" + s);
            }
        }
        System.out.println("");



        instance.getTaskManager().createSheet(events.get(1));
        //instance.getTaskManager().sortTask("esteem");
        KitchenShift kc = new KitchenShift(9, 10, instance.getUserManager().fakeLogin("Marco"));
        KitchenShift kc1 = new KitchenShift(11, 12, instance.getUserManager().fakeLogin("Giorgio"));
        KitchenShift kc2 = new KitchenShift(13, 14, instance.getUserManager().fakeLogin("Federico"));
        instance.getTaskManager().addKitchenShift(kc);
        instance.getTaskManager().addKitchenShift(kc1);
        instance.getTaskManager().addKitchenShift(kc2);
        instance.getTaskManager().setTask(kc, instance.getUserManager().fakeLogin("Marco"), 5, 7);
        instance.getTaskManager().setTask(kc1, instance.getUserManager().fakeLogin("Giorgio"), 4, 8);
        instance.getTaskManager().setTask(kc2, instance.getUserManager().fakeLogin("Federico"), 2, 6);
        instance.getTaskManager().modifyDoses(50, instance.getTaskManager().getTask(1));
        instance.getTaskManager().checkShiftsBoard();
        System.out.println("Stampa Turni\n");
        System.out.println(instance.getTaskManager().toStringKS());
        System.out.println("ciao");




    }
}
