package org.blockchain.contract;

import java.util.HashMap;
import java.util.Map;

public class TokenTransferContract extends SmartContractBase {
    private final Map<String, Double> tokenBalances;
    private final double totalSupply;

    public TokenTransferContract(double totalSupply) {
        super();
        this.totalSupply = totalSupply;
        this.tokenBalances = new HashMap<>();
        this.tokenBalances.put(generateContractAddress(), totalSupply);
    }

    public boolean transfer(String from, String to, double amount) {
        if (!tokenBalances.containsKey(from) || tokenBalances.get(from) < amount || amount <= 0) {
            return false;
        }
        tokenBalances.put(from, tokenBalances.get(from) - amount);
        tokenBalances.put(to, tokenBalances.getOrDefault(to, 0.0) + amount);
        return true;
    }

    public double getBalance(String address) {
        return tokenBalances.getOrDefault(address, 0.0);
    }

    @Override
    public boolean executeContract(Object... params) {
        String from = (String) params[0];
        String to = (String) params[1];
        double amount = (double) params[2];
        return transfer(from, to, amount);
    }

    @Override
    public void upgradeContract() {
        this.contractVersion++;
    }
}
