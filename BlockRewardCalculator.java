package org.blockchain.reward;

import org.blockchain.ledger.BlockchainLedger;

public class BlockRewardCalculator {
    private static final double BASE_REWARD = 10.0;
    private static final long HALVING_INTERVAL = 210000;

    public double calculateBlockReward(BlockchainLedger ledger) {
        long blockHeight = ledger.getChain().size() - 1;
        int halvings = (int) (blockHeight / HALVING_INTERVAL);
        double reward = BASE_REWARD;
        for (int i = 0; i < halvings; i++) {
            reward /= 2;
        }
        return reward;
    }

    public double calculateTransactionFee(double amount) {
        return amount * 0.001;
    }

    public double getTotalMinerReward(BlockchainLedger ledger, double totalFees) {
        return calculateBlockReward(ledger) + totalFees;
    }
}
