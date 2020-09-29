package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

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
    private ClackData dataToReceiveFromServer;
    private ClackData dataToSendToServer;

    private Scanner inFromStd;
    private final String key = "encryption";

    /**
     * General purpose constructor to set up username, hostname, and port.
     *
     * @param userName String representing name of the client.
     * @param hostName String representing name of the computer representing the server.
     * @param port     Integer representing port number on server connected to.
     */
    public ClackClient(String userName, String hostName, int port) {
        try {
            this.userName = userName;
            this.hostName = hostName;
            this.port = port;

            if (userName == null)
                throw new IllegalArgumentException("Username cannot be null");
            if (hostName == null)
                throw new IllegalArgumentException("Hostname cannot be null");
            if (port < 1024)
                throw new IllegalArgumentException("Port must be at least 1024");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal argument found");
        }

        closeConnection = false;
        dataToReceiveFromServer = dataToSendToServer = null;
    }

    /**
     * General purpose constructor to set up username, hostname.
     * Sets port to default 7000.
     *
     * @param userName String representing name of the client.
     * @param hostName String representing name of the computer representing the server.
     */
    public ClackClient(String userName, String hostName) {
        try {
            this.userName = userName;
            this.hostName = hostName;
            this.port = DEFAULT_PORT;

            if (userName == null)
                throw new IllegalArgumentException("Username cannot be null");
            if (hostName == null)
                throw new IllegalArgumentException("Hostname cannot be null");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal argument found");
        }
    }

    /**
     * General purpose constructor to set up username.
     * Sets host name to localhost, the server and client programs run on the same computer.
     *
     * @param userName String representing name of the client.
     */
    public ClackClient(String userName) {
        try {
            this.userName = userName;
            if (userName == null)
                throw new IllegalArgumentException("Username cannot be null");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal argument found");
        }
        hostName = "localhost";
    }

    /**
     * Default constructor that sets user to anonymous.
     */
    public ClackClient() {
        this(DEFAULT_NAME);
    }

    /**
     * A method to start and run the clack client until the user decides to stop.
     *
     * @author Louis Keith
     */
    public void start() {
        inFromStd = new Scanner(System.in);
        while (!closeConnection) {
            readClientData();
            dataToSendToServer = dataToReceiveFromServer;
            printData();
        }
    }

    /**
     * A method to get a command from the user and perform the appropriate action.
     * Reads data into the dataToSendToServer object and initializes it as FileClackData or MessageClackData.
     *
     * @author Louis Keith
     */
    public void readClientData() {
        // get command from user
        String userInput = inFromStd.next();

        switch (userInput) {
            // check if the user wishes to close the connection and does so
            case "DONE": {
                closeConnection = true;
                break;
            }
            // check if the user wishes to send a file name and attempts to do so
            case "SENDFILE": {
                String fileName = inFromStd.next();
                dataToSendToServer = new FileClackData(userName, fileName, 3); // 3 denotes SEND_FILE
                try {
                    ((FileClackData) dataToSendToServer).readFileContents(key);
                } catch (IOException ioe) {
                    dataToSendToServer = null;
                    System.err.println(ioe.getMessage());
                }
                break;
            }
            // check if the user wishes to list users, for now do nothing
            case "LISTUSERS": {
                // do nothing
                break;
            }
            // if the input is anything else, attempt to send a message
            default: {
                dataToSendToServer = new MessageClackData(userName, userInput, key, 2);
                break;
            }
        }
    }

    public void sendData() {

    }

    public void receiveData() {

    }

    /**
     * Reads from dataToSendToServer and prints all of the information a client.
     * may want to see in a user-friendly manner.
     *
     * @author Louis Keith
     */
    public void printData() {
        if (dataToReceiveFromServer == null) {
            System.out.println("The reference is null, there is no data to print");
        }
        // java should automatically choose the correct toString method to use to output the data
        System.out.println(dataToReceiveFromServer);
    }

    /**
     * A method to return the name of a client.
     *
     * @return A string representing the name of a client
     */
    public String getUserName() {
        return userName;
    }

    /**
     * A method to return the name of the computer representing the server.
     *
     * @return String representing name of the computer representing the server
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * A method to return an integer representing port number on server connected to.
     *
     * @return An integer representing port number on server connected to
     */
    public int getPort() {
        return port;
    }

    /**
     * A method to determine if two clients are equal.
     *
     * @param other FileClackData object representing another file.
     * @return boolean representing if the other parameter equals this instance of ClackClient.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ClackClient client = (ClackClient) other;
        return port == client.port &&
                closeConnection == client.closeConnection &&
                Objects.equals(userName, client.userName) &&
                Objects.equals(hostName, client.hostName) &&
                Objects.equals(dataToReceiveFromServer, client.dataToReceiveFromServer) &&
                Objects.equals(dataToSendToServer, client.dataToSendToServer);
    }

    /**
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userName, hostName, port, closeConnection, dataToReceiveFromServer, dataToSendToServer);
    }

    /**
     * A method to return the entire client object as a string.
     *
     * @return A string with all of the instance data for the client.
     */
    @Override
    public String toString() {
        return "ClackClient{" +
                "userName='" + userName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", port=" + port +
                ", closeConnection=" + closeConnection +
                ", dataToReceiveFromClient=" + dataToReceiveFromServer +
                ", dataToSendToClient=" + dataToSendToServer +
                '}';
    }
}
