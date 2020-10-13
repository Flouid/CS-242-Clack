package main;

import data.ClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * This class is the basic structure for the server in Clack.
 *
 * @author Alex Cohen
 */

public class ClackServer {
    // default values
    private final static int DEFAULT_PORT = 7000;

    // instance variable declarations
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;


    /**
     * General purpose constructor to set up port.
     *
     * @param port integer representing port number on server connected to.
     */
    public ClackServer(int port) {
        try {
            if (port < 1024)
                throw new IllegalArgumentException("Port must be greater thatn 1024");
            this.port = port;

            dataToReceiveFromClient = dataToSendToClient = null;
            inFromClient = null;
            outToClient = null;
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        }
    }

    /**
     * Default constructor that sets port to default port number 7000.
     */
    public ClackServer() {
        this(DEFAULT_PORT);
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("Waiting for a client to make connection...");
            Socket skt = serverSocket.accept();
            System.out.println("Connection made, waiting for stuff...");

            outToClient = new ObjectOutputStream(skt.getOutputStream());
            inFromClient = new ObjectInputStream(skt.getInputStream());

            while (!closeConnection) {
                receiveData();
                sendData();
            }
            skt.close();
            outToClient.close();
            inFromClient.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * A method to return a server's port
     *
     * @return An integer representing a server's port.
     */
    public int getPort() {
        return port;
    }

    /**
     * A method to determine if two Servers are equal.
     *
     * @param other FileClackData object representing another file.
     * @return boolean representing if the other parameter equals this instance of ClackServer.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ClackServer server = (ClackServer) other;
        return port == server.port &&
                closeConnection == server.closeConnection &&
                Objects.equals(dataToReceiveFromClient, server.dataToReceiveFromClient) &&
                Objects.equals(dataToSendToClient, server.dataToSendToClient);
    }

    /**
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(port, closeConnection, dataToReceiveFromClient, dataToSendToClient);
    }

    /**
     * A method to return the entire server object as a string.
     *
     * @return A string with all of the instance data for the server.
     */
    @Override
    public String toString() {
        return "ClackServer{" +
                "port=" + port +
                ", closeConnection=" + closeConnection +
                ", dataToReceiveFromClient=" + dataToReceiveFromClient +
                ", dataToSendToClient=" + dataToSendToClient +
                '}';
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            ClackServer server = new ClackServer();
            server.start();
        } else if (args.length == 1) {
            ClackServer server = new ClackServer(Integer.parseInt(args[0]));
        }
    }
}

