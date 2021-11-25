package businesslogic.user;

public class  UserManager {
    private User currentUser;

    public User fakeLogin(String username) //TODO: bisogna implementare il login vero!
    {
        this.currentUser = User.loadUser(username);
        return null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
