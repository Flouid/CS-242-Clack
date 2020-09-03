package data;

import java.util.Date;

/**
 * This class is the basic building block for a unit of data in the Clack application.
 *
 * @author Louis Keith
 */

public class ClackData {

    // constant declarations
    public final static int CONSTANT_LISTUSERS = 0;
    public final static int CONSTANT_LOGOUT = 1;
    public final static int CONSTANT_SENDMESSAGE = 2;
    public final static int CONSTANT_SENDFILE = 3;

    // default values
    private final static String DEFAULT_NAME = "Anon";
    private final static int DEFAULT_TYPE = 1;

    // instance variable declarations
    String userName;
    int type;
    Date date;

    /**
     * General purpose constructor for creating a ClackData object.
     *
     * @param userName String representing the name of the user
     * @param type Integer representing data type
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }

    /**
     * Anonymous constructor for when no name is available.
     *
     * @param type Integer representing data type
     */
    public ClackData(int type) {
        this(DEFAULT_NAME, type);
    }

    /**
     * Default constructor creates an invalid ClackData object.
     */
    public ClackData() {
        // This might need revisiting, right now it creates an invalid object
        this(DEFAULT_NAME, DEFAULT_TYPE);
    }


    /**
     * A simple get method for the data type.
     *
     * @return Integer representing data type
     */
    public int getType() {
        return type;
    }

    /**
     * A simple get method for the username.
     *
     * @return String representing the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * A simple get method for the date.
     *
     * @return Date object representing the creation date of this instance of ClackData object.
     */
    public Date getDate() {
        return date;
    }

    // public abstract String getData();
}
