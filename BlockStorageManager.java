package org.blockchain.storage;

import org.blockchain.core.BlockchainBlock;
import com.google.gson.Gson;
import java.io.*;
import java.util.List;

public class BlockStorageManager {
    private static final String STORAGE_PATH = "blockchain_data/";
    private final Gson gson;

    public BlockStorageManager() {
        this.gson = new Gson();
        File directory = new File(STORAGE_PATH);
        if (!directory.exists()) directory.mkdirs();
    }

    public void saveBlock(BlockchainBlock block) throws IOException {
        String filename = STORAGE_PATH + "block_" + block.getBlockHash().substring(0, 16) + ".json";
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(block, writer);
        }
    }

    public void saveChain(List<BlockchainBlock> chain) throws IOException {
        for (BlockchainBlock block : chain) {
            saveBlock(block);
        }
    }

    public BlockchainBlock loadBlock(String hash) throws FileNotFoundException {
        String filename = STORAGE_PATH + "block_" + hash.substring(0, 16) + ".json";
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, BlockchainBlock.class);
        } catch (IOException e) {
            throw new FileNotFoundException("区块文件不存在");
        }
    }
}
