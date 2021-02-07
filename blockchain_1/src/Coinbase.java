import java.security.PublicKey;
import java.util.HashMap;


public class Coinbase {

    public HashMap<PublicKey,Double> coinbase;
    public Coinbase()
    {
        this.coinbase=new HashMap<>();
    }
    public void newCoinbase(PublicKey publicKey,Double amount)
    {
        this.coinbase.put(publicKey,amount);
    }
}
