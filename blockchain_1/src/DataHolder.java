import java.util.HashSet;

public class  DataHolder {
    static HashSet<User> users=new HashSet<>();

    public static HashSet<User> getUsers() {
        return users;
    }

    public static void addUser(User user){
        users.add(user);
    }

}
