package main;


import data.ClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * This class handles sending and receiving data.
 *
 * @author Alex Cohen
 */

public class ServerSideClientIO implements Runnable {

    // instance variable declarations
    private boolean closeConnection; // true is closed, false is open
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private final ClackServer server;
    private final Socket clientSocket;

    private String connectedUserName;

    /**
     * The standard constructor to initialize a ServerSideClientIO object.
     * This object handles all of the IO with the client on the server side of things.
     *
     * @param server The server that the object communicates with.
     * @param clientSocket The socket that the client is connected to.
     */
    public ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        closeConnection = false;
        dataToReceiveFromClient = dataToSendToClient = null;
        inFromClient = null;
        outToClient = null;

        connectedUserName = null;
    }

    /**
     * The overall run method for the ServerSideClientIO object.
     * Receives data and then broadcasts it to all of the other clients.
     */
    @Override
    public void run() {
        try {
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

            receiveClientName();

            while (!closeConnection) {
                receiveData();
                server.broadcast(dataToReceiveFromClient);
            }

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * A method to receive data from the client
     */
    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
            if (dataToReceiveFromClient.getType()  == ClackData.CONSTANT_LOGOUT) {
                server.remove(this);
                closeConnection = true;
            }
            else if (dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS) {
                String username = dataToReceiveFromClient.getUserName();
                String userList = server.getUsers();
                dataToReceiveFromClient = new MessageClackData(username, userList, ClackData.CONSTANT_LISTUSERS);
            }
        } catch (IOException | ClassNotFoundException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * A method to send data to the client
     */
    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public ClackData getDataToSendToClient() {
        return dataToSendToClient;
    }

    /**
     * A method to receive the client name.
     * The first message that the client sends upon connecting contains only it's name.
     */
    public void receiveClientName() {
        receiveData();
        connectedUserName = dataToReceiveFromClient.getUserName();
    }

    /**
     * A simple getter method to return the connected client's name.
     *
     * @return A string containing the username.
     */
    public String getUserName() {
        return connectedUserName;
    }

    /**
     * A method to change mutate the variable dataToSendToClient
     */
    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }
}
