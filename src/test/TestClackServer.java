package test;

import main.ClackServer;

/**
 * This class tests the TestClackServer class
 *
 * @author Alex Cohen
 */

public class TestClackServer {
    public static void main(String[] args) {
        //test variables
        final int port = 9999;

        //testing ClackServer constructor
        ClackServer testServer = new ClackServer(port);
    }
}
