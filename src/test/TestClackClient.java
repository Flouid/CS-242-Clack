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

        // Testing first constructor
        System.out.println("    TESTING FIRST CONSTRUCTOR");
        ClackClient clackClient1 = new ClackClient(testUserName, testHostName, port);

        // Test accessors
        System.out.println("ACCESSORS");
        System.out.println("User name of clackClient1: " + clackClient1.getUserName());
        System.out.println("Host name of clackClient1: " + clackClient1.getHostName());
        System.out.println("Port number of clackClient 1: " + clackClient1.getPort());
        System.out.println("Hash code of clackClient 1: " + clackClient1.hashCode());
        System.out.println("ToString method of clackClient 1: " + clackClient1.toString());
        System.out.print("\n");

        // Identical object
        ClackClient clackClient2 = new ClackClient(testUserName, testHostName, port);
        System.out.println("Testing if clackClient1 and clackClient2 are equal (should be true): " +
                clackClient1.equals(clackClient2));
        System.out.print("\n");

        // Testing start method, which in turn tests internal functionality
        System.out.println("START METHOD");
//        clackClient1.start();
        System.out.println("\n");


        // Testing second constructor
        System.out.println("    TESTING SECOND CONSTRUCTOR");
        ClackClient clackClient3 = new ClackClient(testUserName, testHostName);

        // Test accessors
        System.out.println("ACCESSORS");
        System.out.println("User name of clackClient3: " + clackClient3.getUserName());
        System.out.println("Host name of clackClient3: " + clackClient3.getHostName());
        System.out.println("Port number of clackClient 3: " + clackClient3.getPort());
        System.out.println("Hash code of clackClient 3: " + clackClient3.hashCode());
        System.out.println("ToString method of clackClient 3: " + clackClient3.toString());
        System.out.print("\n");

        // Identical object
        ClackClient clackClient4 = new ClackClient(testUserName, testHostName, port);
        System.out.println("Testing if clackClient3 and clackClient4 are equal (should be true): " +
                clackClient3.equals(clackClient4));
        System.out.print("\n");

        // Testing start method, which in turn tests internal functionality
        System.out.println("START METHOD");
//        clackClient3.start();
        System.out.println("\n");


        // Testing third constructor
        System.out.println("    TESTING THIRD CONSTRUCTOR");
        ClackClient clackClient5 = new ClackClient(testUserName);

        // Test accessors
        System.out.println("ACCESSORS");
        System.out.println("User name of clackClient5: " + clackClient5.getUserName());
        System.out.println("Host name of clackClient5: " + clackClient5.getHostName());
        System.out.println("Port number of clackClient 5: " + clackClient5.getPort());
        System.out.println("Hash code of clackClient 5: " + clackClient5.hashCode());
        System.out.println("ToString method of clackClient 5: " + clackClient5.toString());
        System.out.print("\n");

        // Identical object
        ClackClient clackClient6 = new ClackClient(testUserName, testHostName, port);
        System.out.println("Testing if clackClient5 and clackClient6 are equal (should be true): " +
                clackClient5.equals(clackClient6));
        System.out.print("\n");

        // Testing start method, which in turn tests internal functionality
        System.out.println("START METHOD");
//        clackClient5.start();
        System.out.println("\n");


        // Testing default constructor
        System.out.println("    TESTING DEFAULT CONSTRUCTOR");
        ClackClient clackClient7 = new ClackClient(testUserName);

        // Test accessors
        System.out.println("ACCESSORS");
        System.out.println("User name of clackClient7: " + clackClient7.getUserName());
        System.out.println("Host name of clackClient7: " + clackClient7.getHostName());
        System.out.println("Port number of clackClient 7: " + clackClient7.getPort());
        System.out.println("Hash code of clackClient 7: " + clackClient7.hashCode());
        System.out.println("ToString method of clackClient 7: " + clackClient7.toString());
        System.out.print("\n");

        // Identical object
        ClackClient clackClient8 = new ClackClient(testUserName, testHostName, port);
        System.out.println("Testing if clackClient7 and clackClient8 are equal (should be true): " +
                clackClient7.equals(clackClient8));
        System.out.print("\n");

        // Testing start method, which in turn tests internal functionality
        System.out.println("START METHOD");
//        clackClient7.start();
        System.out.println("\n");
    }
}
