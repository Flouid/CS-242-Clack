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

public class ServerSideClientIO implements Runnable{

    // instance variable declarations
    private boolean closeConnection; //true is closed, false is open
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

    @Override
    public void run() {
        try {
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

            while(!closeConnection){
                receiveData();
                this.server.broadcast(dataToReceiveFromClient);
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
