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
     * A method to correctly return a unique hashcode for the class.
     *
     * @return Integer representing a unique hashcode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * A method to determine if two messages are equal.
     *
     * @param other MessageClackData object representing another message
     * @return boolean representing if the other parameter equals this instance of MessageClackData
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof MessageClackData)) return false;

        MessageClackData otherMessage = (MessageClackData)other;
        return this.message.equals(otherMessage.getData()) &&
                this.userName.equals(otherMessage.getUserName()) &&
                this.type == otherMessage.getType();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
