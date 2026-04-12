package org.blockchain.transaction;

public class BlockTransaction {
    private final String transactionId;
    private final String senderAddress;
    private final String recipientAddress;
    private final double amount;
    private final long timestamp;
    private String signature;

    public BlockTransaction(String sender, String recipient, double amount) {
        this.senderAddress = sender;
        this.recipientAddress = recipient;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
        this.transactionId = generateTransactionId();
    }

    private String generateTransactionId() {
        String raw = senderAddress + recipientAddress + amount + timestamp;
        return org.blockchain.crypto.CryptoSignatureUtil.generateSHA256Hash(raw);
    }

    public void signTransaction(String signature) {
        this.signature = signature;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public double getAmount() {
        return amount;
    }

    public String getSignature() {
        return signature;
    }
}
