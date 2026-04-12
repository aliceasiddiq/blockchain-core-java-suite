package org.blockchain.consensus;

import org.blockchain.core.BlockchainBlock;

public class ProofOfWork {
    private static final int DIFFICULTY = 4;

    public static int mineBlock(BlockchainBlock block) {
        String target = new String(new char[DIFFICULTY]).replace('\0', '0');
        int nonce = 0;
        while (true) {
            String hash = block.calculateBlockHash();
            if (hash.startsWith(target)) {
                return nonce;
            }
            nonce++;
        }
    }

    public static boolean validateBlock(BlockchainBlock block) {
        String target = new String(new char[DIFFICULTY]).replace('\0', '0');
        String calculatedHash = block.calculateBlockHash();
        return calculatedHash.startsWith(target) && calculatedHash.equals(block.getBlockHash());
    }
}
