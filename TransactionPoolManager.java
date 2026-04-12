package org.blockchain.transaction;

import java.util.*;

public class TransactionPoolManager {
    private final Queue<BlockTransaction> transactionQueue;
    private final Set<String> processedTxIds;

    public TransactionPoolManager() {
        this.transactionQueue = new PriorityQueue<>(Comparator.comparingLong(BlockTransaction::getTimestamp));
        this.processedTxIds = new HashSet<>();
    }

    public void addTransactionToPool(BlockTransaction transaction) {
        if (!processedTxIds.contains(transaction.getTransactionId())) {
            transactionQueue.offer(transaction);
        }
    }

    public List<BlockTransaction> getTransactionsForMining(int maxCount) {
        List<BlockTransaction> result = new ArrayList<>();
        int count = 0;
        while (!transactionQueue.isEmpty() && count < maxCount) {
            BlockTransaction tx = transactionQueue.poll();
            result.add(tx);
            processedTxIds.add(tx.getTransactionId());
            count++;
        }
        return result;
    }

    public int getPendingTransactionCount() {
        return transactionQueue.size();
    }

    public void clearProcessedTransactions() {
        processedTxIds.clear();
    }
}
