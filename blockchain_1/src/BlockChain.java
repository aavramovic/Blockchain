import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    public List<Block> chain;
    public int difficulty;
    private static final int LIMIT = 10;


    public BlockChain() {
        this.chain = new ArrayList<>();
        this.difficulty = 0;

    }

    public void addTransaction(Transaction transaction) throws NoSuchAlgorithmException {
        Block lastBlock = this.chain.get(chain.size() - 1);
        if (lastBlock.token.transactions.size() > LIMIT) {
            this.addNewBlock(new Block());
            addTransaction(transaction);
        } else {
            lastBlock.token.transactions.add(transaction);
        }
    }
    //update difficulty with each block

    public void addNewBlock(Block newBlock) throws NoSuchAlgorithmException {
        //in proofOfWork computes the hash
        // newBlock.hash = newBlock.calculateHash();
        Block lastBlock = this.chain.get(chain.size() - 1);
        List<Transaction> tempTransactions = lastBlock.token.transactions;
        for(Transaction transaction : tempTransactions){
            if (!transaction.verified)
                lastBlock.token.transactions.remove(transaction);
            //ako ima problem ovde e do equals sho promashuva niz instanci
        }
        if(tempTransactions.size() == lastBlock.token.transactions.size()){
            newBlock.setPrevious(chain.get(chain.size() - 1).hash);
            newBlock.proofOfWork(this.difficulty);
            this.chain.add(newBlock);
        }
        else{

        }
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
