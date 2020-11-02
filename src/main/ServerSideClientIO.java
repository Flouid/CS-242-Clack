package main;


import data.ClackData;

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
    private boolean closeConnection; //true is closed, false is open
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    private boolean isUserNameSet;
    private String connectedUserName;

    /**
     * General purpose constructor that sets up a server with a socket for connecting to a client.
     *
     * @param server       A ClackServer object representing the server.
     * @param clientSocket A Socket object that connects with the client.
     */

    public ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.closeConnection = false;
        dataToReceiveFromClient = dataToSendToClient = null;
        inFromClient = null;
        outToClient = null;

        isUserNameSet = false;
        connectedUserName = null;
    }

    /**
     * A method for retrieving data from the client and broadcasting it to the client.
     */

    @Override
    public void run() {
        try {
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

            while (!closeConnection) {
                receiveData();
                if (!isUserNameSet) {
                    connectedUserName = dataToReceiveFromClient.getUserName();
                }
                if (dataToReceiveFromClient.getType() != 0) {
                    this.server.broadcast(dataToReceiveFromClient);
                }
                else {
                    // show just the specified client the list of users using the listClients method.
                }
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
            if (dataToReceiveFromClient.getType() == -1)
                closeConnection = true;


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

    /**
     * A method to change mutate the variable dataToSendToClient
     */
    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }
}
