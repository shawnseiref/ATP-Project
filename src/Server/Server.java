package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService executorService;
    private static final Logger LOG = Logger.getLogger(Server.class.getName());

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        LOG.setLevel(Level.WARNING);
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            LOG.info(String.format("Server started! (port: %s)", port));
            while (!stop) {
                try {
                    Socket clientSocket = server.accept(); // blocking call
                    LOG.info(String.format("Client excepted: %s", clientSocket.toString()));
                    new Thread(() -> {
                        handleClient(clientSocket);
                    }).start();
                } catch (SocketTimeoutException e) {
                    LOG.warning("SocketTimeout - No clients pending!");
                }
            }
        } catch (IOException e) {
            LOG.severe(String.format("IOException: ", e));
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            LOG.info("Client axcepted!");
            LOG.info(String.format("Handling client with socket: %s", clientSocket.toString()));
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();

        } catch (IOException e){
            LOG.severe(String.format("IOException: ", e));
        }
    }

    public void stop() {
        LOG.info("Stopping server..");
        stop = true;
    }
}
