package main;

import java.security.Key;
import java.util.Date;

public class Block {
    public final int ZEROS = 5;
    private String hash;
    private String previousHash;
    private MerkleTree merkleTree;
    private Date timestamp;
    private int nonce;

    public Block(){
        this.blockInit();
        this.previousHash = "";
    }

    public Block(String previousHash){
        this.blockInit();
        this.previousHash = previousHash;
    }

    public void blockInit(){
        timestamp = new Date();
        merkleTree = new MerkleTree();
        hash = "";
    }

    public String generateHash(){
        return Hash.hash(this.previousHash + this.timestamp.toString() + String.valueOf(nonce) + merkleTree.getHash());
    }

    public void addTransaction(Key publicKey, String message){
        merkleTree.addTransaction(new Transaction(message, new Date()), publicKey);
    }

    public static String zerosOfLength(int length)
    {
        String result = "";
        for(int i = 0; i < length; i++)
        {
            result += "0";
        }
        return result;
    }

    public String getHash() {
        return hash;
    }

    public void mine(){
        hash = this.generateHash();
        while (!hash.substring(0, ZEROS).equals(zerosOfLength(ZEROS))) {
            nonce++;
            hash = this.generateHash();
        }
    }

    @Override
    public String toString() {
        return "[Block:" +
                "\nZeros: " + ZEROS +
                "\nHash: '" + hash + '\'' +
                "\nPreviousHash: '" + previousHash + '\'' +
                "\nMerkleTree: " + merkleTree +
                "\nTimestamp: " + timestamp +
                "\nNonce: " + nonce + "]";
    }
}
