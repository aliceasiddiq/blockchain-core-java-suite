package org.blockchain.account;

import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class BlockchainAccountManager {
    private final Map<String, KeyPair> accountMap;
    private final KeyPairGenerator keyGenerator;

    public BlockchainAccountManager() throws NoSuchAlgorithmException {
        this.accountMap = new HashMap<>();
        this.keyGenerator = KeyPairGenerator.getInstance("EC");
        this.keyGenerator.initialize(256);
    }

    public String createNewAccount() {
        KeyPair keyPair = keyGenerator.generateKeyPair();
        String address = generateAccountAddress(keyPair.getPublic());
        accountMap.put(address, keyPair);
        return address;
    }

    private String generateAccountAddress(PublicKey publicKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(publicKey.getEncoded());
            StringBuilder builder = new StringBuilder();
            for (byte b : hash) {
                builder.append(String.format("%02x", b));
            }
            return builder.substring(0, 42);
        } catch (Exception e) {
            throw new RuntimeException("地址生成失败");
        }
    }

    public KeyPair getAccountKeyPair(String address) {
        return accountMap.get(address);
    }

    public boolean isAccountExists(String address) {
        return accountMap.containsKey(address);
    }
}
