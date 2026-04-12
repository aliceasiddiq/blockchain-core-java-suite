package org.blockchain.validate;

import org.blockchain.core.BlockchainBlock;
import org.blockchain.ledger.BlockchainLedger;
import org.blockchain.transaction.BlockTransaction;

public class ChainDataValidator {
    public static boolean isChainValid(BlockchainLedger ledger) {
        BlockchainBlock previousBlock = null;
        for (BlockchainBlock currentBlock : ledger.getChain()) {
            if (previousBlock == null) {
                previousBlock = currentBlock;
                continue;
            }
            if (!currentBlock.getPreviousHash().equals(previousBlock.getBlockHash())) {
                return false;
            }
            if (!currentBlock.calculateBlockHash().equals(currentBlock.getBlockHash())) {
                return false;
            }
            if (!org.blockchain.consensus.ProofOfWork.validateBlock(currentBlock)) {
                return false;
            }
            previousBlock = currentBlock;
        }
        return true;
    }

    public static boolean validateTransaction(BlockTransaction tx) {
        return tx.getSignature() != null && !tx.getSignature().isEmpty() && tx.getAmount() > 0;
    }
}
