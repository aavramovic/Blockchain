import javax.xml.crypto.Data;
import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

public class WebMock {


    public static void main(String[] args) throws Exception {
        User sender = new User();
        User receiver = new User();
        User miner = new User();
        User miner2 = new User();

        DataHolder.addUser(sender);
        DataHolder.addUser(receiver);
        DataHolder.addUser(miner);
        DataHolder.addUser(miner2);
        sender.setUsers(DataHolder.users);
        receiver.setUsers(DataHolder.users);
        miner.setUsers(DataHolder.users);
        miner2.setUsers(DataHolder.users);


        miner.newCoinbase(Double.parseDouble("300.0"), sender.publicKey);
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("1.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("2.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("3.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("4.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("5.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("6.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("7.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("8.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("9.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("10.0"));
        miner.updateBlockChainLocal();
//        miner.verifyBlock(miner.getLastBlock());
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("11.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("12.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("13.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("300.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("14.0"));

        miner.verifyBlock(miner.getLastBlock());
//        miner.updateMinerBlockChain();

        for(Block b: sender.blockchain.chain){
            System.out.println("----------");
            System.out.println(b.print());
        }
    }
}
