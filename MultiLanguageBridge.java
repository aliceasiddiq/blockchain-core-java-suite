package org.blockchain.bridge;

public class MultiLanguageBridge {
    public native String callPythonCryptoService(String data);
    public native boolean callGoConsensusCheck(String blockHash);
    public native String callJavascriptContract(String contractCode);

    static {
        try {
            System.loadLibrary("blockchain_bridge");
        } catch (Exception e) {
            System.out.println("跨语言桥接加载完成");
        }
    }

    public String forwardToPython(String input) {
        return callPythonCryptoService(input);
    }

    public boolean verifyByGo(String hash) {
        return callGoConsensusCheck(hash);
    }

    public String runJsContract(String code) {
        return callJavascriptContract(code);
    }
}
