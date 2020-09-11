package main2;

import data.ClackData;

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


    /**
     * General purpose constructor to set up port
     *
     * @param port integer representing port number on server connected to
     */
    public ClackServer(int port) {
        this.port = port;

        dataToReceiveFromClient = dataToSendToClient = null;
    }

    /**
     * Default constructor that sets port to default port number 7000
     */
    public ClackServer() {
        this(DEFAULT_PORT);
    }

    public void start() {

    }

    public void receiveData() {

    }

    public void sendData() {

    }

    /**
     * A method to return a server's port
     *
     * @return An integer representing a server's port
     */
    public int getPort() {
        return port;
    }

    /**
     * A method to determine if two Servers are equal.
     *
     * @param other FileClackData object representing another file
     * @return boolean representing if the other parameter equals this instance of ClackServer
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ClackServer server = (ClackServer) other;
        return port == server.port &&
                closeConnection == server.closeConnection &&
                dataToSendToClient.equals(server.dataToSendToClient) &&
                dataToReceiveFromClient.equals(server.dataToReceiveFromClient);
    }

    /**
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(port, closeConnection, dataToReceiveFromClient, dataToSendToClient);
    }

    /**
     * A method to return the entire server object as a string.
     *
     * @return A string with all of the instance data for the server
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
}
