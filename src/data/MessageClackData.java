package data;

import data.ClackData;

/**
 * This class is the basic building block for a message in Clack.
 *
 * @author Louis Keith
 */

public class MessageClackData extends ClackData {

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


    public String getData() {
        return "";
    }
}
