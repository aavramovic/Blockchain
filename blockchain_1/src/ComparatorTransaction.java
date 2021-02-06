import java.util.Comparator;

public class ComparatorTransaction implements Comparator<Transaction> {
    @Override
    public int compare(Transaction o1, Transaction o2) {
        return o1.timestamp.compareTo(o2.timestamp);
    }
}
