package main;

import data.ClackData;
import data.MessageClackData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
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
    private boolean closeConnection; //true is closed, false is open
    private ArrayList<ServerSideClientIO> serverSideClientIOList;


    /**
     * General purpose constructor to set up port.
     *
     * @param port integer representing port number on server connected to.
     */
    public ClackServer(int port) {
        try {
            if (port < 1024)
                throw new IllegalArgumentException("Port must be greater than 1024");
            this.port = port;
            serverSideClientIOList = new ArrayList<ServerSideClientIO>();

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

    /**
     * A method to start the connection to the server
     */
// needs to be changed to create and run threads wrapped around ServerSideClientIO
// runnable objects
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("Waiting for a client to make connection...");
            Socket skt;
            ServerSideClientIO obj;
            while (!closeConnection) {
                skt = serverSocket.accept();
                System.out.println("Connection made, waiting for stuff...");
                if (skt.isConnected()) {
//                    obj = new ServerSideClientIO();
//                    serverSideClientIOList.add(obj);
                }
                if (closeConnection) {
                    break;
                }
            }
        } catch (SocketException se) {
            closeConnection = true;
            System.err.println("Socket Exception: " + se.getMessage());
        } catch (IOException ioe) {
            closeConnection = true;
            System.err.println("IO Exception: " + ioe.getMessage());
        }
    }

    /**
     * A method to broadcast data to the clients
     *
     * @param dataToBroadcastToClients ClackData object representing the data that is being broadcasted
     */
    synchronized public void broadcast(ClackData dataToBroadcastToClients) {
        for (ServerSideClientIO s : serverSideClientIOList) {
            s.setDataToSendToClient(dataToBroadcastToClients);
            s.sendData();
        }
    }

    /**
     * A method to remove a client from the server
     *
     * @param serverSideClientToRemove ServerSideClientIO object representing the client being removed
     */
    synchronized public void remove(ServerSideClientIO serverSideClientToRemove) {
        serverSideClientIOList.remove(serverSideClientToRemove);
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
                closeConnection == server.closeConnection;
    }

    /**
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(port, closeConnection);
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
                '}';
    }

    /**
     * A main method to test ClackSever with command line arguments
     *
     * @author Alex Cohen
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            ClackServer server = new ClackServer();
            server.start();
        } else if (args.length == 1) {
            ClackServer server = new ClackServer(Integer.parseInt(args[0]));
            server.start();
        } else {
            System.err.println("Invalid number of arguments given, must be 0 or 1");
        }
    }
}

