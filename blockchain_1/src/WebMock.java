import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.HashSet;

public class WebMock {
    DataHolder dh;
    public WebMock() {
        dh = new DataHolder();

    }

    void addNewUser(User user){
        dh.addUser(user);
    }
    void requestJoinaje(User user){
        //gi izvestuvame userite na tipov da mu pratat info
        //user ke ima klasa send info

    }
}
