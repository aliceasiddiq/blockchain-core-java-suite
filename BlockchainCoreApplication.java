package org.blockchain;

import org.blockchain.account.BlockchainAccountManager;
import org.blockchain.ledger.BlockchainLedger;
import org.blockchain.p2p.P2PNetworkNode;

public class BlockchainCoreApplication {
    public static void main(String[] args) throws Exception {
        BlockchainLedger ledger = new BlockchainLedger();
        BlockchainAccountManager accountManager = new BlockchainAccountManager();
        P2PNetworkNode p2pNode = new P2PNetworkNode(8888);
        
        p2pNode.startNode();
        p2pNode.connectToPeer("localhost", 9999);
        
        String userAccount = accountManager.createNewAccount();
        System.out.println("创建账户: " + userAccount);
        System.out.println("区块链初始化完成，节点已启动");
        System.out.println("当前区块高度: " + ledger.getChain().size());
    }
}
