import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.WrongMethodTypeException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebMock {

    public static void update() {
        DataHolder.users.forEach(User::updateBlockChainLocal);
    }

    public static void main(String[] args) throws Exception {

        //users initialization
        User Alice = new User("Alice");
        User Bob = new User("Bob");
        User Mark = new User("Mark");
        User Anne = new User("Anne");
        User Antonio = new User("Antonio");
        User Marta = new User("Marta");
        User Jana = new User("Jana");

        DataHolder.addUser(Alice);
        DataHolder.addUser(Bob);
        DataHolder.addUser(Antonio);
        DataHolder.addUser(Marta);
        DataHolder.addUser(Jana);
        DataHolder.addUser(Anne);
        DataHolder.addUser(Mark);

        Alice.setUsers(DataHolder.users);
        Bob.setUsers(DataHolder.users);
        Antonio.setUsers(DataHolder.users);
        Marta.setUsers(DataHolder.users);
        Jana.setUsers(DataHolder.users);
        Anne.setUsers(DataHolder.users);
        Mark.setUsers(DataHolder.users);







        //new coinbase
        Alice.newCoinbase(Double.parseDouble("300"), Alice.publicKey);
        Mark.newCoinbase(Double.parseDouble("1000"), Mark.publicKey);
        //all true
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("1.0")); //T
        //verifikacija od miner
       // Antonio.verifyBlock(Antonio.getLastBlock());
       // Antonio.updateMinerBlockChain();
       // update();
        //bez coinbase
        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("100.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("500.0")); //T
        Jana.newTransaction(Jana.publicKey, Antonio.publicKey, Double.parseDouble("100.0")); //F
        Alice.newTransaction(Alice.publicKey, Marta.publicKey, Double.parseDouble("200.0")); //T
        Alice.newTransaction(Alice.publicKey, Jana.publicKey, Double.parseDouble("100.0")); //F
        Marta.newTransaction(Marta.publicKey, Mark.publicKey, Double.parseDouble("100.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("250.0")); //T
        Anne.newTransaction(Anne.publicKey, Alice.publicKey, Double.parseDouble("300.0")); //T
        Mark.newTransaction(Mark.publicKey, Bob.publicKey, Double.parseDouble("780.0")); //F
        //verifikacija od miner
       // Jana.verifyBlock(Antonio.getLastBlock());
        //Jana.updateMinerBlockChain();
        //update();
        System.out.println("Jana"+Jana.amountForUser(Jana.publicKey));
        System.out.println("Mark"+Mark.amountForUser(Mark.publicKey));
        System.out.println("Alice"+Alice.amountForUser(Alice.publicKey));
        System.out.println("Antonio"+Antonio.amountForUser(Antonio.publicKey));
        System.out.println("Marta"+Marta.amountForUser(Marta.publicKey));
        System.out.println("Bob"+Bob.amountForUser(Bob.publicKey));

        //all false
        Antonio.newCoinbase(Double.parseDouble("10.0"), Antonio.publicKey);
        Bob.newTransaction(Bob.publicKey, Mark.publicKey, Double.parseDouble("110.0"));//F
        Marta.newTransaction(Marta.publicKey, Alice.publicKey, Double.parseDouble("103.0"));//F
        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("1002.0"));//F
        Marta.newTransaction(Marta.publicKey, Anne.publicKey, Double.parseDouble("104.0"));//F
        Mark.newTransaction(Mark.publicKey, Bob.publicKey, Double.parseDouble("1011.0"));//F
        Antonio.newTransaction(Antonio.publicKey,Jana.publicKey,Double.parseDouble("100.0"));//F

        //Marta.verifyBlock(Marta.getLastBlock());
        //Marta.updateMinerBlockChain();
        //update();
        Mark.verifyBlockChain();
        Mark.updateMinerBlockChain();

        update();

        for (Block b : Marta.blockchain.chain) {
            System.out.println("-------------");
            System.out.println(b.print());
        }




    }


}
