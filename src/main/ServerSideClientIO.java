package main;


import data.ClackData;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class handles sending and receiving data.
 *
 * @author Alex Cohen
 */

public class ServerSideClientIO {

    // instance variable declarations
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    public ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.closeConnection = false;
        dataToReceiveFromClient = dataToSendToClient = null;
        inFromClient = null;
        outToClient = null;
    }

    public void run() {

    }

    public void receiveData() {

    }

    public void sendData() {

    }

    public void setDataToSendToClient(ClackData dataToSendToClient) {
        this.dataToSendToClient = dataToSendToClient;
    }
}
