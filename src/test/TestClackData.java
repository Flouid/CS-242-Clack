package test;

import data.*;

/**
 * This class tests the MessageClackData and FileClackData classes.
 *
 * @author Louis Keith
 */
public class TestClackData {

    public static void main(String[] args) {

        // Test Variables
        final String testName = "name1";
        final String testMessage = "test1";
        final String testFileName1 = "fileName1";
        final String testFileName2 = "fileName2";
        final int testType = 0;

        /*
         * MessageClackData
         */

        // Test MessageClackData's first constructor
        ClackData testMessageClackData1 = new MessageClackData(testName, testMessage, testType);
        System.out.println("Type of testMessageClackData1: " + testMessageClackData1.getType());
        System.out.println("User name of testMessageClackData1: " + testMessageClackData1.getUserName());
        System.out.println("Date of testMessageClackData1: " + testMessageClackData1.getDate());
        System.out.println("Message contents of testMessageClackData1: " + testMessageClackData1.getData());
        System.out.println("Hash code of testMessageClackData1: " + testMessageClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testMessageClackData1.toString());
        System.out.print("\n");

        ClackData testMessageClackData2 = new MessageClackData(testName, testMessage, testType);
        System.out.println("Type of testMessageClackData2: " + testMessageClackData2.getType());
        System.out.println("User name of testMessageClackData2: " + testMessageClackData2.getUserName());
        System.out.println("Date of testMessageClackData2: " + testMessageClackData2.getDate());
        System.out.println("Message contents of testMessageClackData2: " + testMessageClackData2.getData());
        System.out.println("Hash code of testMessageClackData2: " + testMessageClackData2.hashCode());
        System.out.println("testMessageClackData2's toString method: \n" + testMessageClackData2.toString());
        System.out.print("\n");

        // This is incorrectly returning false
        System.out.println("testMessageClackData1 and testMessageClackData2 are equal: "
                + testMessageClackData1.equals(testMessageClackData2));
        System.out.print("\n");


        // Test MessageClackData's default constructor
        ClackData testMessageClackData3 = new MessageClackData();
        System.out.println("Type of testMessageClackData3: " + testMessageClackData3.getType());
        System.out.println("User name of testMessageClackData3: " + testMessageClackData3.getUserName());
        System.out.println("Date of testMessageClackData3: " + testMessageClackData3.getDate());
        System.out.println("Message contents of testMessageClackData3: " + testMessageClackData3.getData());
        System.out.println("Hash code of testMessageClackData3: " + testMessageClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testMessageClackData3.toString());
        System.out.print("\n");

        ClackData testMessageClackData4 = new MessageClackData();
        System.out.println("Type of testMessageClackData4: " + testMessageClackData4.getType());
        System.out.println("User name of testMessageClackData4: " + testMessageClackData4.getUserName());
        System.out.println("Date of testMessageClackData4: " + testMessageClackData4.getDate());
        System.out.println("Message contents of testMessageClackData4: " + testMessageClackData4.getData());
        System.out.println("Hash code of testMessageClackData4: " + testMessageClackData4.hashCode());
        System.out.println("testMessageClackData4's toString method: \n" + testMessageClackData4.toString());
        System.out.print("\n");

        // This is incorrectly returning false
        System.out.println("testMessageClackData3 and testMessageClackData4 are equal: "
                + testMessageClackData3.equals(testMessageClackData4));
        System.out.print("\n");

        /*
         * FileClackData
         */

        // Test FileClackData's first constructor
        ClackData testFileClackData1 = new FileClackData(testName, testFileName1, testType);
        System.out.println("Type of testFileClackData1: " + testFileClackData1.getType());
        System.out.println("User name of testFileClackData1: " + testFileClackData1.getUserName());
        System.out.println("Date of testFileClackData1: " + testFileClackData1.getDate());
        // getFileName() is specific to the subclass, it cant be accessed :\
        //System.out.println("File Name of testFileClackData1: " + testFileClackData1.getFileName());
        System.out.println("File contents of testMessageClackData1: " + testFileClackData1.getData());
        System.out.println("Hash code of testMessageClackData1: " + testFileClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testFileClackData1.toString());
        System.out.print("\n");

        ClackData testFileClackData2 = new FileClackData(testName, testFileName1, testType);
        System.out.println("Type of testFileClackData2: " + testFileClackData2.getType());
        System.out.println("User name of testFileClackData2: " + testFileClackData2.getUserName());
        System.out.println("Date of testFileClackData2: " + testFileClackData2.getDate());
        // getFileName() is specific to the subclass, it cant be accessed :\
        //System.out.println("File Name of testFileClackData2: " + testFileClackData2.getFileName());
        System.out.println("File contents of testMessageClackData2: " + testFileClackData2.getData());
        System.out.println("Hash code of testMessageClackData2: " + testFileClackData2.hashCode());
        System.out.println("testMessageClackData2's toString method: \n" + testFileClackData2.toString());
        System.out.print("\n");

        System.out.println("testFileClackData1 and testFileClackData2 are equal: "
                + testFileClackData1.equals(testFileClackData2));
        System.out.print("\n");

        // Test FileClackData's setFileName method
        System.out.println("Changing testFileClackData1's file name to: " + testFileName2);
        // Same issue as getFileName()
        //testFileClackData1.setFileName(testFileName2);
        System.out.println("Type of testFileClackData1: " + testFileClackData1.getType());
        System.out.println("User name of testFileClackData1: " + testFileClackData1.getUserName());
        System.out.println("Date of testFileClackData1: " + testFileClackData1.getDate());
        // getFileName() is specific to the subclass, it cant be accessed :\
        //System.out.println("File Name of testFileClackData1: " + testFileClackData1.getFileName());
        System.out.println("File contents of testMessageClackData1: " + testFileClackData1.getData());
        System.out.println("Hash code of testMessageClackData1: " + testFileClackData1.hashCode());
        System.out.println("testMessageClackData1's toString method: \n" + testFileClackData1.toString());
        System.out.print("\n\n");


        // Test FileClackData's default constructor
        ClackData testFileClackData3 = new FileClackData();
        System.out.println("Type of testFileClackData3: " + testFileClackData3.getType());
        System.out.println("User name of testFileClackData3: " + testFileClackData3.getUserName());
        System.out.println("Date of testFileClackData3: " + testFileClackData3.getDate());
        // getFileName() is specific to the subclass, it cant be accessed :\
        //System.out.println("File Name of testFileClackData3: " + testFileClackData3.getFileName());
        System.out.println("File contents of testMessageClackData3: " + testFileClackData3.getData());
        System.out.println("Hash code of testMessageClackData3: " + testFileClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testFileClackData3.toString());
        System.out.print("\n");

        ClackData testFileClackData4 = new FileClackData();
        System.out.println("Type of testFileClackData4: " + testFileClackData4.getType());
        System.out.println("User name of testFileClackData4: " + testFileClackData4.getUserName());
        System.out.println("Date of testFileClackData4: " + testFileClackData4.getDate());
        // getFileName() is specific to the subclass, it cant be accessed :\
        //System.out.println("File Name of testFileClackData4: " + testFileClackData4.getFileName());
        System.out.println("File contents of testMessageClackData4: " + testFileClackData4.getData());
        System.out.println("Hash code of testMessageClackData4: " + testFileClackData4.hashCode());
        System.out.println("testMessageClackData4's toString method: \n" + testFileClackData4.toString());
        System.out.print("\n");

        System.out.println("testFileClackData3 and testFileClackData4 are equal: "
                + testFileClackData3.equals(testFileClackData4));
        System.out.print("\n");

        // Test FileClackData's setFileName method
        System.out.println("Changing testFileClackData3's file name to: " + testFileName2);
        // Same issue as getFileName()
        //testFileClackData3.setFileName(testFileName2);
        System.out.println("Type of testFileClackData3: " + testFileClackData3.getType());
        System.out.println("User name of testFileClackData3: " + testFileClackData3.getUserName());
        System.out.println("Date of testFileClackData3: " + testFileClackData3.getDate());
        // getFileName() is specific to the subclass, it cant be accessed :\
        //System.out.println("File Name of testFileClackData3: " + testFileClackData3.getFileName());
        System.out.println("File contents of testMessageClackData3: " + testFileClackData3.getData());
        System.out.println("Hash code of testMessageClackData3: " + testFileClackData3.hashCode());
        System.out.println("testMessageClackData3's toString method: \n" + testFileClackData3.toString());
        System.out.print("\n\n");
    }
}
