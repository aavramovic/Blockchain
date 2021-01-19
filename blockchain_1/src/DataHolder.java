import java.util.HashSet;

public class DataHolder {
    HashSet<User> users;

    public HashSet<User> getUsers() {
        return users;
    }

    public DataHolder() {
        users = new HashSet<>();
    }


    public void addUser(User user){
        users.add(user);
    }

}
