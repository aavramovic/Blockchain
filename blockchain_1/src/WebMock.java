import javax.xml.crypto.Data;
import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

public class WebMock {


    public static void main(String[] args) throws Exception {
//        User Alice = new User();
//        User Bob = new User();
//        User Oscar = new User();
//        User PapaSmurf = new User();
//        User Jana = new User();
//        User Marta = new User();
//        User Antonio = new User();
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


        miner.newCoinbase(Double.parseDouble("300000.0"), sender.publicKey);
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("100.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("1550.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("1560.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("50.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("10.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("15.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("10.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("107.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("17.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("18.0"));
        miner.verifyBlock(miner.getLastBlock());
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("1000.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("199.0"));
        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("60.0"));

        miner.blockchain = updateBlockChain(miner);
        miner.verifyBlock(miner.getLastBlock());
        System.out.println(miner.blockchain.chain.get(0));
        System.out.println(miner.blockchain.chain.get(1));

        //sender.blockchain = updateBlockChain(sender);


//        System.out.println(sender.getLastBlock().toString());
    }


    public static BlockChain updateBlockChain(User user) {
        //ako ova raboti dzver
        HashMap<BlockChain, Integer> finalBlockChain = new HashMap<>();
        for(User u : DataHolder.getUsers()){
            if(finalBlockChain.containsKey(u.blockchain))
                finalBlockChain.put(u.blockchain, finalBlockChain.get(u.blockchain)+1);
            finalBlockChain.put(u.blockchain, 1);
        }
        Optional<Map.Entry<BlockChain, Integer>>maxEntry = finalBlockChain.entrySet().stream().max(Map.Entry.comparingByValue());
        if(maxEntry.isPresent())
            return maxEntry.get().getKey();
        else throw new WrongMethodTypeException("Update BlockChain maxEntry is not present");
    }

    void addNewUser(User user) {
        DataHolder.addUser(user);
    }


}
