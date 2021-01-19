import java.util.HashSet;

public class DataHolder {
    HashSet<User> users;
    public DataHolder() {
        this.users = new HashSet<>();
    }

    public void fillUsers() throws Exception {
        User Alice = new User();
        User Bob = new User();
        User Oscar = new User();
        User Jana = new User();
        User Marta = new User();
        User Antonio = new User();
    }

}
