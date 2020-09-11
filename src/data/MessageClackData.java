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
    private String message;

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
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode
     */
    @Override
    public int hashCode() {
        int hashCode = 3;
        hashCode += getType() + getUserName().hashCode() + message.hashCode();
        return hashCode;
    }

    /**
     * A method to determine if two messages are equal.
     *
     * @param other MessageClackData object representing another message
     * @return boolean representing if the other parameter equals this instance of MessageClackData
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        MessageClackData otherMessage = (MessageClackData)other;
        return message.equals(otherMessage.getData()) &&
                getUserName().equals(otherMessage.getUserName()) &&
                getType() == otherMessage.getType();
    }

    /**
     * A method to return the entire message object as a string.
     *
     * @return A string with all of the instance data for the message
     */
    @Override
    public String toString() {
        return getUserName() + " sent a message: " + message + "\nSent on: " + getDate() + " with type: " + getType();
    }
}
