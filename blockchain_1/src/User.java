import java.lang.invoke.WrongMethodTypeException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

public class User {
    public PublicKey publicKey;
    private final PrivateKey privateKey;
    public BlockChain blockchain;
    public HashSet<User> users;


    public User() throws Exception {
        //Key pair for the user
        KeyPair kp = RSA.generateKeyPair();
        this.publicKey = kp.getPublic();
        this.privateKey = kp.getPrivate();
        this.users = new HashSet<>();
//        setBlockchain();
        updateBlockChain();
    }
    public void setUsers(HashSet<User> users){
        this.users = users;

    }
//    public void updateBlockChain() {
//        this.blockchain = WebMock.updateBlockChain(this);
//    }

    public void newTransaction(PublicKey publicKeySender, PublicKey publicKeyReceiver, double amount) throws Exception {
        Transaction t = new Transaction(amount, publicKeySender, publicKeyReceiver);
        String key = signTransaction(t);
        getLastBlock().token.transactions.put(key, t);
    }

    public void newCoinbase(double amount, PublicKey publicKey) {
        getLastBlock().coinbase.newCoinbase(publicKey, amount);
    }

    //get the blockchain present in most users
/*    public void setBlockchain() {
        HashMap<BlockChain, Integer> voting = new HashMap<>();
        for (User u : users) {
            //if the blockchain is already in the map just increment the count
            if (voting.computeIfPresent(u.blockchain, (key, val) -> val + 1) == null)
                voting.put(u.blockchain, 1);
        }
        //get the blockchain with the most votes
        Optional<Map.Entry<BlockChain, Integer>> tmp = voting.entrySet().stream().max(Map.Entry.comparingByValue());
        if (tmp.isEmpty())
            this.blockchain = new BlockChain();
        else {
            this.blockchain = tmp.get().getKey();
        }

    }*/
    public BlockChain updateBlockChain() {
        //ako ova raboti dzver
        HashMap<BlockChain, Integer> finalBlockChain = new HashMap<>();
        for(User u : this.users){
            if(finalBlockChain.containsKey(u.blockchain))
                finalBlockChain.put(u.blockchain, finalBlockChain.get(u.blockchain)+1);
            finalBlockChain.put(u.blockchain, 1);
        }
        Optional<Map.Entry<BlockChain, Integer>>maxEntry = finalBlockChain.entrySet().stream().max(Map.Entry.comparingByValue());
        if(maxEntry.isPresent())
            return maxEntry.get().getKey();
        else throw new WrongMethodTypeException("Update BlockChain maxEntry is not present");
    }

    //get last block
    public Block getLastBlock() {
        //update blockchain?
        if (blockchain.chain.size() == 0) {
            blockchain.chain.add(new Block());
            return blockchain.chain.get(0);
        }
        return blockchain.chain.get(blockchain.chain.size() - 1);
    }


    //sign the transaction
    public String signTransaction(Transaction transaction) throws Exception {
        //RSA signature

        transaction.signature = RSA.sign(transaction.mess, this.privateKey);
        return transaction.signature;
    }

    public void verifyBlock(Block block) throws Exception{
        block.token.transactions.forEach((key, value) -> {
            try {
                verifyTransaction(key, value.mess, value.sender.publicKey);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //String signature, String mess, PublicKey publicKey
    }

    //verify a transaction
    public boolean verifyTransaction(String signature, String mess, PublicKey publicKey) throws Exception {
        return RSA.verify(mess, signature, publicKey);
    }

    //sum amount of coins for user with public key (+coinbase+receiver-sender)
    public double amountForUser(PublicKey publicKey) {
        double sum = 0;
        //iterates through blocks in chain
        for (Block b : this.blockchain.chain) {
            //sums the new coins of user
            if (b.coinbase.coinbase.containsKey(publicKey))
                sum += b.coinbase.coinbase.get(publicKey);

            for (Transaction t : b.token.transactions.values()) {
                //adds amount of transaction if the user is the receiver
                if (t.receiver.publicKey.equals(publicKey))
                    sum += t.amount;
                //subtracts amount of transaction if the user is the sender
                if (t.sender.publicKey.equals(publicKey))
                    sum -= t.amount;
            }

        }
        return sum;
    }

}
