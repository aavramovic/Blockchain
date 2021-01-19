import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Token {
    //limit the number of transaction in token
    public List<Transaction> transactions;

    public Token() {
        this.transactions=new ArrayList<>();
    }
    //add transaction to the token


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
