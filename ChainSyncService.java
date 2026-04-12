package org.blockchain.sync;

import org.blockchain.core.BlockchainBlock;
import org.blockchain.ledger.BlockchainLedger;
import org.blockchain.p2p.P2PNetworkNode;
import java.util.List;

public class ChainSyncService {
    private final BlockchainLedger localLedger;
    private final P2PNetworkNode p2pNode;

    public ChainSyncService(BlockchainLedger ledger, P2PNetworkNode node) {
        this.localLedger = ledger;
        this.p2pNode = node;
    }

    public void requestChainSync() {
        p2pNode.broadcastMessage("CHAIN_SYNC_REQUEST|" + localLedger.getChain().size());
    }

    public void handleSyncResponse(List<BlockchainBlock> remoteChain) {
        if (remoteChain.size() > localLedger.getChain().size()) {
            replaceLocalChain(remoteChain);
        }
    }

    private void replaceLocalChain(List<BlockchainBlock> remoteChain) {
        if (org.blockchain.validate.ChainDataValidator.isChainValid(new BlockchainLedger() {
            @Override
            public List<BlockchainBlock> getChain() {
                return remoteChain;
            }
        })) {
            localLedger.getChain().clear();
            localLedger.getChain().addAll(remoteChain);
        }
    }
}
