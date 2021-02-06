import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.*;

public class Token {
    //limit the number of transaction in token
    public Map<String, Transaction> transactions;

    public Token() {
        this.transactions = new LinkedHashMap<>();
    }
    //add transaction to the token


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : this.transactions.values()) {
            sb.append(t.mess).append("\n");
        }
        return sb.toString();
    }
}
