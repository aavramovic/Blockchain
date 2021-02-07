import javax.xml.crypto.Data;
import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

public class WebMock {

    public static void update()
    {
        DataHolder.users.forEach(User::updateBlockChainLocal);
    }

    public static void main(String[] args) throws Exception {

        //users initialization
        User Alice= new User();
        User Bob= new User();
        User Mark=new User();
        User Anne=new User();
        User Antonio = new User();
        User Marta = new User();
        User Jana=new User();


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


        Alice.newCoinbase(Double.parseDouble("300.0"), Alice.publicKey);
        Mark.newCoinbase(Double.parseDouble("1000.0"),Mark.publicKey);
        //making transactions
        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("1.0"));
        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("2.0"));
        Alice.newTransaction(Alice.publicKey, Antonio.publicKey, Double.parseDouble("3.0"));
        Alice.newTransaction(Alice.publicKey, Mark.publicKey, Double.parseDouble("4.0"));
        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("5.0"));
        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("6.0"));
        Alice.newTransaction(Alice.publicKey, Anne.publicKey, Double.parseDouble("7.0"));
        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("8.0"));
        Bob.newTransaction(Alice.publicKey, Anne.publicKey, Double.parseDouble("9.0"));
        Mark.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("10.0"));
        //miner verifies the block
        Antonio.verifyBlock(Antonio.getLastBlock());
        Antonio.updateMinerBlockChain();
        //new transactions
        Jana.newCoinbase(Double.parseDouble("300.0"), Jana.publicKey);
        Bob.newTransaction(Alice.publicKey, Mark.publicKey, Double.parseDouble("11.0"));
        Mark.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("12.0"));
        Marta.newTransaction(Alice.publicKey, Alice.publicKey, Double.parseDouble("13.0"));
        Bob.newTransaction(Alice.publicKey, Jana.publicKey, Double.parseDouble("300.0"));
        Marta.newTransaction(Alice.publicKey, Anne.publicKey, Double.parseDouble("14.0"));
        Marta.verifyBlock(Antonio.getLastBlock());
        update();


    }



}
