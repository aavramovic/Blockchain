import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    public List<Block> chain;
    public int difficulty;


    public BlockChain() {
        this.chain = new ArrayList<>();
        this.difficulty = 0;

    }
    //update difficulty with each block

    public void addNewBlock(Block newBlock) throws NoSuchAlgorithmException {
        //in proofOfWork computes the hash
        // newBlock.hash = newBlock.calculateHash();
        newBlock.setPrevious(chain.get(chain.size() - 1).hash);
        newBlock.proofOfWork(this.difficulty);
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

    public boolean equals(BlockChain bc) {
        if (bc.chain.size() != this.chain.size() || this.difficulty != bc.difficulty)
            return false;
        for (int i = 0; i < this.chain.size(); i++) {
            if (!chain.get(i).equals(bc.chain.get(i)))
                return false;
        }
        return false;
    }
}
