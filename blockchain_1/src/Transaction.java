import javax.naming.directory.InvalidAttributesException;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction  {
    //verified signature
    public String signature;
    public User sender;
    public User receiver;
    public double amount;
    public String mess;
    public boolean verified;
    public LocalDateTime timestamp;


    public Transaction(double amount, PublicKey senderPubK,PublicKey receiverPubK) throws InvalidAttributesException {
        this.amount = amount;
        this.sender=DataHolder.getUsers().stream().filter(u->u.publicKey.equals(senderPubK)).findFirst().orElse(null);
        this.receiver=DataHolder.getUsers().stream().filter(u->u.publicKey.equals(receiverPubK)).findFirst().orElse(null);
        if (this.receiver== null || this.sender==null)
            throw new InvalidAttributesException("Invalid user or sender");
        //message construction mess=amount;senderPubK;receiverPubK
        StringBuilder sb =new StringBuilder();
        this.verified=false;
        this.timestamp=LocalDateTime.now();
        this.mess=sb.append(amount).append(";").append(sender.publicKey).append(";").append(receiver.publicKey).append(";").append(timestamp).toString();

    }

    public String print(){
        return "Transaction{" +
//                "signature='" + signature + '\'' +
                "sender=" + sender +
                ", receiver=" + receiver +

                ", amount=" + amount +
//                ", mess='" + mess + '\'' +
                ", verified=" + verified +
                ", timestamp=" + timestamp +
                '}';
    }


}
