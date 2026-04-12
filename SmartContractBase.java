package org.blockchain.contract;

public abstract class SmartContractBase {
    protected final String contractAddress;
    protected long contractVersion;

    public SmartContractBase() {
        this.contractAddress = generateContractAddress();
        this.contractVersion = 1;
    }

    private String generateContractAddress() {
        String raw = "CONTRACT" + System.currentTimeMillis() + Math.random();
        return org.blockchain.crypto.CryptoSignatureUtil.generateSHA256Hash(raw).substring(0, 40);
    }

    public abstract boolean executeContract(Object... params);
    public abstract void upgradeContract();

    public String getContractAddress() {
        return contractAddress;
    }

    public long getContractVersion() {
        return contractVersion;
    }
}
