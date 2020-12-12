import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
    public PublicKey publicKey;
    private PrivateKey privateKey;
    public List<Block> chain;
    public static List<User> users;
    public User() throws Exception{
        //Key pair for the user
        KeyPair kp=RSA.generateKeyPair();
        this.publicKey=kp.getPublic();
        this.privateKey=kp.getPrivate();
        this.chain=new ArrayList<>();
    }

    public void setChain(List<Block> chain) {
        this.chain = chain;
    }


    //add finished block
    public void addFinishedBlock(Block b)
    {
        b.setPrevious(chain.get(chain.size()-1).hash);
        this.chain.add(b);
    }
    //sign the transaction
    public String signTransaction(String mess) throws Exception {
        //RSA signature
        return RSA.sign(mess,this.privateKey);


    }
    //verify a transaction
    public boolean verifyTransaction(String signature,String mess,PublicKey publicKey) throws Exception {
        return RSA.verify(mess,signature,publicKey);
    }
    //sum amount of coins for user with public key (+coinbase+receiver-sender)
    public double amountForUser(PublicKey publicKey)
    { double sum=0;
    //iterates through blocks in chain
       for(Block b:this.chain)
       {
           //sums the new coins of user
           if(b.coinbase.userPublicKey.equals(publicKey))
               sum+=b.coinbase.amount;

           for(Transaction t:b.token.transactions)
           {
               //adds amount of transaction if the user is the receiver
               if(t.receiver.publicKey.equals(publicKey))
                   sum+=t.amount;
               //subtracts amount of transaction if the user is the sender
               if(t.sender.publicKey.equals(publicKey))
                   sum-=t.amount;
           }

        }
       return sum;
    }

}
