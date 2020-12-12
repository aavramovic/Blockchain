import java.time.LocalDateTime;
import java.util.List;

public class Block {
    public long ID;
    public long Nonce;
    public Token token;
    public String previous;
    public String hash;
    public Coinbase coinbase;
    public LocalDateTime timestamp;



    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
