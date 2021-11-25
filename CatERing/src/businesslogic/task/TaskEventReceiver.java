package businesslogic.task;

public interface TaskEventReceiver {
    public void updateSummarySheetCreated(SummarySheet fr);

    public void updateTaskAdded(Task task);

    public void updateModifyCook(Task task);

    public void updateModifyShift(Task task);

    public void updateModifyEsteem(Task task);

    public void updateModifyDoses(Task task);

    public void updateTaskDeleted(Task task);

    public void updateReadyUpdate(Task task);
}
