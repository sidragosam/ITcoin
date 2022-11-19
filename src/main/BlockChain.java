package main;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    private List<Block> blockchain;

    public BlockChain() {
        blockchain = new ArrayList<>();
        Block block = new Block();
        blockchain.add(block);
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(List<Block> blockchain) {
        this.blockchain = blockchain;
    }

    public void addBlock(){
        this.mineBlocks();
        Block block = new Block(this.lastBlock().getHash());
        blockchain.add(block);
    }

    public Block lastBlock(){
        return blockchain.get(blockchain.size()-1);
    }

    public void mineBlocks(){
        this.lastBlock().mine();
    }

    public void addTransaction(Key pkey, String msg){
        this.lastBlock().addTransaction(pkey, msg);
    }

    @Override
    public String toString() {
        return "\nBlockChain: " + blockchain;
    }
}
