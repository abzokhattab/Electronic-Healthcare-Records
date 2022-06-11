package com.ehr.models;

import com.ehr.Block;
import com.ehr.BlockChain;
import com.ehr.algorithms.AES_ENCRYPTION;
import com.google.gson.Gson;

import javax.crypto.SecretKey;
import java.security.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Doctor {

    // Signing Algorithm
    private static final String
            SIGNING_ALGORITHM
            = "SHA256withRSA";
    private static final String RSA = "RSA";
    private static Scanner sc;
    private final PrivateKey privateKey;
    //    private final SecretKey symmetricKey;
    public PublicKey publicKey;

    public String id;
    private final HashMap<String, SecretKey> patientsKeys;

    //private HashMap<String,String>
    public Doctor() throws Exception {
        patientsKeys = new HashMap<String, SecretKey>();
        //  symmetricKey = AES_ENCRYPTION.generateKey();
        KeyPair keyPair = Generate_RSA_KeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
        this.id = UUID.randomUUID().toString();
    }

    // Function to implement Digital signature
    // using SHA256 and RSA algorithm
    // by passing private key.
    public static byte[] Create_Digital_Signature(
            byte[] input,
            PrivateKey Key)
            throws Exception {
        Signature signature
                = Signature.getInstance(
                SIGNING_ALGORITHM);
        signature.initSign(Key);
        signature.update(input);
        return signature.sign();
    }

    // Generating the asymmetric key pair
    // using SecureRandom class
    // functions and RSA algorithm.
    public static KeyPair Generate_RSA_KeyPair()
            throws Exception {
        SecureRandom secureRandom
                = new SecureRandom();
        KeyPairGenerator keyPairGenerator
                = KeyPairGenerator
                .getInstance(RSA);
        keyPairGenerator
                .initialize(
                        2048, secureRandom);
        return keyPairGenerator
                .generateKeyPair();
    }

    public HashMap<String, SecretKey> getPatientsKeys() {
        return patientsKeys;
    }

    // Function for Verification of the
    // digital signature by using the public key


    // Driver Code

    public Block addPatient(Patient patient) throws Exception {
        SecretKey symmetricKey = AES_ENCRYPTION.generateKey();
        patientsKeys.put(patient.id, symmetricKey);

        String dataEncrypted = AES_ENCRYPTION.encrypt(patient.toString(), symmetricKey);
        byte[] signature
                = Create_Digital_Signature(
                dataEncrypted.getBytes(),
                privateKey);
        BlockChain blockchain = BlockChain.getInstance();
        Block b = blockchain.addBlock(dataEncrypted, signature, publicKey, patient.id, "PATIENT");
        System.out.println(b);
        return b;
    }

    public Object addVisit(String patientId, Visit v) throws Exception {
        SecretKey symmetricKey = patientsKeys.get(patientId);
        if (symmetricKey ==null) return "Unauthorized action";
        String dataEncrypted = AES_ENCRYPTION.encrypt(v.toString(), symmetricKey);
        byte[] signature
                = Create_Digital_Signature(
                dataEncrypted.getBytes(),
                privateKey);
        BlockChain blockchain = BlockChain.getInstance();
        Block b = blockchain.addBlock(dataEncrypted, signature, publicKey, patientId, "VISIT");
        System.out.println(b);
        return b;
    }


    //    public  ArrayList<Patient>  getPatients(){
//        ArrayList<Patient> patients= new ArrayList<Patient>();
//        Block block =BlockChain.getInstance().getBlockByPatientId(patientId);
//        AES_ENCRYPTION.decrypt(block.getData(),symmetricKey)
//    }
//    public  static  String  getPatient(String patientId, SecretKey symmetricKey){
//
//    Block block =BlockChain.getInstance().getBlockByPatientId(patientId);
//    return AES_ENCRYPTION.decrypt(block.getData(),symmetricKey);
//
//    }

    public Object getPatient(String patientId) throws Exception {

        SecretKey symmetricKey = patientsKeys.get(patientId);
        if (symmetricKey == null)  return "Unauthorized action";
        ArrayList<Block> blocks = BlockChain.getInstance().getBlockByPatientId(patientId);
        ArrayList<Object> result = new ArrayList<Object>();
        for (Block block : blocks) {
            Gson gson = new Gson();
            String data = AES_ENCRYPTION.decrypt(block.getData(), symmetricKey);
            if (block.getType().contains("VISIT"))
                result.add(gson.fromJson(data, Visit.class));
            else
                result.add(gson.fromJson(data, Patient.class));

        }
        return result;

    }
//    public static void main(String args[])
//                throws Exception
//        {
//
//            String input
//                    = "GEEKSFORGEEKS IS A"
//                    + " COMPUTER SCIENCE PORTAL";
//
//
//            // Function Call
//            byte[] signature
//                    = Create_Digital_Signature(
//                    input.getBytes(),
//                    keyPair.getPrivate());
//
//            System.out.println(
//                    "Signature Value:\n "
//                            + DatatypeConverter
//                            .printHexBinary(signature));
//
//            System.out.println(
//                    "Verification: "
//                            + Verify_Digital_Signature(
//                            input.getBytes(),
//                            signature, keyPair.getPublic()));
//        }

}

