package com.ehr;


import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;


public class BlockChain {
    private static final String
            SIGNING_ALGORITHM
            = "SHA256withRSA";
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

    public Block addBlock(String msg, byte[] signatureToVerify, PublicKey key,String patientId) throws Exception {
        byte[] input = msg.getBytes();
        if (!Verify_Digital_Signature(input, signatureToVerify, key)) throw new Exception("Invalid signature");
        Block b;
        if (counter == 0) {
            b = new Block(msg, "0",patientId);

        } else {
            b = new Block(msg, blocks.get(blocks.size() - 1).hash,patientId);
        }
        blocks.add(b);
    blocks.get(counter).mineBlock(difficulty);
        System.out.println("\nBlock Mined! -> " + blocks.get(blocks.size() - 1).hash);
        System.out.println("\nBlockchain is Valid: " + isChainValid());
        counter ++;

        return b;
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

    public boolean
    Verify_Digital_Signature(
            byte[] input,
            byte[] signatureToVerify,
            PublicKey key)
            throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(key);
        signature.update(input);
        return signature
                .verify(signatureToVerify);
    }

    public Block getBlockByPatientId(String patientId){

                for ( Block block : blocks){

                    if(block.getPatientId().equals(patientId))
                        return block ;
                }

        return null;
    }

}