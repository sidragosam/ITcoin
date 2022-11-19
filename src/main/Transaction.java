package main;

import java.util.Arrays;
import java.util.Date;

public class Transaction {
    private String message;
    private Date date;
    private byte[] signature;

    public Transaction(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "\n[Transaction: " +
                "Message: '" + message + '\'' +
                ", Date: " + date +
                ", Signature: " + Arrays.toString(signature) + "]";
    }
}
