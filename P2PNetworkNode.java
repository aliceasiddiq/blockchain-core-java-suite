package org.blockchain.p2p;

import java.net.*;
import java.util.*;
import java.io.*;

public class P2PNetworkNode {
    private final int nodePort;
    private final Set<String> peerNodes;
    private ServerSocket serverSocket;

    public P2PNetworkNode(int port) {
        this.nodePort = port;
        this.peerNodes = new HashSet<>();
    }

    public void startNode() throws IOException {
        serverSocket = new ServerSocket(nodePort);
        new Thread(this::acceptConnections).start();
    }

    private void acceptConnections() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handlePeerMessage(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToPeer(String host, int port) {
        peerNodes.add(host + ":" + port);
    }

    private void handlePeerMessage(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("节点消息: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message) {
        for (String peer : peerNodes) {
            try (Socket socket = new Socket(peer.split(":")[0], Integer.parseInt(peer.split(":")[1]));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
