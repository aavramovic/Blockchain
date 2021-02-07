
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;


public class User {
    public PublicKey publicKey;
    private final PrivateKey privateKey;
    public BlockChain blockchain;
    public HashSet<User> users;
    public String name;


    public User(String name) throws Exception {
        //Key pair za user
        KeyPair kp = RSA.generateKeyPair();
        this.publicKey = kp.getPublic();
        this.privateKey = kp.getPrivate();
        this.users = new HashSet<>();
        updateBlockChainLocal();
        this.name = name;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void newTransaction(PublicKey publicKeySender, PublicKey publicKeyReceiver, double amount) throws Exception {
        Transaction t = new Transaction(amount, publicKeySender, publicKeyReceiver);
        String key = signTransaction(t);
        DataHolder.users.forEach(x -> {
            try {
                x.blockchain.addTransaction(t);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });
    }
    public void newCoinbase(double amount, PublicKey publicKey) {
        DataHolder.getUsers().forEach(x->x.getLastBlock().coinbase.newCoinbase(publicKey, amount));
    }
    public void updateBlockChainLocal() {
        this.blockchain = updateBlockChain();
    }

    public void updateMinerBlockChain() {
        DataHolder.getUsers().forEach(x -> x.blockchain = this.blockchain);
    }
    public BlockChain updateBlockChain() {
        HashMap<BlockChain, Integer> finalBlockChain = new HashMap<>();
        ArrayList<BlockChain> list = new ArrayList<>();
        for (User u : users) {
            list.add(u.blockchain);
        }
        list.sort(new ComparatorBlockChain());
        ArrayList<BlockChain> fin = new ArrayList<>( list.subList(0, Math.min(list.size(), 3)));
        for (User u : users) {
            if (finalBlockChain.containsKey(u.blockchain) && fin.contains(u.blockchain))
                finalBlockChain.put(u.blockchain, finalBlockChain.get(u.blockchain) + 1);
            finalBlockChain.put(u.blockchain, 1);
        }
        Optional<Map.Entry<BlockChain, Integer>> maxEntry = finalBlockChain.entrySet().stream().max(Map.Entry.comparingByValue());
        if (maxEntry.isPresent())
            return maxEntry.get().getKey();
        else {
            this.blockchain = new BlockChain();
            return this.blockchain;
        }
    }
    public Block getLastBlock() {
        if (blockchain.chain.size() == 0) {
            blockchain.chain.add(new Block());
            return blockchain.chain.get(0);
        }
        return blockchain.chain.get(blockchain.chain.size() - 1);
    }

    public boolean verifyBlock(Block block) throws Exception {
        Map<String, Transaction> tmp = new LinkedHashMap<>(block.token.transactions);
        tmp.forEach((key, value) -> {
            try {
                if (verifyTransaction(key, value.mess, value.sender.publicKey))
                    block.token.transactions.get(key).verified = true;
                else
                    block.token.transactions.remove(key);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        System.out.println(block.token.transactions.entrySet().size());
        if (block.token.transactions.entrySet().size()==0)
        {
            return false;
        }
        block.proofOfWork(this.blockchain.difficulty);
        block.coinbase.coinbase.put(this.publicKey,Double.parseDouble("15.00"));
        int index=this.blockchain.chain.indexOf(block);
        if(index!=0)
        {
            block.setPrevious(this.blockchain.chain.get(index-1).hash);
        }

        return true;

    }
    public boolean verifyTransaction(String signature, String mess, PublicKey publicKey) throws Exception {
        //proveri dali ima pari
        Double amount = amountForUser(publicKey);
        Double price = Double.parseDouble(mess.split(";")[0]);
        if (amount - price < 0) {
            return false;
        }
        return RSA.verify(mess, signature, publicKey);
    }
    public String signTransaction(Transaction transaction) throws Exception {
        //RSA signature
        transaction.signature = RSA.sign(transaction.mess, this.privateKey);
        return transaction.signature;
    }
    //sum za user so public key kako argument (+coinbase+receiver-sender)
    public Double amountForUser(PublicKey publicKey) {
        double sum = 0;
        for (Block b : this.blockchain.chain) {
            if (b.coinbase.coinbase.containsKey(publicKey))
                sum += b.coinbase.coinbase.get(publicKey);

            for (Transaction t : b.token.transactions.values()) {
                if (t.verified) {
                    if (t.receiver.publicKey.equals(publicKey))
                        sum += t.amount;
                    if (t.sender.publicKey.equals(publicKey))
                        sum -= t.amount;
                }
            }
        }
        return sum;
    }
    public void verifyBlockChain() throws Exception {

        ArrayList<Block> tmp=new ArrayList<>(this.blockchain.chain);
        for(Block b:tmp)
        {
           if(b.hash==null)
           {
               boolean flag=this.verifyBlock(b);
               if(this.blockchain.chain.indexOf(b)!=this.blockchain.chain.size()-1&&!flag)
               {
                   int index=this.blockchain.chain.indexOf(b);
                   this.blockchain.chain.remove(b);
                   this.blockchain.chain.get(index).previous=b.previous;
               }

           }
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "publicKey=" + publicKey +
                '}';
    }
}
