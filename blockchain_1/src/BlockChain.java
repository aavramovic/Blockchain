import java.security.NoSuchAlgorithmException;
import java.util.*;

public class BlockChain {
    public List<Block> chain;
    public int difficulty;
    private static final int LIMIT = 10;
    public BlockChain() {
        this.chain = new ArrayList<>();
        this.difficulty = 1;
        this.chain.add(new Block());
    }
    public void addTransaction(Transaction transaction) throws NoSuchAlgorithmException {
        Block lastBlock = this.chain.get(chain.size() - 1);
        if (lastBlock.token.transactions.size() >= LIMIT||lastBlock.hash!=null) {
            this.addNewBlock(new Block());
            addTransaction(transaction);
            //this.difficulty=this.difficulty++;
        } else {
            lastBlock.token.transactions.put(transaction.signature, transaction);
        }
    }
    public void addNewBlock(Block newBlock) throws NoSuchAlgorithmException {
        Block lastBlock = this.chain.get(chain.size() - 1);
        newBlock.setPrevious(lastBlock.hash);
        this.chain.add(newBlock);
    }
    public int verifiedSize() {
        int size = 0;
        for (Block b : this.chain) {
            if (b.hash != null) {
                size++;
            }
        }
        return size;
    }
    public boolean chainValidity() throws NoSuchAlgorithmException {
        for (int i = 1; i < this.chain.size(); i++) {
            Block curr = this.chain.get(i);
            Block prev = this.chain.get(i - 1);
            if(curr.hash==null)
                continue;
            if (!curr.previous.equals(prev.hash) || !curr.hash.equals(curr.hash))
                return false;
        }
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockChain)) return false;
        BlockChain that = (BlockChain) o;
        return difficulty == that.difficulty &&
                Objects.equals(chain, that.chain);
    }
    @Override
    public int hashCode() {
        return Objects.hash(chain, difficulty);
    }
    @Override
    public String toString() {
        return "BlockChain{" +
                "chain=" + chain.toString() +
                ", difficulty=" + difficulty +
                '}';
    }
}