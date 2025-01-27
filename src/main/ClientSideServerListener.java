package main;

/**
 * This class is responsible for allowing the client to connect and listen to the server.
 *
 * @author Louis Keith
 */
public class ClientSideServerListener implements Runnable {

    // instance variable declarations
    ClackClient client;

    /**
     * Constructor to create the client side listener.
     *
     * @param client ClackClient object representing the client.
     */
    ClientSideServerListener(ClackClient client) {
        this.client = client;
    }

    /**
     * The functional part of the class that runs while the connection is open.
     * Receives and prints data from the server.
     */
    @Override
    public void run() {
        while (!client.isCloseConnection()) {
            client.receiveData();
            client.printData();
        }
    }
}
