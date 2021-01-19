import javax.xml.crypto.Data;
import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

public class WebMock {
    private static DataHolder dh = new DataHolder();

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

        dh.addUser(sender);
        dh.addUser(receiver);
        dh.addUser(miner);
        dh.addUser(miner2);
        sender.setUsers(dh.users);
        receiver.setUsers(dh.users);
        miner.setUsers(dh.users);
        miner2.setUsers(dh.users);
        System.out.println(sender.blockchain.chain.get(0));
        //coinbase
//        miner.newCoinbase(Double.parseDouble("300.0"), sender.publicKey);
        //token
//        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("100.0"));
//        sender.newTransaction(sender.publicKey, receiver.publicKey, Double.parseDouble("150.0"));
//        miner.blockchain = updateBlockChain(miner);
//        miner.verifyBlock(miner.getLastBlock());
//        sender.blockchain = updateBlockChain(sender);
//        System.out.println(sender.getLastBlock().toString());
    }


    public static BlockChain updateBlockChain(User user) {
        //ako ova raboti dzver
        HashMap<BlockChain, Integer> finalBlockChain = new HashMap<>();
        for(User u : dh.getUsers()){
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
        dh.addUser(user);
    }

    public static DataHolder getDh() {
        return dh;
    }
}
