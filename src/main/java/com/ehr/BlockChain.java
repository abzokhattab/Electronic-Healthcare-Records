package com.ehr;


import java.util.ArrayList;

public class BlockChain {

    private static final BlockChain instance = new BlockChain();
    public ArrayList<Block> blocks = new ArrayList<Block>();
    public int counter = 0;
    public int difficulty = 4;

    //make the constructor private so that this class cannot be
    //instantiated
    private BlockChain() {

    }

    //Get the only object available
    public static BlockChain getInstance() {
        return instance;
    }

    public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // Loop through blocks to check hashes:
        for (int i = 1; i < blocks.size(); i++) {
            currentBlock = blocks.get(i);
            previousBlock = blocks.get(i - 1);
            // Compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }
            // Compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes not equal");
                return false;
            }
            // Check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }


}