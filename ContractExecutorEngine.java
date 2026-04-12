package org.blockchain.contract;

import java.lang.reflect.Method;

public class ContractExecutorEngine {
    public Object executeSmartContract(SmartContractBase contract, String methodName, Object... params) throws Exception {
        Class<?>[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }
        Method method = contract.getClass().getMethod(methodName, paramTypes);
        return method.invoke(contract, params);
    }

    public boolean validateContractExecution(SmartContractBase contract, Object... params) {
        try {
            contract.executeContract(params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void upgradeContractVersion(SmartContractBase contract) {
        contract.upgradeContract();
    }
}
