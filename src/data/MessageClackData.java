package data;

import data.ClackData;

/**
 * This class is the basic building block for a message in Clack.
 *
 * @author Louis Keith
 */

public class MessageClackData extends ClackData {

    // default values
    private final static String DEFAULT_MESSAGE = "";

    // instance variable declarations
    String message;

    /**
     * General purpose constructor to set up username, message, and type
     *
     * @param userName String representing the user name of the sender
     * @param message String representing the user's message
     * @param type Integer representing the type of message being sent
     */
    public MessageClackData(String userName, String message, int type) {
        super(userName, type);
        this.message = message;
    }

    /**
     * Default constructor initializes with a blank message.
     */
    public MessageClackData() {
        super();
        this.message = DEFAULT_MESSAGE;
    }

    /**
     * A method to return the contents of a user's message.
     *
     * @return String representing the user's message
     */
    public String getData() {
        return message;
    }

    /**
     * A method to correctly return a unique hashcode for the message.
     *
     * @return Integer representing a unique hashcode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
