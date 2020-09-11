package test;

import main2.ClackClient;

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
        ClackClient testClackClient1 = new ClackClient(testUserName, testHostName, port);
        System.out.println("testClackClient1's user name: " + testClackClient1.getUserName());
        System.out.println("testClackClient1's host name: " + testClackClient1.getHostName());
        System.out.println("testClackClient1's port: " + testClackClient1.getPort());
        System.out.println("testClackClient1's hash code: " + testClackClient1.hashCode());
        System.out.println("testClackClient1's toString method: \n" + testClackClient1.toString());
        System.out.print("\n");

        ClackClient testClackClient2 = new ClackClient(testUserName, testHostName, port);
        System.out.println("testClackClient2's user name: " + testClackClient2.getUserName());
        System.out.println("testClackClient2's host name: " + testClackClient2.getHostName());
        System.out.println("testClackClient2's port: " + testClackClient2.getPort());
        System.out.println("testClackClient2's hash code: " + testClackClient2.hashCode());
        System.out.println("testClackClient2's toString method: \n" + testClackClient2.toString());
        System.out.print("\n");

        System.out.println("testClackClient1 and testClackClient2 are equal: "
                + testClackClient1.equals(testClackClient2));
        System.out.print("\n\n");


        // Test ClackClient's second constructor
        ClackClient testClackClient3 = new ClackClient(testUserName, testHostName);
        System.out.println("testClackClient3's user name: " + testClackClient3.getUserName());
        System.out.println("testClackClient3's host name: " + testClackClient3.getHostName());
        System.out.println("testClackClient3's port: " + testClackClient3.getPort());
        System.out.println("testClackClient3's hash code: " + testClackClient3.hashCode());
        System.out.println("testClackClient3's toString method: \n" + testClackClient3.toString());
        System.out.print("\n");

        ClackClient testClackClient4 = new ClackClient(testUserName, testHostName);
        System.out.println("testClackClient4's user name: " + testClackClient4.getUserName());
        System.out.println("testClackClient4's host name: " + testClackClient4.getHostName());
        System.out.println("testClackClient4's port: " + testClackClient4.getPort());
        System.out.println("testClackClient4's hash code: " + testClackClient4.hashCode());
        System.out.println("testClackClient4's toString method: \n" + testClackClient4.toString());
        System.out.print("\n");

        System.out.println("testClackClient3 and testClackClient4 are equal: "
                + testClackClient3.equals(testClackClient4));
        System.out.print("\n\n");


        // Test ClackClient's third constructor
        ClackClient testClackClient5 = new ClackClient(testUserName);
        System.out.println("testClackClient5's user name: " + testClackClient5.getUserName());
        System.out.println("testClackClient5's host name: " + testClackClient5.getHostName());
        System.out.println("testClackClient5's port: " + testClackClient5.getPort());
        System.out.println("testClackClient5's hash code: " + testClackClient5.hashCode());
        System.out.println("testClackClient5's toString method: \n" + testClackClient5.toString());
        System.out.print("\n");

        ClackClient testClackClient6 = new ClackClient(testUserName);
        System.out.println("testClackClient6's user name: " + testClackClient6.getUserName());
        System.out.println("testClackClient6's host name: " + testClackClient6.getHostName());
        System.out.println("testClackClient6's port: " + testClackClient6.getPort());
        System.out.println("testClackClient6's hash code: " + testClackClient6.hashCode());
        System.out.println("testClackClient6's toString method: \n" + testClackClient6.toString());
        System.out.print("\n");

        System.out.println("testClackClient5 and testClackClient6 are equal: "
                + testClackClient5.equals(testClackClient6));
        System.out.print("\n\n");


        // Test ClackClient's default constructor
        ClackClient testClackClient7 = new ClackClient(testUserName);
        System.out.println("testClackClient7's user name: " + testClackClient7.getUserName());
        System.out.println("testClackClient7's host name: " + testClackClient7.getHostName());
        System.out.println("testClackClient7's port: " + testClackClient7.getPort());
        System.out.println("testClackClient7's hash code: " + testClackClient7.hashCode());
        System.out.println("testClackClient7's toString method: \n" + testClackClient7.toString());
        System.out.print("\n");

        ClackClient testClackClient8 = new ClackClient(testUserName);
        System.out.println("testClackClient8's user name: " + testClackClient8.getUserName());
        System.out.println("testClackClient8's host name: " + testClackClient8.getHostName());
        System.out.println("testClackClient8's port: " + testClackClient8.getPort());
        System.out.println("testClackClient8's hash code: " + testClackClient8.hashCode());
        System.out.println("testClackClient8's toString method: \n" + testClackClient8.toString());
        System.out.print("\n");

        System.out.println("testClackClient7 and testClackClient8 are equal: "
                + testClackClient7.equals(testClackClient8));
        System.out.print("\n\n");
    }
}