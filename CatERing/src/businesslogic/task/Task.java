package businesslogic.task;

import businesslogic.event.EventInfo;
import businesslogic.recipe.Recipe;
import businesslogic.shift.Shift;
import businesslogic.user.User;

public class Task {
    public int esteem;
    private int id;
    private Shift shift;
    private User cook;
    private int doses;
    private boolean ready;
    private Recipe recipe;

    public Task(Shift shift, User cook, int esteem, int doses) {
        this.shift = shift;
        this.cook = cook;
        this.esteem = esteem;
        this.doses = doses;
        this.ready = false;
    }

    public Task(Shift shift, User cook) {
        this.shift = shift;
        this.cook = cook;
        this.esteem = Integer.parseInt(null);
        this.doses = Integer.parseInt(null);
        this.ready = false;
    }

    public Task(Recipe recipe) {
        this.recipe = recipe;
    }


    public Task(Shift shift, int esteem, int doses) {
        this.shift = shift;
        this.cook = null;
        this.esteem = esteem;
        this.doses = doses;
        this.ready = false;
    }

    //GETTER

    //SETTER

    public void setCook(User cook) {
        this.cook = cook;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public void setEsteem(int esteem) {
        this.esteem = esteem;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public void setReady() {
        this.ready = true;
    }

    public int getEsteem() {
        return this.esteem;
    }

    public int getDoses() { return this.doses; }
}
