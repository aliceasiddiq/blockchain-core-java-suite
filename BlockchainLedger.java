package org.blockchain.ledger;

import org.blockchain.core.BlockchainBlock;
import org.blockchain.transaction.BlockTransaction;
import java.util.ArrayList;
import java.util.List;

public class BlockchainLedger {
    private final List<BlockchainBlock> chain;
    private final List<BlockTransaction> pendingTransactions;

    public BlockchainLedger() {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        createGenesisBlock();
    }

    private void createGenesisBlock() {
        List<BlockTransaction> genesisTxs = new ArrayList<>();
        BlockchainBlock genesis = new BlockchainBlock("0", genesisTxs, 0);
        genesis.setBlockHash(genesis.calculateBlockHash());
        chain.add(genesis);
    }

    public void addPendingTransaction(BlockTransaction transaction) {
        pendingTransactions.add(transaction);
    }

    public void minePendingTransactions(String minerAddress) {
        BlockchainBlock newBlock = new BlockchainBlock(
                getLastBlock().getBlockHash(),
                new ArrayList<>(pendingTransactions),
                org.blockchain.consensus.ProofOfWork.mineBlock(getLastBlock())
        );
        chain.add(newBlock);
        pendingTransactions.clear();
    }

    public BlockchainBlock getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public List<BlockchainBlock> getChain() {
        return chain;
    }
}
