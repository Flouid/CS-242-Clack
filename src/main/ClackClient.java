package main;

import data.ClackData;

import java.util.Objects;

/**
 * This class is the basic structure for the client in Clack.
 *
 * @author Alex Cohen
 */
public class ClackClient {
    // default values
    private final static int DEFAULT_PORT = 7000;
    private final static String DEFAULT_NAME = "Anonymous";

    // instance variable declarations
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection; //true is closed, false is open
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    /**
     * General purpose constructor to set up username, hostname, and port
     *
     * @param userName String representing name of the client
     * @param hostName String representing name of the computer representing the server
     * @param port     integer representing port number on server connected to
     */
    ClackClient(String userName, String hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;

        closeConnection = false;
        dataToReceiveFromClient = dataToSendToClient = null;
    }

    /**
     * General purpose constructor to set up username, hostname
     * Sets port to default 7000
     *
     * @param userName String representing name of the client
     * @param hostName String representing name of the computer representing the server
     */
    ClackClient(String userName, String hostName) {
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * General purpose constructor to set up username
     * Sets host name to localhost, the server and client programs run on the same computer
     *
     * @param userName String representing name of the client
     */
    ClackClient(String userName) {
        this.userName = userName;
        hostName = "localhost";
    }

    /**
     * Default constructor that sets user to anonymous
     */
    ClackClient() {
        this(DEFAULT_NAME);
    }

    public void start() {

    }

    public void readClientData() {

    }

    public void sendData() {

    }

    public void receiveData() {

    }

    public void printData() {

    }

    public String getUserName() {
        return userName;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    /**
     * A method to determine if two clients are equal.
     *
     * @param other FileClackData object representing another file
     * @return boolean representing if the other parameter equals this instance of ClackClient
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ClackClient that = (ClackClient) other;
        return port == that.port &&
                closeConnection == that.closeConnection &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(hostName, that.hostName) &&
                Objects.equals(dataToReceiveFromClient, that.dataToReceiveFromClient) &&
                Objects.equals(dataToSendToClient, that.dataToSendToClient);
    }

    /**
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(userName, hostName, port, closeConnection, dataToReceiveFromClient, dataToSendToClient);
    }

    /**
     * A method to return the entire client object as a string.
     *
     * @return A string with all of the instance data for the client
     */
    @Override
    public String toString() {
        return "ClackClient{" +
                "userName='" + userName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", port=" + port +
                ", closeConnection=" + closeConnection +
                ", dataToReceiveFromClient=" + dataToReceiveFromClient +
                ", dataToSendToClient=" + dataToSendToClient +
                '}';
    }
}
