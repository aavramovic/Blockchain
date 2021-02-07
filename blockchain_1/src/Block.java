import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import java.util.Random;

public class Block {
    public long ID;
    public long Nonce;
    public Coinbase coinbase;
    public Token token;
    public String previous;
    public String hash;
    public LocalDateTime timestamp;
    public Block() {
        this.coinbase = new Coinbase();
        this.token = new Token();
        this.timestamp = LocalDateTime.now();
        this.ID = new Random().nextLong();
        this.Nonce = 0;
    }
    public void proofOfWork(int difficulty) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(Math.max(0, difficulty)));
        this.hash=calculateHash();
        while (!this.hash.substring(0, difficulty).equals(sb.toString())) {
            this.Nonce++;
            this.hash = this.calculateHash(); }
    }
    public String print(){
        return "Block{" +
                "ID=" + ID + ", Nonce=" + Nonce +
                ", coinbase=" + coinbase + ", token= " +'\n'+ token.print() + ", previous='" + previous + '\'' +
                ", hash='" + hash + '\'' + ", timestamp=" + timestamp + '}'; }
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String calculateHash() throws NoSuchAlgorithmException {
        return SHA256.toString(SHA256.getSHA(ID + Nonce + previous + token.toString() + timestamp.toString()));
    }
    public boolean equals(Block b) {
        return b.ID != this.ID;
    }

}
