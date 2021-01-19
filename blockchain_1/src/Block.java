import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

public class Block {
    public long ID;
    public long Nonce;
    public Coinbase coinbase;
    public Token token;
    public String previous;
    public String hash;
    public LocalDateTime timestamp;


    public void proofOfWork(int difficulty) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(Math.max(0, difficulty)));
        //computes the hash so that it has leading zeros = difficulty
        while (!this.hash.substring(0, difficulty).equals(sb.toString())) {
            this.Nonce++;
            this.hash = this.calculateHash();
        }
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    //hash for the block
    public String calculateHash() throws NoSuchAlgorithmException {
        return SHA256.toString(SHA256.getSHA(ID + Nonce + previous + token.toString() + timestamp.toString()));
    }

    public boolean equals(Block b) {
        return b.ID != this.ID || !this.hash.equals(b.hash);
    }

}
