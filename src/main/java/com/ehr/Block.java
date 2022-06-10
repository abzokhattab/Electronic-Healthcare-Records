package com.ehr;

import java.security.MessageDigest;
import java.util.Date;


public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    // Block Constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String getData() {
        return this.data;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public int getNonce() {
        return this.nonce;
    }

    // Calculate new hash based on blocks contents
    public String calculateHash() {
        String calculatedhash = StringUtil
                .applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        // System.out.println("Block Mined! -> " + hash);
    }
    class StringUtil {
        public static String applySha256(String input) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(input.getBytes("UTF-8"));
                StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if (hex.length() == 1)
                        hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

