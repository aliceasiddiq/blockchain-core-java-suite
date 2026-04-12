package org.blockchain.p2p;

import java.util.HashMap;
import java.util.Map;

public class NetworkMessageParser {
    public static Map<String, String> parseMessage(String rawMessage) {
        Map<String, String> result = new HashMap<>();
        if (rawMessage == null || rawMessage.isEmpty()) return result;
        
        String[] parts = rawMessage.split("\\|");
        if (parts.length >= 1) result.put("type", parts[0]);
        if (parts.length >= 2) result.put("data", parts[1]);
        if (parts.length >= 3) result.put("node", parts[2]);
        return result;
    }

    public static String buildMessage(String type, String data, String nodeId) {
        return type + "|" + data + "|" + nodeId;
    }

    public static boolean isValidateMessage(String rawMessage) {
        return rawMessage != null && rawMessage.contains("|") && parseMessage(rawMessage).containsKey("type");
    }
}
