package test;
import data.ClackData;

/**
 * This class exists to test that the ClackData Class
 * works exactly as intended.
 *
 * @author Louis Keith
 */

public class TestClackData {

    public static void main(String[] args) {

        // Test detailed constructor
        ClackData testOne = new ClackData("testOne", 0);
        // Test the getUserName and getType methods
        System.out.println("The first piece of data is named: " + testOne.getUserName() + " and has type: " + testOne.getType());
        // Test getDate method
        System.out.println("It was created at precisely: " + testOne.getDate());

        System.out.print("\n"); // blank line

        // Test anonymous constructor
        ClackData testTwo = new ClackData(1);
        System.out.println("The second piece of data is named: " + testTwo.getUserName() + " and has type: " + testTwo.getType());

        System.out.print("\n"); // blank line

        // Test default constructor
        ClackData testThree = new ClackData();
        System.out.println("The third piece of data is named: " + testThree.getUserName() + " and has type: " + testThree.getType());


    }
}
