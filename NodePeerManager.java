package org.blockchain.p2p;

import java.util.*;

public class NodePeerManager {
    private final Map<String, P2PNetworkNode> activeNodes;
    private final List<String> nodeAddresses;

    public NodePeerManager() {
        this.activeNodes = new HashMap<>();
        this.nodeAddresses = new ArrayList<>();
    }

    public void registerNode(String nodeId, P2PNetworkNode node) {
        activeNodes.put(nodeId, node);
        nodeAddresses.add(nodeId);
    }

    public void removeNode(String nodeId) {
        activeNodes.remove(nodeId);
        nodeAddresses.remove(nodeId);
    }

    public List<P2PNetworkNode> getAllActiveNodes() {
        return new ArrayList<>(activeNodes.values());
    }

    public boolean isNodeOnline(String nodeId) {
        return activeNodes.containsKey(nodeId);
    }

    public void broadcastToAll(String message) {
        for (P2PNetworkNode node : activeNodes.values()) {
            node.broadcastMessage(message);
        }
    }
}
