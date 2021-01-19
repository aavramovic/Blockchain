import java.util.ArrayList;
import java.util.List;

public class Token {
    //limit the number of transaction in token
     private static final int LIMIT =10;
    public List<Transaction> transactions;

    public Token() {
        this.transactions=new ArrayList<>();
    }
    //add transaction to the token
    public void addTransaction(Transaction transaction){
        if(this.transactions.size()<LIMIT)
            this.transactions.add(transaction);

    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Transaction t :this.transactions)
        {
            sb.append(t.mess).append("\n");
        }
        return sb.toString();

    }
}
