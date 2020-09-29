package test;

import data.*;

import java.io.IOException;

/**
 * This class tests the MessageClackData and FileClackData classes.
 *
 * @author Louis Keith
 */
@SuppressWarnings("CastCanBeRemovedNarrowingVariableType")
public class TestClackData {

    public static void main(String[] args) {

        // Test Variables
        final String testName = "name1";
        final String testMessage = "test1";
        final String testFileName1 = "Part2_document.txt";
        final String testKey = "key";
        final String testFileName2 = "fileName1.txt";
        final int testType = 0;

        /*
         * MessageClackData
         */

        // Test MessageClackData's first constructor
        // This constructor does not take a key (insecure mode) so it can't test the secure methods
        System.out.println("    TESTING FIRST CONSTRUCTOR OF MessageClackData");
        ClackData testMessageClackData1 = new MessageClackData(testName, testMessage, testType);
        System.out.println("Type of testMessageClackData1: " + testMessageClackData1.getType());
        System.out.println("User name of testMessageClackData1: " + testMessageClackData1.getUserName());
        System.out.println("Date of testMessageClackData1: " + testMessageClackData1.getDate());
        System.out.println("Message contents of testMessageClackData1: " + testMessageClackData1.getData());
        System.out.println("Hash code of testMessageClackData1: " + testMessageClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testMessageClackData1.toString());
        System.out.print("\n");

        // Identical object
        ClackData testMessageClackData2 = new MessageClackData(testName, testMessage, testType);
        System.out.println("testMessageClackData1 and testMessageClackData2 are equal (should be true): "
                + testMessageClackData1.equals(testMessageClackData2));
        System.out.print("\n");

        // Test MessageClackData's second constructor
        // This constructor takes a key, so here secure methods are tested.
        System.out.println("    TESTING SECOND CONSTRUCTOR OF MessageClackData");
        ClackData testMessageClackData3 = new MessageClackData(testName, testMessage, testKey, testType);
        System.out.println("Type of testMessageClackData3: " + testMessageClackData3.getType());
        System.out.println("User name of testMessageClackData3: " + testMessageClackData3.getUserName());
        System.out.println("Date of testMessageClackData3: " + testMessageClackData3.getDate());
        // The message will be printed in it's encrypted form
        System.out.println("Encrypted message contents of testMessageClackData3: " + testMessageClackData3.getData());
        // The same message will be printed but the secure method for getData() will be used, decrypting the message
        System.out.println("Decrypted message contents of testMessageClackData3: " + testMessageClackData3.getData(testKey));
        System.out.println("Hash code of testMessageClackData3: " + testMessageClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testMessageClackData3.toString());
        System.out.print("\n");

        // Identical object
        ClackData testMessageClackData4 = new MessageClackData(testName, testMessage, testKey, testType);
        System.out.println("testMessageClackData3 and testMessageClackData4 are equal (should be true): "
                + testMessageClackData3.equals(testMessageClackData4));
        System.out.print("\n");


        // Test MessageClackData's default constructor
        // This constructor does not take a key (insecure mode) so again it can't test the secure methods
        System.out.println("    TESTING DEFAULT CONSTRUCTOR OF MessageClackData");
        ClackData testMessageClackData5 = new MessageClackData();
        System.out.println("Type of testMessageClackData5: " + testMessageClackData5.getType());
        System.out.println("User name of testMessageClackData5: " + testMessageClackData5.getUserName());
        System.out.println("Date of testMessageClackData5: " + testMessageClackData5.getDate());
        System.out.println("Message contents of testMessageClackData5: " + testMessageClackData5.getData());
        System.out.println("Hash code of testMessageClackData5: " + testMessageClackData5.hashCode());
        System.out.println("testMessageClackData5's toString method: \n" + testMessageClackData5.toString());
        System.out.print("\n");

        // Identical object
        ClackData testMessageClackData6 = new MessageClackData();
        System.out.println("testMessageClackData5 and testMessageClackData6 are equal (should be true): "
                + testMessageClackData5.equals(testMessageClackData6));
        System.out.print("\n");

        /*
         * FileClackData
         */

        // Test FileClackData's first constructor
        System.out.println("    TESTING FIRST CONSTRUCTOR OF FileClackData");
        ClackData testFileClackData1 = new FileClackData(testName, testFileName1, testType);

        // Identical object, testing before mutators
        ClackData testFileClackData2 = new FileClackData(testName, testFileName1, testType);
        System.out.println("testFileClackData1 and testFileClackData2 are equal (should be true): "
                + testFileClackData1.equals(testFileClackData2));
        System.out.print("\n");

        // INSECURE METHODS
        System.out.println("INSECURE METHODS");
        // Reading file
        try {
            ((FileClackData) testFileClackData1).readFileContents();
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        // Testing Accessors
        System.out.println("Type of testFileClackData1: " + testFileClackData1.getType());
        System.out.println("User name of testFileClackData1: " + testFileClackData1.getUserName());
        System.out.println("Date of testFileClackData1: " + testFileClackData1.getDate());
        System.out.println("File name of testMessageClackData1: " + ((FileClackData)testFileClackData1).getFileName());
        System.out.println("File contents of testMessageClackData1: " + testFileClackData1.getData());
        System.out.println("Hash code of testMessageClackData1: " + testFileClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testFileClackData1.toString());
        System.out.print("\n");
        // Mutating the file name
        System.out.println("CHANGING FILE NAME TO " + testFileName2);
        ((FileClackData)testFileClackData1).setFileName(testFileName2);
        // Writing to the new file
        ((FileClackData)testFileClackData1).writeFileContents();
        // Retesting Accessors to make sure nothing incorrect changed
        System.out.println("Type of testFileClackData1: " + testFileClackData1.getType());
        System.out.println("User name of testFileClackData1: " + testFileClackData1.getUserName());
        System.out.println("Date of testFileClackData1: " + testFileClackData1.getDate());
        System.out.println("File name of testMessageClackData1: " + ((FileClackData)testFileClackData1).getFileName());
        System.out.println("File contents of testMessageClackData1: " + testFileClackData1.getData());
        System.out.println("Hash code of testMessageClackData1: " + testFileClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testFileClackData1.toString());
        System.out.print("\n");
        // Reverting the file name
        ((FileClackData)testFileClackData1).setFileName(testFileName1);

        // SECURE METHODS
        System.out.println("SECURE METHODS");
        // Reading file
        try {
            ((FileClackData) testFileClackData1).readFileContents(testKey);
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        // Testing Accessors
        System.out.println("Type of testFileClackData1: " + testFileClackData1.getType());
        System.out.println("User name of testFileClackData1: " + testFileClackData1.getUserName());
        System.out.println("Date of testFileClackData1: " + testFileClackData1.getDate());
        System.out.println("File name of testMessageClackData1: " + ((FileClackData)testFileClackData1).getFileName());
        System.out.println("Encrypted File contents of testMessageClackData1: " + testFileClackData1.getData());
        System.out.println("Decrypted File contents of testMessageClackData1: " + testFileClackData1.getData(testKey));
        System.out.println("Hash code of testMessageClackData1: " + testFileClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testFileClackData1.toString());
        System.out.print("\n");
        // Mutating the file name
        System.out.println("CHANGING FILE NAME TO " + testFileName2);
        ((FileClackData)testFileClackData1).setFileName(testFileName2);
        // Writing to the new file
        ((FileClackData)testFileClackData1).writeFileContents(testKey);
        // Retesting Accessors to make sure nothing incorrect changed
        System.out.println("Type of testFileClackData1: " + testFileClackData1.getType());
        System.out.println("User name of testFileClackData1: " + testFileClackData1.getUserName());
        System.out.println("Date of testFileClackData1: " + testFileClackData1.getDate());
        System.out.println("File name of testMessageClackData1: " + ((FileClackData)testFileClackData1).getFileName());
        System.out.println("Encrypted File contents of testMessageClackData1: " + testFileClackData1.getData());
        System.out.println("Decrypted File contents of testMessageClackData1: " + testFileClackData1.getData(testKey));
        System.out.println("Hash code of testMessageClackData1: " + testFileClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testFileClackData1.toString());
        System.out.print("\n");


        // Test FileClackData's default constructor
        System.out.println("    TESTING DEFAULT CONSTRUCTOR OF FileClackData");
        ClackData testFileClackData3 = new FileClackData(testName, testFileName1, testType);

        // Identical object, testing before mutators
        ClackData testFileClackData4 = new FileClackData(testName, testFileName1, testType);
        System.out.println("testFileClackData3 and testFileClackData4 are equal (should be true): "
                + testFileClackData3.equals(testFileClackData4));
        System.out.print("\n");

        // INSECURE METHODS
        System.out.println("INSECURE METHODS");
        // Reading file
        try {
            ((FileClackData) testFileClackData3).readFileContents();
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        // Testing Accessors
        System.out.println("Type of testFileClackData3: " + testFileClackData3.getType());
        System.out.println("User name of testFileClackData3: " + testFileClackData3.getUserName());
        System.out.println("Date of testFileClackData3: " + testFileClackData3.getDate());
        System.out.println("File name of testMessageClackData3: " + ((FileClackData)testFileClackData3).getFileName());
        System.out.println("File contents of testMessageClackData3: " + testFileClackData3.getData());
        System.out.println("Hash code of testMessageClackData3: " + testFileClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testFileClackData3.toString());
        System.out.print("\n");
        // Mutating the file name
        ((FileClackData)testFileClackData3).setFileName(testFileName2);
        // Writing to the new file
        ((FileClackData)testFileClackData3).writeFileContents();
        // Retesting Accessors to make sure nothing incorrect changed
        System.out.println("Type of testFileClackData3: " + testFileClackData3.getType());
        System.out.println("User name of testFileClackData3: " + testFileClackData3.getUserName());
        System.out.println("Date of testFileClackData3: " + testFileClackData3.getDate());
        System.out.println("File name of testMessageClackData3: " + ((FileClackData)testFileClackData3).getFileName());
        System.out.println("File contents of testMessageClackData3: " + testFileClackData3.getData());
        System.out.println("Hash code of testMessageClackData3: " + testFileClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testFileClackData3.toString());
        System.out.print("\n");
        // Reverting the file name
        ((FileClackData)testFileClackData3).setFileName(testFileName1);

        // SECURE METHODS
        System.out.println("SECURE METHODS");
        // Reading file
        try {
            ((FileClackData) testFileClackData3).readFileContents(testKey);
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        // Testing Accessors
        System.out.println("Type of testFileClackData3: " + testFileClackData3.getType());
        System.out.println("User name of testFileClackData3: " + testFileClackData3.getUserName());
        System.out.println("Date of testFileClackData3: " + testFileClackData3.getDate());
        System.out.println("File name of testMessageClackData3: " + ((FileClackData)testFileClackData3).getFileName());
        System.out.println("Encrypted File contents of testMessageClackData3: " + testFileClackData3.getData());
        System.out.println("Decrypted File contents of testMessageClackData3: " + testFileClackData3.getData(testKey));
        System.out.println("Hash code of testMessageClackData3: " + testFileClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testFileClackData3.toString());
        System.out.print("\n");
        // Mutating the file name
        ((FileClackData)testFileClackData1).setFileName(testFileName2);
        // Writing to the new file
        ((FileClackData)testFileClackData1).writeFileContents(testKey);
        // Retesting Accessors to make sure nothing incorrect changed
        System.out.println("Type of testFileClackData3: " + testFileClackData3.getType());
        System.out.println("User name of testFileClackData3: " + testFileClackData3.getUserName());
        System.out.println("Date of testFileClackData3: " + testFileClackData3.getDate());
        System.out.println("File name of testMessageClackData3: " + ((FileClackData)testFileClackData3).getFileName());
        System.out.println("Encrypted File contents of testMessageClackData3: " + testFileClackData3.getData());
        System.out.println("Decrypted File contents of testMessageClackData3: " + testFileClackData3.getData(testKey));
        System.out.println("Hash code of testMessageClackData3: " + testFileClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testFileClackData3.toString());
        System.out.print("\n");
    }
}
