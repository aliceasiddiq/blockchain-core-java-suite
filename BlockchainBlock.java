package org.blockchain.core;

import org.blockchain.transaction.BlockTransaction;
import java.util.List;

public class BlockchainBlock {
    private final String previousHash;
    private String blockHash;
    private final long timestamp;
    private final int nonce;
    private final List<BlockTransaction> transactions;

    public BlockchainBlock(String previousHash, List<BlockTransaction> transactions, int nonce) {
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.transactions = transactions;
        this.nonce = nonce;
        this.blockHash = calculateBlockHash();
    }

    public String calculateBlockHash() {
        String rawData = previousHash + timestamp + nonce + transactions.toString();
        return org.blockchain.crypto.CryptoSignatureUtil.generateSHA256Hash(rawData);
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public List<BlockTransaction> getTransactions() {
        return transactions;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }
}
