import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.HashSet;

public class WebMock {
    private static DataHolder dh;

    public static void main(String[] args) throws Exception {
        dh.fillUsers();
        User sender = new User();
        User receiver = new User();
        User miner = new User();
        User miner2 = new User();

        dh.addUser(sender);
        dh.addUser(receiver);
        dh.addUser(miner);
        dh.addUser(miner2);

        //coinbase
        miner.newCoinbase(Double.parseDouble("300.0"), sender.publicKey);
        //token
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("100.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("150.0"));
    }

    void addNewUser(User user) {
        dh.addUser(user);
    }

    public static DataHolder getDh() {
        return dh;
    }

    void requestJoin(User user) {
        //gi izvestuvame userite na tipov da mu pratat info
        //user ke ima klasa send info
        for (User u : dh.getUsers()) {
            u.sendDataToUser(user);
        }

    }
}
