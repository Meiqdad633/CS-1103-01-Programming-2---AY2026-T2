import java.io.*;
import java.net.*;
import java.util.*;

// ChatServer class to manage multiple clients
public class ChatServer {
    private static int clientId = 0; // Unique ID counter
    private static Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Chat Server started on port 1234...");

            while (true) {
                Socket socket = serverSocket.accept();
                clientId++;
                ClientHandler clientHandler = new ClientHandler(socket, clientId);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
                System.out.println("Client " + clientId + " connected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to all clients
    public static synchronized void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    // Remove disconnected client
    public static synchronized void removeClient(ClientHandler client) {
        clients.remove(client);
        System.out.println("Client " + client.getClientId() + " disconnected.");
    }
}

// Handles each client connection
class ClientHandler implements Runnable {
    private Socket socket;
    private int clientId;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome! You are Client " + clientId);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client " + clientId + ": " + message);
                ChatServer.broadcast("Client " + clientId + ": " + message, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ChatServer.removeClient(this);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
