import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.util.stream.Collectors.toMap;

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
        Block lastBlock = this.chain.get(chain.size()-1);
        if (lastBlock.token.transactions.size() >= LIMIT) {
            this.addNewBlock(new Block());
            addTransaction(transaction);

        } else {
            lastBlock.token.transactions.put(transaction.signature,transaction);

        }
    }
    //update difficulty with each block

    public void addNewBlock(Block newBlock) throws NoSuchAlgorithmException {
        //boolean za da proverime dali se dodal nov block
        //in proofOfWork computes the hash
        // newBlock.hash = newBlock.calculateHash();
        Block lastBlock = this.chain.get(chain.size() - 1);
        newBlock.setPrevious(lastBlock.hash);
        this.chain.add(newBlock);

    }

    //check chain validity
    public boolean chainValidity() throws NoSuchAlgorithmException {

        for (int i = 1; i < this.chain.size(); i++) {
            Block curr = this.chain.get(i);
            Block prev = this.chain.get(i - 1);

            if (!curr.previous.equals(prev.hash) || !curr.hash.equals(curr.calculateHash()))
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
    //    public boolean equals(BlockChain bc) {
//        if (bc.chain.size() != this.chain.size() || this.difficulty != bc.difficulty)
//            return false;
//        for (int i = 0; i < this.chain.size(); i++) {
//            if (!chain.get(i).equals(bc.chain.get(i)))
//                return false;
//        }
//        return false;
//    }
}
