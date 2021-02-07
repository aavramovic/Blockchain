import javax.xml.crypto.Data;
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


        Alice.newCoinbase(Double.parseDouble("300"), Alice.publicKey);
        Mark.newCoinbase(Double.parseDouble("1000"), Mark.publicKey);


        //making transactions
         //works
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

        Antonio.verifyBlock(Antonio.getLastBlock());
        Antonio.updateMinerBlockChain();
        update();

        Alice.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("100.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("500.0")); //T
        Jana.newTransaction(Jana.publicKey, Antonio.publicKey, Double.parseDouble("100.0")); //F
        Alice.newTransaction(Alice.publicKey, Marta.publicKey, Double.parseDouble("200.0")); //T
        Alice.newTransaction(Alice.publicKey, Jana.publicKey, Double.parseDouble("100.0")); //F
        Marta.newTransaction(Marta.publicKey, Mark.publicKey, Double.parseDouble("100.0")); //T
        Mark.newTransaction(Mark.publicKey, Anne.publicKey, Double.parseDouble("250.0")); //T
        Anne.newTransaction(Anne.publicKey, Alice.publicKey, Double.parseDouble("300.0")); //T
        Mark.newTransaction(Mark.publicKey, Bob.publicKey, Double.parseDouble("780.0")); //F


        Antonio.verifyBlock(Antonio.getLastBlock());
        Antonio.updateMinerBlockChain();
        update();
        for (Block b : Antonio.blockchain.chain) {
            System.out.println("-------------");
            System.out.println(b.print());
        }
//        Antonio.verifyBlock(Antonio.getLastBlock());
//        Antonio.updateMinerBlockChain();
//        update();
//        for(Block b:Antonio.blockchain.chain){
//            System.out.println("-------------");
//            System.out.println(b.print());
//        }


        //new transactions
//        Jana.newCoinbase(Double.parseDouble("300.0"), Jana.publicKey);
//        Bob.newTransaction(Alice.publicKey, Mark.publicKey, Double.parseDouble("11.0"));
//        Mark.newTransaction(Alice.publicKey, Bob.publicKey, Double.parseDouble("12.0"));
//        Marta.newTransaction(Alice.publicKey, Alice.publicKey, Double.parseDouble("13.0"));
//        Bob.newTransaction(Alice.publicKey, Jana.publicKey, Double.parseDouble("300.0"));
//        Marta.newTransaction(Alice.publicKey, Anne.publicKey, Double.parseDouble("14.0"));
//        Marta.verifyBlock(Antonio.getLastBlock());


    }


}
