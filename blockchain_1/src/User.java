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
        this.users=WebMock.getDh().getUsers();
        setBlockchain();
    }
    public void receiveInfo(HashSet<User> users, BlockChain blockChain){
        //collect info and compare??
    }
    public void newTransaction(PublicKey publicKeySender,PublicKey publicKeyReceiver, double amount)
    {
        Transaction t=new Transaction(amount,publicKeySender,publicKeyReceiver);
        getLastBlock().token.transactions.add(t);
    }
    public void newCoinbase(double amount,PublicKey publicKey)
    {
        getLastBlock().coinbase.newCoinbase(publicKey,amount);
    }
    public void sendDataToUser(User user){
//        WebMock.sendToUser(user);
    }

    //get the blockchain present in most users
    public void setBlockchain() {
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

    }

    //get last block
    public Block getLastBlock() {
        //update blockchain?
        return blockchain.chain.get(blockchain.chain.size() - 1);
    }


    //sign the transaction
    public String signTransaction(String mess) throws Exception {
        //RSA signature
        return RSA.sign(mess, this.privateKey);

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

            for (Transaction t : b.token.transactions) {
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
