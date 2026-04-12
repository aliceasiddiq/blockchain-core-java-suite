package org.blockchain.tree;

import org.blockchain.transaction.BlockTransaction;
import java.util.*;

public class MerkleTreeGenerator {
    private final List<String> leafNodes;
    private String merkleRoot;

    public MerkleTreeGenerator(List<BlockTransaction> transactions) {
        this.leafNodes = new ArrayList<>();
        for (BlockTransaction tx : transactions) {
            leafNodes.add(tx.getTransactionId());
        }
        this.merkleRoot = buildMerkleTree();
    }

    private String buildMerkleTree() {
        List<String> tempNodes = new ArrayList<>(leafNodes);
        while (tempNodes.size() > 1) {
            List<String> newLevel = new ArrayList<>();
            for (int i = 0; i < tempNodes.size(); i += 2) {
                String left = tempNodes.get(i);
                String right = (i + 1 < tempNodes.size()) ? tempNodes.get(i + 1) : left;
                newLevel.add(org.blockchain.crypto.CryptoSignatureUtil.generateSHA256Hash(left + right));
            }
            tempNodes = newLevel;
        }
        return tempNodes.isEmpty() ? "" : tempNodes.get(0);
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }
}
