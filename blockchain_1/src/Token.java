import java.util.*;

public class Token {
    public Map<String, Transaction> transactions;

    public Token() {
        this.transactions = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : this.transactions.values()) {
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }
    public String print(){
        StringBuilder sb = new StringBuilder();
        for (Transaction t : this.transactions.values()) {
            sb.append(t.print()).append("\n");
        }
        return sb.toString();
    }
}
