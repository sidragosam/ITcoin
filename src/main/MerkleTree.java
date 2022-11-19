package main;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
        private final List<Transaction> transactions;
        private String hash;
        private MessageDigest messageDigest;

    public MerkleTree() {
        transactions = new ArrayList<>();
        hash = "";
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e){
            System.err.println("Message Digest Error: " + e.toString());
        }
    }

    public void addTransaction(Transaction transaction, Key publicKey){
        transactions.add(transaction);
        byte[] messageHash = messageDigest.digest(transaction.toString().getBytes(StandardCharsets.UTF_8));
        try {
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, publicKey);
            c.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] signature = c.doFinal(messageHash);
            transaction.setSignature(signature);
        } catch (Exception e) {
            System.err.println("Error while creating the Signature: " + e.getMessage());
        }
        this.createTree();
    }

    private List<String> createHash(List<String> list)
    {
        List<String> result = new ArrayList<>();
        if(list.size() == 1)
        {
            result.add(list.get(0));
            return result;
        }
        for(int i = 0; i < list.size(); i=i+2)
        {
            if(list.size() > i+1)
            {
                result.add(Hash.hash(list.get(i) + list.get(i+1)));
            }
            else
            {
                result.add(list.get(i));
            }
        }
        return this.createHash(result);
    }

    private void createTree(){
        List<String> list = new ArrayList<>();
        for (Transaction transaction: transactions){
            list.add(Hash.hash(transaction.toString()));
        }
        List<String> lista = this.createHash(list);
        hash = lista.get(0);
    }

    @Override
    public String toString() {
        return "[MerkleTree: " +
                " Transactions: " + transactions +
                ", Hash: '" + hash + '\'' +
                ", Message Digest: " + messageDigest + "]";
    }

    public String getHash() {
        return hash;
    }
}
