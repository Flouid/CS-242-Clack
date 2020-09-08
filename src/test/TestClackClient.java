package test;

import main.ClackClient;

/**
 * This class tests the TestClackClient class
 *
 * @author Louis Keith
 */
public class TestClackClient {

    public static void main(String[] args) {

        // Test Variables
        final String testUserName = "testUserName1";
        final String testHostName = "testHostName1";
        final int port = 9999;


        // Test ClackClient's first constructor
        ClackClient testClackData1 = new ClackClient(testUserName, testHostName, port);
    }
}
