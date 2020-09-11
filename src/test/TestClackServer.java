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
        System.out.println("testServer's port: " + testServer.getPort());
        System.out.println("testServer's hashCode: " + testServer.hashCode());
        System.out.println("testServer's toString: " + testServer.toString());

        ClackServer testServer2 = new ClackServer(port);
        System.out.println("\ntestServer2's port: " + testServer2.getPort());
        System.out.println("testServer2's hashCode: " + testServer2.hashCode());
        System.out.println("testServer2's toString: " + testServer2.toString());

        System.out.println("\nare testServer and testServer2 equal: " + testServer.equals(testServer2));

        //testing ClackServer default constructor
        ClackServer testServer3 = new ClackServer();
        System.out.println("testServer's port: " + testServer3.getPort());
        System.out.println("testServer's hashCode: " + testServer3.hashCode());
        System.out.println("testServer's toString: " + testServer3.toString());

        ClackServer testServer4 = new ClackServer();
        System.out.println("\ntestServer2's port: " + testServer4.getPort());
        System.out.println("testServer2's hashCode: " + testServer4.hashCode());
        System.out.println("testServer2's toString: " + testServer4.toString());

        System.out.println("\nare testServer3 and testServer4 equal: " + testServer3.equals(testServer4));
    }
}
