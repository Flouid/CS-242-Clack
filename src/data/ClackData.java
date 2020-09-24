package data;

import java.util.Date;

/**
 * This class is the basic building block for a unit of data in the Clack application.
 *
 * @author Louis Keith
 */

public abstract class ClackData {

    // constant declarations
    public final static int CONSTANT_LISTUSERS = 0;
    public final static int CONSTANT_LOGOUT = 1;
    public final static int CONSTANT_SENDMESSAGE = 2;
    public final static int CONSTANT_SENDFILE = 3;

    // default values
    private final static String DEFAULT_NAME = "Anon";
    private final static int DEFAULT_TYPE = 1;

    // instance variable declarations
    private String userName;
    private int type;
    private Date date;

    /**
     * General purpose constructor for creating a ClackData object.
     *
     * @param userName String representing the name of the user
     * @param type     Integer representing data type
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

    /**
     * An abstract method to return the data contained in the class.
     *
     * @return String representing message or file contents.
     */
    public abstract String getData();

    /**
     * An abstract method to return the encrypted data contained in the class.
     *
     * @param key String representing the encryption key
     * @return String representing message or file contents
     */
    public abstract String getData(String key);

    protected String encrypt(String inputStringToEncrypt, String key) {
        char[] encryptedString = inputStringToEncrypt.toCharArray();
        char charBuffer;
        for (int i = 0; i < encryptedString.length; i++) {
            charBuffer = encryptedString[i];
            if (encryptedString[i] != ' ')
                encryptedString[i] += ((int) key.charAt(i % key.length()));

            if (charBuffer >= 65 && charBuffer <= 90) {
                encryptedString[i] = (char) (encryptedString[i] % 90);
                if (encryptedString[i] < 65)
                    encryptedString[i] += 65;
            } else if (charBuffer >= 97 && charBuffer <= 122) {
                encryptedString[i] = (char) (encryptedString[i] % 122);
                if (encryptedString[i] < 97)
                    encryptedString[i] += 97;
            }
        }
        return new String(encryptedString);
    }

    protected String decrypt(String inputStringToDecrypt, String key) {
        return "";
    }
}
