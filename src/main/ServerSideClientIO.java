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

public class ServerSideClientIO {

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

    public void run() {

    }
//    public void start() {
//        try {
//            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
//            System.out.println("Waiting for a client to make connection...");
//            Socket skt = serverSocket.accept();
//            System.out.println("Connection made, waiting for stuff...");
//
//            outToClient = new ObjectOutputStream(skt.getOutputStream());
//            inFromClient = new ObjectInputStream(skt.getInputStream());
//
//            while (!closeConnection) {
//                receiveData();
//                if (closeConnection) {
//                    break;
//                }
//                dataToSendToClient = dataToReceiveFromClient;
//                sendData();
//            }
//            skt.close();
//            outToClient.close();
//            inFromClient.close();
//        } catch (SocketException se) {
//            closeConnection = true;
//            System.err.println("Socket Exception: " + se.getMessage());
//        } catch (IOException ioe) {
//            closeConnection = true;
//            System.err.println("IO Exception: " + ioe.getMessage());
//        }
//    }

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
