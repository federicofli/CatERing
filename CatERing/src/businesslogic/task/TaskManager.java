package businesslogic.task;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.EventInfo;
import businesslogic.shift.KitchenShift;
import businesslogic.shift.Shift;
import businesslogic.user.User;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<SummarySheet> openSheet;
    private ArrayList<TaskEventReceiver> eventReceivers;
    private SummarySheet currentSheet;
    private Task currentTask;
    private ArrayList<KitchenShift> kitchenShiftsList;


    public TaskManager() {
        openSheet = new ArrayList<>();
        eventReceivers = new ArrayList<>();
        this.kitchenShiftsList = new ArrayList<KitchenShift>();
    }

    public SummarySheet createSheet(EventInfo event) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        SummarySheet fr = new SummarySheet(event, user);
        this.setCurrentSheet(fr);
        this.notifySheetAdded(fr);
        return fr;
    }

    public Task setTask(Shift shift, User cook, int esteem, int doses) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        /*if(!user.isChef()) {
            throw new UseCaseLogicException();
        }*/
        Task task = new Task(shift, cook, esteem, doses);
        this.addTask(task);
        this.notifyTaskAdded(task);
        this.setCurrentTask(task);
        return task;
    }

    public Task setTask(Shift shift, User cook) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        Task task = new Task(shift, cook);
        this.addTask(task);
        this.notifyTaskAdded(task);
        this.setCurrentTask(task);
        return task;
    }

    public Task setTask(Shift shift, int esteem, int doses) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        Task task = new Task(shift, esteem, doses);
        this.addTask(task);
        this.notifyTaskAdded(task);
        this.setCurrentTask(task);
        return task;
    }

    public void addTask(Task task) {
        currentSheet.addTask(task);
    }

    public Task modifyCook(User cook, Task task) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        currentTask.setCook(cook);
        this.notifyCookUpdate(task);
        return task;
    }

    public Task modifyShift(Shift shift, Task task) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        currentTask.setShift(shift);
        this.notifyShiftUpdate(task);
        return task;
    }

    public Task modifyEsteem(int esteem, Task task) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        currentTask.setEsteem(esteem);
        this.notifyEsteemUpdate(task);
        return task;
    }

    public Task modifyDoses(int doses, Task task) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        /*if(!user.isChef()) {
            throw new UseCaseLogicException();
        }*/
        currentTask.setDoses(doses);
        this.notifyDosesUpdate(task);
        return task;
    }

    public void deleteTask(Task task) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        this.notifyTaskDeleted(task);
    }

    public Task setReady(Task task) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        currentTask.setReady();
        this.notifyReadysUpdate(task);
        return task;
    }

    public void sortTask(String sort) {
        String esteem = "esteem";
        if(sort.equals(esteem)) {
            currentSheet.sortEsteem();
        }
        else {
            currentSheet.sortDoses();
        }
    }

    public void addKitchenShift(KitchenShift kc) {
        kitchenShiftsList.add(kc);
    }

    public String toStringKS() {
        String result = "";
        for(int i = kitchenShiftsList.size()-1; i>=0; i--) {
            result = "**Shift: " + kitchenShiftsList.get(i).toStringKitchenShift() + "\n" + result;
        }
        return result;
    }

    public ArrayList<KitchenShift> checkShiftsBoard() {
        return kitchenShiftsList;
    }


    public Task getTask(int index) {
        Task task = currentSheet.getTasklist(index);
        return task;
    }

    public void setCurrentSheet(SummarySheet fr) {
        this.currentSheet = fr;
    }

    public void setCurrentTask(Task task) {
        this.currentTask = task;
    }

    private void notifySheetAdded(SummarySheet fr) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetCreated(fr);
        }
    }

    private void notifyTaskAdded(Task task) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateTaskAdded(task);
        }
    }

    private void notifyCookUpdate(Task task) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateModifyCook(task);
        }
    }

    private void notifyShiftUpdate(Task task) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateModifyShift(task);
        }
    }

    private void notifyEsteemUpdate(Task task) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateModifyEsteem(task);
        }
    }

    private void notifyDosesUpdate(Task task) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateModifyDoses(task);
        }
    }

    private void notifyTaskDeleted(Task task) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateTaskDeleted(task);
        }
    }

    private void notifyReadysUpdate(Task task) {
        for(TaskEventReceiver er : this.eventReceivers) {
            er.updateReadyUpdate(task);
        }
    }
}
