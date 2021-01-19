import java.util.HashSet;

public class DataHolder {
     static HashSet<User> users;

    public  static HashSet<User> getUsers() {
        return users;
    }

    public DataHolder() {
        users = new HashSet<>();
    }

    public void fillUsers() throws Exception {
        User Alice = new User();
        User Bob = new User();
        User Oscar = new User();
        User PapaSmurf = new User();
        User Jana = new User();
        User Marta = new User();
        User Antonio = new User();

        users.add(Alice);
        users.add(Bob);
        users.add(Oscar);
        users.add(PapaSmurf);
        users.add(Jana);
        users.add(Marta);
        users.add(Antonio);

    }
    public void addUser(User user){
        users.add(user);
    }

}
