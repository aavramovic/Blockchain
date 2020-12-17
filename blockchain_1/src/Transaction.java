import java.security.PublicKey;

public class Transaction {
    //verified signature
    public String signature;
    public User sender;
    public User receiver;
    public User miner;
    public double amount;
    public String mess;


    public Transaction(double amount, PublicKey senderPubK,PublicKey receiverPubK) {
        this.amount = amount;
        this.sender=User.users.stream().filter(u->u.publicKey.equals(senderPubK)).findFirst().orElse(null);
        this.receiver=User.users.stream().filter(u->u.publicKey.equals(receiverPubK)).findFirst().orElse(null);
        //message construction mess=amount;senderPubK;receiverPubK
        StringBuilder sb =new StringBuilder();
        this.mess=sb.append(amount).append(";").append(sender.publicKey).append(";").append(receiver.publicKey).toString();

    }

    //sign the transaction sender.sign
    public String signTransaction() throws Exception {

        this.signature=sender.signTransaction(this.mess);
        return this.signature;
    }

    //verify the transaction receiver.verify
    public boolean verifySignature() throws Exception {
        return receiver.verifyTransaction(signature,this.mess,sender.publicKey);
    }

    //check the amount miner.check
    public boolean checkAmountSender()
    {
        if((amount-miner.amountForUser(sender.publicKey))>=0)
             return true;
        return false;

    }



}
