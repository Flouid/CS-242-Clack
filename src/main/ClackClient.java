package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class is the basic structure for the client in Clack.
 *
 * @author Alex Cohen
 */
public class ClackClient extends Application {

    // default values
    private final static int DEFAULT_PORT = 7000;
    private final static String DEFAULT_NAME = "Anonymous";
    private final static boolean DEFAULT_CLOSE_CONNECTION = false;
    private final static String DEFAULT_HOST_NAME = "localhost";
    private final static int DEFAULT_HASH_CODE = 7;

    // instance variable declarations
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection; // true when connection is closed, false when open
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;

    private Scanner inFromStd;
    private final String key = "encryption";

    @FXML
    private Button sendButton;

    @FXML
    private TextArea messages;

    @FXML
    private TextField text;

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
            closeConnection = DEFAULT_CLOSE_CONNECTION;
            dataToSendToServer = dataToReceiveFromServer = null;
            inFromServer = null;
            outToServer = null;
            closeConnection = false;
            dataToReceiveFromServer = dataToSendToServer = null;

            if (userName == null) {
                throw new IllegalArgumentException("Username cannot be null");
            }
            if (hostName == null) {
                throw new IllegalArgumentException("Hostname cannot be null");
            }
            if (port < 1024) {
                throw new IllegalArgumentException("Port must be at least 1024");
            }
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal arguments found: " + iae.getMessage());
        }

    }


    /**
     * General purpose constructor to set up username, hostname.
     * Sets port to default 7000.
     *
     * @param userName String representing name of the client.
     * @param hostName String representing name of the computer representing the server.
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * General purpose constructor to set up username.
     * Sets port to default 7000.
     * Sets host name to localhost, the server and client programs run on the same computer.
     *
     * @param userName String representing name of the client.
     */
    public ClackClient(String userName) {
        this(userName, DEFAULT_HOST_NAME);
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
    @Override
    public synchronized void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("logInScreen.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Clack");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Socket skt = new Socket(hostName, port);
        outToServer = new ObjectOutputStream(skt.getOutputStream());
        inFromServer = new ObjectInputStream(skt.getInputStream());

        (new Thread(new ClientSideServerListener(this))).start();

//        inFromStd = new Scanner(System.in);
//        try {
//            Socket skt = new Socket(hostName, port);
//            outToServer = new ObjectOutputStream(skt.getOutputStream());
//            inFromServer = new ObjectInputStream(skt.getInputStream());
//
//            (new Thread(new ClientSideServerListener(this))).start();
//
//            // send the username to the server
//            dataToSendToServer = new MessageClackData(userName, userName, key, ClackData.CONSTANT_SENDMESSAGE);
//            sendData();
//
//            while (!closeConnection) {
//                readClientData();
//                sendData();
//            }
//
//            inFromStd.close();
//            skt.close();
//
//        } catch (UnknownHostException uhe) {
//            System.err.println("Host not known: " + uhe.getMessage());
//        } catch (NoRouteToHostException nre) {
//            System.err.println("Route to host not available: " + nre.getMessage());
//        } catch (ConnectException ce) {
//            System.err.println("Connection refused: " + ce.getMessage());
//        } catch (IOException ioe) {
//            System.err.println("IO Exception: " + ioe.getMessage());
//        }
    }

    /**
     * Public helper method to determine if the connection is closed or not.
     * This method may or may not be temporary.
     *
     * @return boolean representing whether or not the connection is closed.
     * @author Louis Keith
     */
    public boolean isCloseConnection() {
        return closeConnection;
    }

    /**
     * A method to get a command from the user and perform the appropriate action.
     * Reads data into the dataToSendToServer object and initializes it as FileClackData or MessageClackData.
     *
     * @author Louis Keith
     */
    public void readClientData() {
        // get command from user
        System.out.print("Enter command: ");
        String userInput = inFromStd.next();

        switch (userInput) {
            // check if the user wishes to close the connection and does so
            case "DONE": {
                closeConnection = true;
                dataToSendToServer = new MessageClackData(userName, userInput, key, ClackData.CONSTANT_LOGOUT);
                break;
            }
            case "SENDFILE": {
                String fileName = inFromStd.next();
                dataToSendToServer = new FileClackData(userName, fileName, ClackData.CONSTANT_SENDFILE);
                try {
                    ((FileClackData) dataToSendToServer).readFileContents(key);
                } catch (IOException ioe) {
                    dataToSendToServer = null;
                    System.err.println(ioe.getMessage());
                }
                break;
            }
            case "LISTUSERS": {
                dataToSendToServer = new MessageClackData(userName, userInput, key, ClackData.CONSTANT_LISTUSERS);
                break;
            }
            default: {
                String restOfLine = inFromStd.nextLine();
                dataToSendToServer = new MessageClackData(userName, userInput + restOfLine,
                        key, ClackData.CONSTANT_SENDMESSAGE);
                break;
            }
        }
    }

    /**
     * A method to write the data contained in dataToSendToServer
     * to the output stream outToServer
     *
     * @author Louis Keith
     */
    public void sendData() {
        try {
            outToServer.writeObject(dataToSendToServer);
            outToServer.flush();
            // hopefully this is temporary
            // without it the program prints the last message twice and errors before closing
            if (dataToSendToServer.getType() == ClackData.CONSTANT_LOGOUT) {
                System.exit(0);
            }
            // now it just kills the program instantly
            // fully aware this is bad practice, but its a dirty fix

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * A method to read the data contained in inFromServer
     * into dataToReceiveFromServer
     *
     * @author Louis Keith
     */
    public void receiveData() {
        try {
            dataToReceiveFromServer = (ClackData) inFromServer.readObject();

        } catch (ClassNotFoundException | IOException cnf) {
            System.err.println("Read failed: " + cnf.getMessage());
        }
    }

    /**
     * Reads from dataToSendToServer and prints all of the information a client.
     * may want to see in a user-friendly manner.
     *
     * @author Louis Keith
     */
    public void printData() {
        if (dataToReceiveFromServer == null) {
            System.out.println("There is no data to receive, the reference is null");
        } else if (dataToReceiveFromServer.getType() == ClackData.CONSTANT_SENDFILE) {
            System.out.println(hostName + " sent " + ((FileClackData) dataToReceiveFromServer).getFileName()
                    + " on " + dataToReceiveFromServer.getDate());
            System.out.println(dataToReceiveFromServer.getData(key));
        } else if (dataToReceiveFromServer.getType() == ClackData.CONSTANT_SENDMESSAGE) {
            System.out.println(hostName + " sent a message on " + dataToReceiveFromServer.getDate());
            System.out.println(dataToReceiveFromServer.getData(key));
        } else if (dataToReceiveFromServer.getType() == ClackData.CONSTANT_LISTUSERS) {
            System.out.println("User list: " + dataToReceiveFromServer.getData());
        }
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
//    @Override
//    public int hashCode() {
//        int closeConnectionInt = 0;
//        if (closeConnection) {
//            closeConnectionInt = 1;
//        }
//        return DEFAULT_HASH_CODE + userName.hashCode() + hostName.hashCode() + port +
//                dataToReceiveFromServer.hashCode() + dataToSendToServer.hashCode() +
//                closeConnectionInt;
//    }

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

    /**
     * A main method to test ClackClient with command line arguments
     *
     * @author Louis Keith
     */
    public static void main(String[] args) {
        launch(args);
    }
}